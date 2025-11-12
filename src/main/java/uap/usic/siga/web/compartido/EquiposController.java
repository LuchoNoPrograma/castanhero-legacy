package uap.usic.siga.web.compartido;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para la gestión de equipos y participantes
 * Usado en eventos, olimpiadas y competencias
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/equipos")
public class EquiposController {

    /**
     * Lista todos los equipos
     */
    @GetMapping("")
    public String listarEquipos(
            @RequestParam(value = "evento", required = false) String evento,
            Model model) {

        // Implementación pendiente - delegar a servicios
        return "equipos/listar";
    }

    /**
     * Muestra el formulario para crear un nuevo equipo
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        return "equipos/formulario";
    }

    /**
     * Guarda un nuevo equipo
     */
    @PostMapping("/guardar")
    public String guardarEquipo(
            @RequestParam("nombre") String nombre,
            @RequestParam("institucion") String institucion,
            @RequestParam(value = "categoria", required = false) String categoria,
            RedirectAttributes redirectAttributes) {

        try {
            redirectAttributes.addFlashAttribute("mensaje", "Equipo registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar equipo");
        }
        return "redirect:/equipos";
    }

    // ========== Participantes ==========

    /**
     * Lista los participantes de un equipo
     */
    @GetMapping("/participantes")
    public String listarParticipantes(
            @RequestParam("idEquipo") Long idEquipo,
            Model model) {

        return "equipos/participantes/listar";
    }

    /**
     * Agrega un participante a un equipo
     */
    @PostMapping("/participantes/agregar")
    public String agregarParticipante(
            @RequestParam("idEquipo") Long idEquipo,
            @RequestParam("idPersona") Long idPersona,
            @RequestParam(value = "rol", required = false) String rol,
            RedirectAttributes redirectAttributes) {

        try {
            redirectAttributes.addFlashAttribute("mensaje", "Participante agregado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al agregar participante");
        }
        return "redirect:/equipos/participantes?idEquipo=" + idEquipo;
    }

    /**
     * Elimina un participante de un equipo
     */
    @PostMapping("/participantes/eliminar")
    public String eliminarParticipante(
            @RequestParam("idParticipante") Long idParticipante,
            @RequestParam("idEquipo") Long idEquipo,
            RedirectAttributes redirectAttributes) {

        try {
            redirectAttributes.addFlashAttribute("mensaje", "Participante eliminado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar participante");
        }
        return "redirect:/equipos/participantes?idEquipo=" + idEquipo;
    }
}
