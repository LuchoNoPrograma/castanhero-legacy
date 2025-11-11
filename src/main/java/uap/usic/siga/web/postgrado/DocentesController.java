package uap.usic.siga.web.postgrado;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.service.postgrado.DocenteService;

/**
 * Controlador para la gestión de docentes de postgrado
 * Incluye: asignación de materias, registro de notas y horarios
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/pg")
public class DocentesController {

    @Autowired
    private DocenteService docenteService;

    /**
     * Lista todos los docentes activos
     */
    @GetMapping("/docentes")
    public String listarDocentes(Model model, HttpSession session) {
        Long idSisAdministrador = obtenerIdSisAdministrador(session);
        model.addAttribute("docentes", docenteService.listarDocentesActivos(idSisAdministrador));
        return "pg/docentes/listar";
    }

    /**
     * Muestra las asignaciones de un docente
     */
    @GetMapping("/docentes/asignaciones")
    public String verAsignaciones(
            @RequestParam("idDocente") Long idDocente,
            Model model) {

        model.addAttribute("docente", docenteService.buscarDocentePorId(idDocente));
        model.addAttribute("asignaciones", docenteService.listarAsignacionesPorDocente(idDocente));
        return "pg/docentes/asignaciones";
    }

    /**
     * Asigna una materia a un docente
     */
    @PostMapping("/docentes/asignar")
    public String asignarMateria(
            @RequestParam("idDocente") Long idDocente,
            @RequestParam("idMateria") Long idMateria,
            @RequestParam("gestion") String gestion,
            RedirectAttributes redirectAttributes) {

        try {
            docenteService.asignarMateriaADocente(idDocente, idMateria, gestion);
            redirectAttributes.addFlashAttribute("mensaje", "Materia asignada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al asignar materia: " + e.getMessage());
        }
        return "redirect:/pg/docentes/asignaciones?idDocente=" + idDocente;
    }

    // ========== Registro de Notas ==========

    /**
     * Muestra el formulario para registrar notas
     */
    @GetMapping("/docentes/notas")
    public String mostrarFormularioNotas(
            @RequestParam("idAsignacion") Long idAsignacion,
            Model model) {

        model.addAttribute("asignacion", docenteService.buscarAsignacionPorId(idAsignacion));
        model.addAttribute("estudiantes", docenteService.listarEstudiantesPorAsignacion(idAsignacion));
        model.addAttribute("notas", docenteService.listarNotasPorAsignacion(idAsignacion));
        return "pg/docentes/notas";
    }

    /**
     * Guarda las notas de los estudiantes
     */
    @PostMapping("/docentes/notas/guardar")
    public String guardarNotas(
            @RequestParam("idAsignacion") Long idAsignacion,
            @RequestParam("idsEstudiantes") Long[] idsEstudiantes,
            @RequestParam("notas") Double[] notas,
            RedirectAttributes redirectAttributes) {

        try {
            docenteService.registrarNotas(idAsignacion, idsEstudiantes, notas);
            redirectAttributes.addFlashAttribute("mensaje", "Notas registradas exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar notas: " + e.getMessage());
        }
        return "redirect:/pg/docentes/notas?idAsignacion=" + idAsignacion;
    }

    /**
     * Actualiza una nota específica
     */
    @PostMapping("/docentes/notas/actualizar")
    public String actualizarNota(
            @RequestParam("idNota") Long idNota,
            @RequestParam("nota") Double nota,
            @RequestParam("idAsignacion") Long idAsignacion,
            RedirectAttributes redirectAttributes) {

        try {
            docenteService.actualizarNota(idNota, nota);
            redirectAttributes.addFlashAttribute("mensaje", "Nota actualizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar nota");
        }
        return "redirect:/pg/docentes/notas?idAsignacion=" + idAsignacion;
    }

    /**
     * Publica las notas de una asignación
     */
    @PostMapping("/docentes/notas/publicar")
    public String publicarNotas(
            @RequestParam("idAsignacion") Long idAsignacion,
            RedirectAttributes redirectAttributes) {

        try {
            docenteService.publicarNotas(idAsignacion);
            redirectAttributes.addFlashAttribute("mensaje", "Notas publicadas exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al publicar notas");
        }
        return "redirect:/pg/docentes/notas?idAsignacion=" + idAsignacion;
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
