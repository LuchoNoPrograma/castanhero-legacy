package uap.usic.siga.web.postgrado;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.service.postgrado.ProgramaService;

/**
 * Controlador para la gestión de programas de postgrado
 * Incluye: programas, planes, materias, módulos y lanzamientos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/pg")
@Slf4j
@RequiredArgsConstructor
public class ProgramasController {

    private final ProgramaService programaService;

    /**
     * Lista todos los programas de postgrado
     */
    @GetMapping("/programas")
    public String listarProgramas(Model model, HttpSession session) {
        Long idSisAdministrador = obtenerIdSisAdministrador(session);
        model.addAttribute("programas", programaService.listarProgramasActivos(idSisAdministrador));
        return "pg/programas/listar";
    }

    /**
     * Muestra el formulario para crear un nuevo programa
     */
    @GetMapping("/programas/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        cargarDatosComunes(model);
        return "pg/programas/formulario";
    }

    /**
     * Guarda un nuevo programa
     */
    @PostMapping("/programas/guardar")
    public String guardarPrograma(
            @RequestParam("idPrograma") Long idPrograma,
            @RequestParam("nombre") String nombre,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            programaService.guardarPrograma(idPrograma, nombre, idSisAdministrador);
            redirectAttributes.addFlashAttribute("mensaje", "Programa guardado exitosamente");
            return "redirect:/pg/programas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el programa: " + e.getMessage());
            cargarDatosComunes(model);
            return "pg/programas/formulario";
        }
    }

    /**
     * Muestra el formulario para editar un programa
     */
    @GetMapping("/programas/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("programa", programaService.buscarProgramaPorId(id));
        cargarDatosComunes(model);
        return "pg/programas/formulario";
    }

    /**
     * Elimina un programa
     */
    @PostMapping("/programas/eliminar")
    public String eliminarPrograma(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            programaService.eliminarPrograma(id);
            redirectAttributes.addFlashAttribute("mensaje", "Programa eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el programa");
        }
        return "redirect:/pg/programas";
    }

    // ========== Planes de Estudio ==========

    /**
     * Lista los planes de un programa
     */
    @GetMapping("/programas/planes")
    public String listarPlanes(
            @RequestParam("idPrograma") Long idPrograma,
            Model model) {

        model.addAttribute("programa", programaService.buscarProgramaPorId(idPrograma));
        model.addAttribute("planes", programaService.listarPlanesPorPrograma(idPrograma));
        return "pg/planes/listar";
    }

    /**
     * Guarda un nuevo plan de estudios
     */
    @PostMapping("/programas/planes/guardar")
    public String guardarPlan(
            @RequestParam("idPrograma") Long idPrograma,
            @RequestParam("nombrePlan") String nombrePlan,
            RedirectAttributes redirectAttributes) {

        try {
            programaService.guardarPlan(idPrograma, nombrePlan);
            redirectAttributes.addFlashAttribute("mensaje", "Plan guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el plan");
        }
        return "redirect:/pg/programas/planes?idPrograma=" + idPrograma;
    }

    // ========== Materias ==========

    /**
     * Lista las materias de un plan
     */
    @GetMapping("/programas/materias")
    public String listarMaterias(
            @RequestParam("idPlan") Long idPlan,
            Model model) {

        model.addAttribute("plan", programaService.buscarPlanPorId(idPlan));
        model.addAttribute("materias", programaService.listarMateriasPorPlan(idPlan));
        return "pg/materias/listar";
    }

    /**
     * Guarda una nueva materia
     */
    @PostMapping("/programas/materias/guardar")
    public String guardarMateria(
            @RequestParam("idPlan") Long idPlan,
            @RequestParam("nombreMateria") String nombreMateria,
            @RequestParam("creditos") Integer creditos,
            RedirectAttributes redirectAttributes) {

        try {
            programaService.guardarMateria(idPlan, nombreMateria, creditos);
            redirectAttributes.addFlashAttribute("mensaje", "Materia guardada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la materia");
        }
        return "redirect:/pg/programas/materias?idPlan=" + idPlan;
    }

    // ========== Lanzamientos ==========

    /**
     * Lista los lanzamientos de programas
     */
    @GetMapping("/programas/lanzamientos")
    public String listarLanzamientos(Model model, HttpSession session) {
        Long idSisAdministrador = obtenerIdSisAdministrador(session);
        model.addAttribute("lanzamientos", programaService.listarLanzamientos(idSisAdministrador));
        return "pg/lanzamientos/listar";
    }

    /**
     * Crea un nuevo lanzamiento de programa
     */
    @PostMapping("/programas/lanzamientos/guardar")
    public String guardarLanzamiento(
            @RequestParam("idPrograma") Long idPrograma,
            @RequestParam("gestion") String gestion,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            programaService.crearLanzamiento(idPrograma, gestion, idSisAdministrador);
            redirectAttributes.addFlashAttribute("mensaje", "Lanzamiento creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el lanzamiento");
        }
        return "redirect:/pg/programas/lanzamientos";
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("tiposPrograma", programaService.listarTiposPrograma());
        model.addAttribute("modalidades", programaService.listarModalidades());
    }

    /**
     * Obtiene el ID del sistema administrador desde la sesión
     */
    private Long obtenerIdSisAdministrador(HttpSession session) {
        Object sisAdminObj = session.getAttribute("sisAdministrador");
        if (sisAdminObj != null) {
            return ((uap.usic.siga.entidades.SisAdministrador) sisAdminObj).getIdSisAdministrador();
        }
        return 1L; // Valor por defecto
    }
}
