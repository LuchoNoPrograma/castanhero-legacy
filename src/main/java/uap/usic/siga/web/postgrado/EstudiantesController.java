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

import uap.usic.siga.service.postgrado.EstudianteService;

/**
 * Controlador para la gestión de estudiantes de postgrado
 * Incluye: registro, seguimiento académico y datos personales
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/pg")
public class EstudiantesController {

    @Autowired
    private EstudianteService estudianteService;

    /**
     * Lista todos los estudiantes activos
     */
    @GetMapping("/estudiantes")
    public String listarEstudiantes(
            @RequestParam(value = "idPrograma", required = false) Long idPrograma,
            Model model,
            HttpSession session) {

        if (idPrograma != null) {
            model.addAttribute("estudiantes", estudianteService.listarEstudiantesPorPrograma(idPrograma));
            model.addAttribute("idPrograma", idPrograma);
        } else {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            model.addAttribute("estudiantes", estudianteService.listarTodosEstudiantes(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "pg/estudiantes/listar";
    }

    /**
     * Muestra el formulario para registrar un nuevo estudiante
     */
    @GetMapping("/estudiantes/nuevo")
    public String mostrarFormularioNuevo(
            @RequestParam(value = "idLanzamiento", required = false) Long idLanzamiento,
            Model model) {

        model.addAttribute("idLanzamiento", idLanzamiento);
        cargarDatosComunes(model);
        return "pg/estudiantes/formulario";
    }

    /**
     * Registra un nuevo estudiante (conversión desde admitido)
     */
    @PostMapping("/estudiantes/guardar")
    public String guardarEstudiante(
            @RequestParam("idPostulante") Long idPostulante,
            @RequestParam("idLanzamiento") Long idLanzamiento,
            @RequestParam(value = "codigoEstudiante", required = false) String codigoEstudiante,
            RedirectAttributes redirectAttributes) {

        try {
            estudianteService.registrarEstudiante(idPostulante, idLanzamiento, codigoEstudiante);
            redirectAttributes.addFlashAttribute("mensaje", "Estudiante registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar estudiante: " + e.getMessage());
        }
        return "redirect:/pg/estudiantes?idPrograma=" + idLanzamiento;
    }

    /**
     * Muestra el formulario para editar un estudiante
     */
    @GetMapping("/estudiantes/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("estudiante", estudianteService.buscarEstudiantePorId(id));
        cargarDatosComunes(model);
        return "pg/estudiantes/formulario";
    }

    /**
     * Actualiza los datos de un estudiante
     */
    @PostMapping("/estudiantes/actualizar")
    public String actualizarEstudiante(
            @RequestParam("id") Long id,
            @RequestParam(value = "codigoEstudiante", required = false) String codigoEstudiante,
            @RequestParam(value = "estado", required = false) String estado,
            RedirectAttributes redirectAttributes) {

        try {
            estudianteService.actualizarEstudiante(id, codigoEstudiante, estado);
            redirectAttributes.addFlashAttribute("mensaje", "Estudiante actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar estudiante");
        }
        return "redirect:/pg/estudiantes";
    }

    /**
     * Da de baja a un estudiante
     */
    @PostMapping("/estudiantes/baja")
    public String darDeBaja(
            @RequestParam("id") Long id,
            @RequestParam(value = "motivo", required = false) String motivo,
            RedirectAttributes redirectAttributes) {

        try {
            estudianteService.darDeBaja(id, motivo);
            redirectAttributes.addFlashAttribute("mensaje", "Estudiante dado de baja");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al dar de baja al estudiante");
        }
        return "redirect:/pg/estudiantes";
    }

    /**
     * Muestra el historial académico de un estudiante
     */
    @GetMapping("/estudiantes/historial")
    public String verHistorialAcademico(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("estudiante", estudianteService.buscarEstudiantePorId(id));
        model.addAttribute("historial", estudianteService.obtenerHistorialAcademico(id));
        return "pg/estudiantes/historial";
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("programas", estudianteService.listarProgramasActivos());
        model.addAttribute("estadosEstudiante", estudianteService.listarEstadosEstudiante());
        model.addAttribute("admitidos", estudianteService.listarAdmitidosPendientes());
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
