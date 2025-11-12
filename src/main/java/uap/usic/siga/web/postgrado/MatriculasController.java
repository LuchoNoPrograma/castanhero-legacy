package uap.usic.siga.web.postgrado;

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

import uap.usic.siga.service.postgrado.MatriculaService;

/**
 * Controlador para la gestión de matrículas de postgrado
 * Incluye: inscripción, modificación y anulación de matrículas
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/pg")
@Slf4j
@RequiredArgsConstructor
public class MatriculasController {

    private final MatriculaService matriculaService;

    /**
     * Lista todas las matrículas
     */
    @GetMapping("/matriculas")
    public String listarMatriculas(
            @RequestParam(value = "idLanzamiento", required = false) Long idLanzamiento,
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model,
            HttpSession session) {

        if (idLanzamiento != null) {
            model.addAttribute("matriculas", matriculaService.listarMatriculasPorLanzamiento(idLanzamiento));
            model.addAttribute("idLanzamiento", idLanzamiento);
        } else if (gestion != null) {
            model.addAttribute("matriculas", matriculaService.listarMatriculasPorGestion(gestion));
            model.addAttribute("gestion", gestion);
        } else {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            model.addAttribute("matriculas", matriculaService.listarTodasMatriculas(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "pg/matriculas/listar";
    }

    /**
     * Muestra el formulario para matricular un estudiante
     */
    @GetMapping("/matriculas/nueva")
    public String mostrarFormularioNuevaMatricula(
            @RequestParam(value = "idEstudiante", required = false) Long idEstudiante,
            Model model) {

        if (idEstudiante != null) {
            model.addAttribute("estudiante", matriculaService.buscarEstudiantePorId(idEstudiante));
        }

        cargarDatosComunes(model);
        return "pg/matriculas/formulario";
    }

    /**
     * Registra una nueva matrícula
     */
    @PostMapping("/matriculas/guardar")
    public String guardarMatricula(
            @RequestParam("idEstudiante") Long idEstudiante,
            @RequestParam("idLanzamiento") Long idLanzamiento,
            @RequestParam("gestion") String gestion,
            @RequestParam(value = "idsMaterias", required = false) Long[] idsMaterias,
            RedirectAttributes redirectAttributes) {

        try {
            matriculaService.registrarMatricula(idEstudiante, idLanzamiento, gestion, idsMaterias);
            redirectAttributes.addFlashAttribute("mensaje", "Matrícula registrada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar matrícula: " + e.getMessage());
        }
        return "redirect:/pg/matriculas?idLanzamiento=" + idLanzamiento;
    }

    /**
     * Muestra los detalles de una matrícula
     */
    @GetMapping("/matriculas/detalle")
    public String verDetalleMatricula(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("matricula", matriculaService.buscarMatriculaPorId(id));
        model.addAttribute("materias", matriculaService.listarMateriasPorMatricula(id));
        return "pg/matriculas/detalle";
    }

    /**
     * Muestra el formulario para modificar una matrícula
     */
    @GetMapping("/matriculas/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("matricula", matriculaService.buscarMatriculaPorId(id));
        model.addAttribute("materiasMatriculadas", matriculaService.listarMateriasPorMatricula(id));
        cargarDatosComunes(model);
        return "pg/matriculas/formulario";
    }

    /**
     * Actualiza una matrícula existente
     */
    @PostMapping("/matriculas/actualizar")
    public String actualizarMatricula(
            @RequestParam("id") Long id,
            @RequestParam(value = "idsMaterias", required = false) Long[] idsMaterias,
            RedirectAttributes redirectAttributes) {

        try {
            matriculaService.actualizarMatricula(id, idsMaterias);
            redirectAttributes.addFlashAttribute("mensaje", "Matrícula actualizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar matrícula");
        }
        return "redirect:/pg/matriculas/detalle?id=" + id;
    }

    /**
     * Anula una matrícula
     */
    @PostMapping("/matriculas/anular")
    public String anularMatricula(
            @RequestParam("id") Long id,
            @RequestParam(value = "motivo", required = false) String motivo,
            RedirectAttributes redirectAttributes) {

        try {
            matriculaService.anularMatricula(id, motivo);
            redirectAttributes.addFlashAttribute("mensaje", "Matrícula anulada");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al anular matrícula");
        }
        return "redirect:/pg/matriculas";
    }

    /**
     * Confirma una matrícula pendiente
     */
    @PostMapping("/matriculas/confirmar")
    public String confirmarMatricula(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            matriculaService.confirmarMatricula(id);
            redirectAttributes.addFlashAttribute("mensaje", "Matrícula confirmada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al confirmar matrícula");
        }
        return "redirect:/pg/matriculas/detalle?id=" + id;
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("estudiantes", matriculaService.listarEstudiantesActivos());
        model.addAttribute("lanzamientos", matriculaService.listarLanzamientosDisponibles());
        model.addAttribute("gestiones", matriculaService.listarGestionesDisponibles());
        model.addAttribute("estadosMatricula", matriculaService.listarEstadosMatricula());
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
