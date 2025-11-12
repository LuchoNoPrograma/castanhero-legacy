package uap.usic.siga.web.gdoc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.service.gdoc.GdocResolucionesService;
import uap.usic.siga.service.gdoc.GdocArchivosAdjuntosService;

/**
 * Controlador para la gestión de resoluciones universitarias
 * Incluye: resoluciones físicas, digitales y archivo de documentos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/gdoc/resoluciones")
@Slf4j
@RequiredArgsConstructor
public class ResolucionesController {

    private final GdocResolucionesService resolucionesService;

    private final GdocArchivosAdjuntosService archivosService;

    /**
     * Lista todas las resoluciones
     */
    @GetMapping("")
    public String listarResoluciones(
            @RequestParam(value = "gestion", required = false) String gestion,
            @RequestParam(value = "tipo", required = false) String tipo,
            Model model,
            HttpSession session) {

        Long idSisAdministrador = obtenerIdSisAdministrador(session);

        if (gestion != null && !gestion.isEmpty()) {
            model.addAttribute("resoluciones", resolucionesService.listarResolucionesPorGestion(gestion, idSisAdministrador));
        } else if (tipo != null && !tipo.isEmpty()) {
            model.addAttribute("resoluciones", resolucionesService.listarResolucionesPorTipo(tipo, idSisAdministrador));
        } else {
            model.addAttribute("resoluciones", resolucionesService.listarTodasResoluciones(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "gdoc/resoluciones/listar";
    }

    /**
     * Muestra el formulario para registrar una nueva resolución
     */
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        cargarDatosComunes(model);
        return "gdoc/resoluciones/formulario";
    }

    /**
     * Guarda una nueva resolución
     */
    @PostMapping("/guardar")
    public String guardarResolucion(
            @RequestParam("numero") String numero,
            @RequestParam("fecha") String fecha,
            @RequestParam("tipo") String tipo,
            @RequestParam("asunto") String asunto,
            @RequestParam(value = "contenido", required = false) String contenido,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            Long idResolucion = resolucionesService.guardarResolucion(
                    numero, fecha, tipo, asunto, contenido, idSisAdministrador);

            // Guardar archivo adjunto si existe
            if (archivo != null && !archivo.isEmpty()) {
                archivosService.guardarArchivoResolucion(idResolucion, archivo);
            }

            redirectAttributes.addFlashAttribute("mensaje", "Resolución guardada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar resolución: " + e.getMessage());
        }
        return "redirect:/gdoc/resoluciones";
    }

    /**
     * Muestra el formulario para editar una resolución
     */
    @GetMapping("/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("resolucion", resolucionesService.buscarResolucionPorId(id));
        model.addAttribute("archivos", archivosService.listarArchivosPorResolucion(id));
        cargarDatosComunes(model);
        return "gdoc/resoluciones/formulario";
    }

    /**
     * Actualiza una resolución existente
     */
    @PostMapping("/actualizar")
    public String actualizarResolucion(
            @RequestParam("id") Long id,
            @RequestParam("asunto") String asunto,
            @RequestParam(value = "contenido", required = false) String contenido,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            RedirectAttributes redirectAttributes) {

        try {
            resolucionesService.actualizarResolucion(id, asunto, contenido);

            // Actualizar archivo si se proporcionó uno nuevo
            if (archivo != null && !archivo.isEmpty()) {
                archivosService.actualizarArchivoResolucion(id, archivo);
            }

            redirectAttributes.addFlashAttribute("mensaje", "Resolución actualizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar resolución");
        }
        return "redirect:/gdoc/resoluciones";
    }

    /**
     * Elimina una resolución
     */
    @PostMapping("/eliminar")
    public String eliminarResolucion(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            resolucionesService.eliminarResolucion(id);
            redirectAttributes.addFlashAttribute("mensaje", "Resolución eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar resolución");
        }
        return "redirect:/gdoc/resoluciones";
    }

    // ========== Resoluciones Digitales ==========

    /**
     * Convierte una resolución física a digital
     */
    @PostMapping("/digitalizar")
    public String digitalizarResolucion(
            @RequestParam("id") Long id,
            @RequestParam("archivo") MultipartFile archivo,
            RedirectAttributes redirectAttributes) {

        try {
            resolucionesService.digitalizarResolucion(id, archivo);
            redirectAttributes.addFlashAttribute("mensaje", "Resolución digitalizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al digitalizar resolución");
        }
        return "redirect:/gdoc/resoluciones/editar?id=" + id;
    }

    /**
     * Descarga el archivo PDF de una resolución
     */
    @GetMapping("/descargar")
    public String descargarResolucion(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            // Lógica de descarga
            return "gdoc/resoluciones/descargar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al descargar resolución");
            return "redirect:/gdoc/resoluciones";
        }
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("tiposResolucion", resolucionesService.listarTiposResolucion());
        model.addAttribute("gestiones", resolucionesService.listarGestionesDisponibles());
        model.addAttribute("estados", resolucionesService.listarEstadosResolucion());
    }

    /**
     * Obtiene el ID del sistema administrador desde la sesión
     */
    private Long obtenerIdSisAdministrador(HttpSession session) {
        Object sisAdminObj = session.getAttribute("sisAdministrador");
        if (sisAdminObj != null) {
            return ((uap.usic.siga.entidades.SisAdministrador) sisAdminObj).getIdSisAdministrador();
        }
        return 1L;
    }
}
