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

import uap.usic.siga.service.gdoc.GdocConveniosService;
import uap.usic.siga.service.gdoc.GdocArchivosAdjuntosService;

/**
 * Controlador para la gestión de convenios institucionales
 * Incluye: registro, seguimiento y archivo de convenios
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/gdoc/convenios")
@Slf4j
@RequiredArgsConstructor
public class ConveniosController {

    private final GdocConveniosService conveniosService;

    private final GdocArchivosAdjuntosService archivosService;

    /**
     * Lista todos los convenios
     */
    @GetMapping("")
    public String listarConvenios(
            @RequestParam(value = "estado", required = false) String estado,
            @RequestParam(value = "tipo", required = false) String tipo,
            Model model,
            HttpSession session) {

        Long idSisAdministrador = obtenerIdSisAdministrador(session);

        if (estado != null && !estado.isEmpty()) {
            model.addAttribute("convenios", conveniosService.listarConveniosPorEstado(estado, idSisAdministrador));
        } else if (tipo != null && !tipo.isEmpty()) {
            model.addAttribute("convenios", conveniosService.listarConveniosPorTipo(tipo, idSisAdministrador));
        } else {
            model.addAttribute("convenios", conveniosService.listarTodosConvenios(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "gdoc/convenios/listar";
    }

    /**
     * Muestra el formulario para registrar un nuevo convenio
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        cargarDatosComunes(model);
        return "gdoc/convenios/formulario";
    }

    /**
     * Guarda un nuevo convenio
     */
    @PostMapping("/guardar")
    public String guardarConvenio(
            @RequestParam("nombre") String nombre,
            @RequestParam("institucion") String institucion,
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin,
            @RequestParam("tipo") String tipo,
            @RequestParam(value = "objetivos", required = false) String objetivos,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            Long idConvenio = conveniosService.guardarConvenio(
                    nombre, institucion, fechaInicio, fechaFin, tipo, objetivos, idSisAdministrador);

            // Guardar archivo adjunto si existe
            if (archivo != null && !archivo.isEmpty()) {
                archivosService.guardarArchivoConvenio(idConvenio, archivo);
            }

            redirectAttributes.addFlashAttribute("mensaje", "Convenio registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar convenio: " + e.getMessage());
        }
        return "redirect:/gdoc/convenios";
    }

    /**
     * Muestra el formulario para editar un convenio
     */
    @GetMapping("/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("convenio", conveniosService.buscarConvenioPorId(id));
        model.addAttribute("archivos", archivosService.listarArchivosPorConvenio(id));
        cargarDatosComunes(model);
        return "gdoc/convenios/formulario";
    }

    /**
     * Actualiza un convenio existente
     */
    @PostMapping("/actualizar")
    public String actualizarConvenio(
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("fechaFin") String fechaFin,
            @RequestParam(value = "objetivos", required = false) String objetivos,
            @RequestParam(value = "estado", required = false) String estado,
            RedirectAttributes redirectAttributes) {

        try {
            conveniosService.actualizarConvenio(id, nombre, fechaFin, objetivos, estado);
            redirectAttributes.addFlashAttribute("mensaje", "Convenio actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar convenio");
        }
        return "redirect:/gdoc/convenios";
    }

    /**
     * Renueva un convenio existente
     */
    @PostMapping("/renovar")
    public String renovarConvenio(
            @RequestParam("id") Long id,
            @RequestParam("nuevaFechaFin") String nuevaFechaFin,
            RedirectAttributes redirectAttributes) {

        try {
            conveniosService.renovarConvenio(id, nuevaFechaFin);
            redirectAttributes.addFlashAttribute("mensaje", "Convenio renovado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al renovar convenio");
        }
        return "redirect:/gdoc/convenios/editar?id=" + id;
    }

    /**
     * Finaliza un convenio
     */
    @PostMapping("/finalizar")
    public String finalizarConvenio(
            @RequestParam("id") Long id,
            @RequestParam(value = "observaciones", required = false) String observaciones,
            RedirectAttributes redirectAttributes) {

        try {
            conveniosService.finalizarConvenio(id, observaciones);
            redirectAttributes.addFlashAttribute("mensaje", "Convenio finalizado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al finalizar convenio");
        }
        return "redirect:/gdoc/convenios";
    }

    // ========== Reportes ==========

    /**
     * Genera reporte de convenios
     */
    @GetMapping("/reporte")
    public String generarReporte(
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "estado", required = false) String estado,
            Model model,
            HttpSession session) {

        Long idSisAdministrador = obtenerIdSisAdministrador(session);
        model.addAttribute("reporte", conveniosService.generarReporteConvenios(tipo, estado, idSisAdministrador));
        return "gdoc/convenios/reporte";
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("tiposConvenio", conveniosService.listarTiposConvenio());
        model.addAttribute("estadosConvenio", conveniosService.listarEstadosConvenio());
        model.addAttribute("instituciones", conveniosService.listarInstituciones());
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
