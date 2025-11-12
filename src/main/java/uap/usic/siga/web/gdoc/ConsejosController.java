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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.service.gdoc.GdocConsejosService;
import uap.usic.siga.service.gdoc.GdocGestionConsejosService;

/**
 * Controlador para la gestión de consejos universitarios y facultativos
 * Incluye: creación, configuración y gestión de sesiones de consejos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/gdoc/consejos")
@Slf4j
@RequiredArgsConstructor
public class ConsejosController {

    private final GdocConsejosService consejosService;

    private final GdocGestionConsejosService gestionConsejosService;

    /**
     * Lista todos los consejos activos
     */
    @GetMapping("")
    public String listarConsejos(Model model, HttpSession session) {
        Long idSisAdministrador = obtenerIdSisAdministrador(session);
        model.addAttribute("consejos", consejosService.listarConsejosActivos(idSisAdministrador));
        return "gdoc/consejos/listar";
    }

    /**
     * Muestra el formulario para crear un nuevo consejo
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        cargarDatosComunes(model);
        return "gdoc/consejos/formulario";
    }

    /**
     * Guarda un nuevo consejo
     */
    @PostMapping("/guardar")
    public String guardarConsejo(
            @RequestParam("nombre") String nombre,
            @RequestParam("tipo") String tipo,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            consejosService.guardarConsejo(nombre, tipo, descripcion, idSisAdministrador);
            redirectAttributes.addFlashAttribute("mensaje", "Consejo creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear consejo: " + e.getMessage());
        }
        return "redirect:/gdoc/consejos";
    }

    /**
     * Muestra el formulario para editar un consejo
     */
    @GetMapping("/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("consejo", consejosService.buscarConsejoPorId(id));
        cargarDatosComunes(model);
        return "gdoc/consejos/formulario";
    }

    /**
     * Actualiza un consejo existente
     */
    @PostMapping("/actualizar")
    public String actualizarConsejo(
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            RedirectAttributes redirectAttributes) {

        try {
            consejosService.actualizarConsejo(id, nombre, descripcion);
            redirectAttributes.addFlashAttribute("mensaje", "Consejo actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar consejo");
        }
        return "redirect:/gdoc/consejos";
    }

    // ========== Gestión de Sesiones de Consejo ==========

    /**
     * Lista las sesiones de un consejo
     */
    @GetMapping("/sesiones")
    public String listarSesiones(
            @RequestParam("idConsejo") Long idConsejo,
            Model model) {

        model.addAttribute("consejo", consejosService.buscarConsejoPorId(idConsejo));
        model.addAttribute("sesiones", gestionConsejosService.listarSesionesPorConsejo(idConsejo));
        return "gdoc/consejos/sesiones";
    }

    /**
     * Crea una nueva sesión de consejo
     */
    @PostMapping("/sesiones/guardar")
    public String guardarSesion(
            @RequestParam("idConsejo") Long idConsejo,
            @RequestParam("fecha") String fecha,
            @RequestParam("numeroSesion") Integer numeroSesion,
            @RequestParam(value = "temas", required = false) String temas,
            RedirectAttributes redirectAttributes) {

        try {
            gestionConsejosService.crearSesionConsejo(idConsejo, fecha, numeroSesion, temas);
            redirectAttributes.addFlashAttribute("mensaje", "Sesión creada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear sesión");
        }
        return "redirect:/gdoc/consejos/sesiones?idConsejo=" + idConsejo;
    }

    /**
     * Registra los miembros de un consejo
     */
    @PostMapping("/miembros/agregar")
    public String agregarMiembro(
            @RequestParam("idConsejo") Long idConsejo,
            @RequestParam("idPersona") Long idPersona,
            @RequestParam("cargo") String cargo,
            RedirectAttributes redirectAttributes) {

        try {
            consejosService.agregarMiembroConsejo(idConsejo, idPersona, cargo);
            redirectAttributes.addFlashAttribute("mensaje", "Miembro agregado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al agregar miembro");
        }
        return "redirect:/gdoc/consejos/editar?id=" + idConsejo;
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("tiposConsejo", consejosService.listarTiposConsejo());
        model.addAttribute("personas", consejosService.listarPersonasDisponibles());
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
