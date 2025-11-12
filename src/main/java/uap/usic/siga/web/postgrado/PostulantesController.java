package uap.usic.siga.web.postgrado;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.service.postgrado.PostulanteService;

/**
 * Controlador para la gestión de postulantes a programas de postgrado
 * Incluye: registro, admisión y seguimiento de postulantes
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/pg")
public class PostulantesController {

    @Autowired
    private PostulanteService postulanteService;

    /**
     * Lista todos los postulantes
     */
    @GetMapping("/postulantes")
    public String listarPostulantes(
            @RequestParam(value = "idLanzamiento", required = false) Long idLanzamiento,
            Model model,
            HttpSession session) {

        if (idLanzamiento != null) {
            model.addAttribute("postulantes", postulanteService.listarPostulantesPorLanzamiento(idLanzamiento));
            model.addAttribute("idLanzamiento", idLanzamiento);
        } else {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            model.addAttribute("postulantes", postulanteService.listarTodosPostulantes(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "pg/postulantes/listar";
    }

    /**
     * Muestra el formulario para registrar un nuevo postulante
     */
    @GetMapping("/postulantes/nuevo")
    public String mostrarFormularioNuevo(
            @RequestParam("idLanzamiento") Long idLanzamiento,
            Model model) {

        model.addAttribute("idLanzamiento", idLanzamiento);
        cargarDatosComunes(model);
        return "pg/postulantes/formulario";
    }

    /**
     * Registra un nuevo postulante
     */
    @PostMapping("/postulantes/guardar")
    public String guardarPostulante(
            @RequestParam("idLanzamiento") Long idLanzamiento,
            @RequestParam("idPersona") Long idPersona,
            @RequestParam(value = "observaciones", required = false) String observaciones,
            RedirectAttributes redirectAttributes) {

        try {
            postulanteService.registrarPostulante(idLanzamiento, idPersona, observaciones);
            redirectAttributes.addFlashAttribute("mensaje", "Postulante registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar postulante: " + e.getMessage());
        }
        return "redirect:/pg/postulantes?idLanzamiento=" + idLanzamiento;
    }

    /**
     * Muestra el formulario para editar un postulante
     */
    @GetMapping("/postulantes/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("postulante", postulanteService.buscarPostulantePorId(id));
        cargarDatosComunes(model);
        return "pg/postulantes/formulario";
    }

    /**
     * Elimina un postulante
     */
    @PostMapping("/postulantes/eliminar")
    public String eliminarPostulante(
            @RequestParam("id") Long id,
            @RequestParam("idLanzamiento") Long idLanzamiento,
            RedirectAttributes redirectAttributes) {

        try {
            postulanteService.eliminarPostulante(id);
            redirectAttributes.addFlashAttribute("mensaje", "Postulante eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar postulante");
        }
        return "redirect:/pg/postulantes?idLanzamiento=" + idLanzamiento;
    }

    // ========== Admitidos ==========

    /**
     * Lista los postulantes admitidos
     */
    @GetMapping("/postulantes/admitidos")
    public String listarAdmitidos(
            @RequestParam("idLanzamiento") Long idLanzamiento,
            Model model) {

        model.addAttribute("admitidos", postulanteService.listarAdmitidosPorLanzamiento(idLanzamiento));
        model.addAttribute("idLanzamiento", idLanzamiento);
        return "pg/postulantes/admitidos";
    }

    /**
     * Admite a un postulante
     */
    @PostMapping("/postulantes/admitir")
    public String admitirPostulante(
            @RequestParam("id") Long id,
            @RequestParam("idLanzamiento") Long idLanzamiento,
            @RequestParam(value = "puntaje", required = false) Double puntaje,
            RedirectAttributes redirectAttributes) {

        try {
            postulanteService.admitirPostulante(id, puntaje);
            redirectAttributes.addFlashAttribute("mensaje", "Postulante admitido exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al admitir postulante");
        }
        return "redirect:/pg/postulantes?idLanzamiento=" + idLanzamiento;
    }

    /**
     * Rechaza a un postulante
     */
    @PostMapping("/postulantes/rechazar")
    public String rechazarPostulante(
            @RequestParam("id") Long id,
            @RequestParam("idLanzamiento") Long idLanzamiento,
            @RequestParam(value = "motivo", required = false) String motivo,
            RedirectAttributes redirectAttributes) {

        try {
            postulanteService.rechazarPostulante(id, motivo);
            redirectAttributes.addFlashAttribute("mensaje", "Postulante rechazado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al rechazar postulante");
        }
        return "redirect:/pg/postulantes?idLanzamiento=" + idLanzamiento;
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("personas", postulanteService.listarPersonasDisponibles());
        model.addAttribute("estadosPostulante", postulanteService.listarEstadosPostulante());
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
