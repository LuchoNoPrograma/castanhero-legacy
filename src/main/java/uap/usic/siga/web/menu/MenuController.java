package uap.usic.siga.web.menu;

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

import uap.usic.siga.servicios.MenuesServicios;

/**
 * Controlador para la gestión del sistema de menús
 * Incluye: menús, enlaces, funciones y permisos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/menu")
@Slf4j
@RequiredArgsConstructor
public class MenuController {

    private final MenuesServicios menuesServicios;

    /**
     * Lista todos los menús del sistema
     */
    @GetMapping("/menues")
    public String listarMenues(Model model) {
        model.addAttribute("menues", menuesServicios.listarTodosMenues());
        return "menu/menues/listar";
    }

    /**
     * Guarda un nuevo menú
     */
    @PostMapping("/menues/guardar")
    public String guardarMenu(
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "orden", required = false) Integer orden,
            RedirectAttributes redirectAttributes) {

        try {
            menuesServicios.guardarMenu(nombre, descripcion, orden);
            redirectAttributes.addFlashAttribute("mensaje", "Menú guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar menú");
        }
        return "redirect:/menu/menues";
    }

    // ========== Enlaces ==========

    /**
     * Lista todos los enlaces del sistema
     */
    @GetMapping("/enlaces")
    public String listarEnlaces(
            @RequestParam(value = "idMenu", required = false) Long idMenu,
            Model model) {

        if (idMenu != null) {
            model.addAttribute("enlaces", menuesServicios.listarEnlacesPorMenu(idMenu));
            model.addAttribute("menu", menuesServicios.buscarMenuPorId(idMenu));
        } else {
            model.addAttribute("enlaces", menuesServicios.listarTodosEnlaces());
        }

        model.addAttribute("menues", menuesServicios.listarTodosMenues());
        return "menu/enlaces/listar";
    }

    /**
     * Guarda un nuevo enlace
     */
    @PostMapping("/enlaces/guardar")
    public String guardarEnlace(
            @RequestParam("idMenu") Long idMenu,
            @RequestParam("nombre") String nombre,
            @RequestParam("url") String url,
            @RequestParam(value = "icono", required = false) String icono,
            @RequestParam(value = "orden", required = false) Integer orden,
            RedirectAttributes redirectAttributes) {

        try {
            menuesServicios.guardarEnlace(idMenu, nombre, url, icono, orden);
            redirectAttributes.addFlashAttribute("mensaje", "Enlace guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar enlace");
        }
        return "redirect:/menu/enlaces?idMenu=" + idMenu;
    }

    /**
     * Actualiza un enlace existente
     */
    @PostMapping("/enlaces/actualizar")
    public String actualizarEnlace(
            @RequestParam("idEnlace") Long idEnlace,
            @RequestParam("nombre") String nombre,
            @RequestParam("url") String url,
            @RequestParam(value = "estado", required = false) String estado,
            RedirectAttributes redirectAttributes) {

        try {
            menuesServicios.actualizarEnlace(idEnlace, nombre, url, estado);
            redirectAttributes.addFlashAttribute("mensaje", "Enlace actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar enlace");
        }
        return "redirect:/menu/enlaces";
    }

    // ========== Funciones ==========

    /**
     * Lista todas las funciones del sistema
     */
    @GetMapping("/funciones")
    public String listarFunciones(
            @RequestParam(value = "idTipoFuncion", required = false) Long idTipoFuncion,
            Model model) {

        if (idTipoFuncion != null) {
            model.addAttribute("funciones", menuesServicios.listarFuncionesPorTipo(idTipoFuncion));
        } else {
            model.addAttribute("funciones", menuesServicios.listarTodasFunciones());
        }

        model.addAttribute("tiposFuncion", menuesServicios.listarTiposFuncion());
        return "menu/funciones/listar";
    }

    /**
     * Guarda una nueva función
     */
    @PostMapping("/funciones/guardar")
    public String guardarFuncion(
            @RequestParam("idTipoFuncion") Long idTipoFuncion,
            @RequestParam("nombre") String nombre,
            @RequestParam("codigo") String codigo,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            RedirectAttributes redirectAttributes) {

        try {
            menuesServicios.guardarFuncion(idTipoFuncion, nombre, codigo, descripcion);
            redirectAttributes.addFlashAttribute("mensaje", "Función guardada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar función");
        }
        return "redirect:/menu/funciones";
    }

    /**
     * Asigna funciones a un usuario
     */
    @PostMapping("/funciones/asignar")
    public String asignarFunciones(
            @RequestParam("idUsuario") Long idUsuario,
            @RequestParam("idsFunciones") Long[] idsFunciones,
            RedirectAttributes redirectAttributes) {

        try {
            menuesServicios.asignarFuncionesAUsuario(idUsuario, idsFunciones);
            redirectAttributes.addFlashAttribute("mensaje", "Funciones asignadas exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al asignar funciones");
        }
        return "redirect:/menu/funciones";
    }
}
