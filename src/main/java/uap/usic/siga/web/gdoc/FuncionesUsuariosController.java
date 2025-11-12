package uap.usic.siga.web.gdoc;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para la asignación de funciones y permisos de usuarios GDOC
 * Gestiona los roles y accesos específicos del módulo de gestión documental
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/gdoc/funciones")
public class FuncionesUsuariosController {

    /**
     * Lista las funciones asignadas a usuarios
     */
    @GetMapping("")
    public String listarFuncionesUsuarios(Model model) {
        // Implementación pendiente
        return "gdoc/funciones/listar";
    }

    /**
     * Muestra el formulario para asignar funciones a un usuario
     */
    @GetMapping("/asignar")
    public String mostrarFormularioAsignar(
            @RequestParam("idUsuario") Long idUsuario,
            Model model) {

        // Implementación pendiente
        return "gdoc/funciones/asignar";
    }

    /**
     * Asigna funciones a un usuario
     */
    @PostMapping("/guardar")
    public String guardarAsignacion(
            @RequestParam("idUsuario") Long idUsuario,
            @RequestParam("idsFunciones") Long[] idsFunciones,
            RedirectAttributes redirectAttributes) {

        try {
            // Implementación pendiente
            redirectAttributes.addFlashAttribute("mensaje", "Funciones asignadas exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al asignar funciones");
        }
        return "redirect:/gdoc/funciones";
    }

    /**
     * Revoca funciones de un usuario
     */
    @PostMapping("/revocar")
    public String revocarFunciones(
            @RequestParam("idUsuario") Long idUsuario,
            @RequestParam("idsFunciones") Long[] idsFunciones,
            RedirectAttributes redirectAttributes) {

        try {
            // Implementación pendiente
            redirectAttributes.addFlashAttribute("mensaje", "Funciones revocadas");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al revocar funciones");
        }
        return "redirect:/gdoc/funciones";
    }
}
