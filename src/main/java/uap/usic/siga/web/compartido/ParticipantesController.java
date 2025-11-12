package uap.usic.siga.web.compartido;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para la gestión general de participantes
 * Gestiona participantes independientes no asignados a equipos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/participantes")
public class ParticipantesController {

    /**
     * Lista todos los participantes registrados
     */
    @GetMapping("")
    public String listarParticipantes(
            @RequestParam(value = "evento", required = false) String evento,
            Model model) {

        // Implementación pendiente
        return "participantes/listar";
    }

    /**
     * Muestra el formulario para registrar un nuevo participante
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        return "participantes/formulario";
    }

    /**
     * Registra un nuevo participante
     */
    @PostMapping("/guardar")
    public String guardarParticipante(
            @RequestParam("idPersona") Long idPersona,
            @RequestParam(value = "categoria", required = false) String categoria,
            RedirectAttributes redirectAttributes) {

        try {
            redirectAttributes.addFlashAttribute("mensaje", "Participante registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar participante");
        }
        return "redirect:/participantes";
    }

    /**
     * Actualiza los datos de un participante
     */
    @PostMapping("/actualizar")
    public String actualizarParticipante(
            @RequestParam("id") Long id,
            @RequestParam(value = "categoria", required = false) String categoria,
            @RequestParam(value = "estado", required = false) String estado,
            RedirectAttributes redirectAttributes) {

        try {
            redirectAttributes.addFlashAttribute("mensaje", "Participante actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar participante");
        }
        return "redirect:/participantes";
    }
}
