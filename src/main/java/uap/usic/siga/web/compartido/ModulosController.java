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
 * Controlador para la gestión de ejecución de módulos del sistema
 * Administra la habilitación y configuración de módulos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/modulos")
public class ModulosController {

    /**
     * Lista todos los módulos del sistema
     */
    @GetMapping("")
    public String listarModulos(Model model) {
        // Implementación pendiente
        return "modulos/listar";
    }

    /**
     * Habilita un módulo para una gestión específica
     */
    @PostMapping("/habilitar")
    public String habilitarModulo(
            @RequestParam("idModulo") Long idModulo,
            @RequestParam("gestion") String gestion,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            redirectAttributes.addFlashAttribute("mensaje", "Módulo habilitado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al habilitar módulo");
        }
        return "redirect:/modulos";
    }

    /**
     * Deshabilita un módulo
     */
    @PostMapping("/deshabilitar")
    public String deshabilitarModulo(
            @RequestParam("idModulo") Long idModulo,
            RedirectAttributes redirectAttributes) {

        try {
            redirectAttributes.addFlashAttribute("mensaje", "Módulo deshabilitado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al deshabilitar módulo");
        }
        return "redirect:/modulos";
    }

    /**
     * Configura los parámetros de un módulo
     */
    @GetMapping("/configurar")
    public String mostrarConfiguracion(
            @RequestParam("idModulo") Long idModulo,
            Model model) {

        return "modulos/configurar";
    }

    /**
     * Guarda la configuración de un módulo
     */
    @PostMapping("/configurar/guardar")
    public String guardarConfiguracion(
            @RequestParam("idModulo") Long idModulo,
            RedirectAttributes redirectAttributes) {

        try {
            redirectAttributes.addFlashAttribute("mensaje", "Configuración guardada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar configuración");
        }
        return "redirect:/modulos";
    }
}
