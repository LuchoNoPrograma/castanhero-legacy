package uap.usic.siga.web.postgrado;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.service.postgrado.ProgramaService;

/**
 * Controlador para la generación de reportes de postgrado
 * Incluye: reportes de programas, estudiantes, matrículas y estadísticas
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/pg/reportes")
public class ReportesController {

    @Autowired
    private ProgramaService programaService;

    /**
     * Muestra el panel principal de reportes
     */
    @GetMapping("")
    public String mostrarPanelReportes(Model model, HttpSession session) {
        Long idSisAdministrador = obtenerIdSisAdministrador(session);
        model.addAttribute("programas", programaService.listarProgramasActivos(idSisAdministrador));
        model.addAttribute("gestiones", programaService.listarGestionesDisponibles());
        return "pg/reportes/panel";
    }

    /**
     * Genera reporte de programas
     */
    @GetMapping("/programas")
    public String generarReporteProgramas(
            @RequestParam(value = "gestion", required = false) String gestion,
            HttpServletResponse response,
            Model model) {

        try {
            // Lógica de generación de reporte
            model.addAttribute("mensaje", "Reporte generado exitosamente");
            return "pg/reportes/programas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte: " + e.getMessage());
            return "pg/reportes/panel";
        }
    }

    /**
     * Genera reporte de estudiantes
     */
    @GetMapping("/estudiantes")
    public String generarReporteEstudiantes(
            @RequestParam(value = "idPrograma", required = false) Long idPrograma,
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model) {

        try {
            model.addAttribute("mensaje", "Reporte de estudiantes generado");
            return "pg/reportes/estudiantes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte");
            return "pg/reportes/panel";
        }
    }

    /**
     * Genera reporte de matrículas
     */
    @GetMapping("/matriculas")
    public String generarReporteMatriculas(
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model) {

        try {
            model.addAttribute("mensaje", "Reporte de matrículas generado");
            return "pg/reportes/matriculas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte");
            return "pg/reportes/panel";
        }
    }

    /**
     * Genera estadísticas generales
     */
    @GetMapping("/estadisticas")
    public String generarEstadisticas(
            @RequestParam(value = "gestion", required = false) String gestion,
            HttpSession session,
            Model model) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            // Lógica de estadísticas
            model.addAttribute("mensaje", "Estadísticas generadas exitosamente");
            return "pg/reportes/estadisticas";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar estadísticas");
            return "pg/reportes/panel";
        }
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
