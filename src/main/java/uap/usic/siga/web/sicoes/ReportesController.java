package uap.usic.siga.web.sicoes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.service.sicoes.SicoesService;

/**
 * Controlador para la generación de reportes SICOES
 * Incluye: reportes por proyectos, contratados, unidades, direcciones y modalidades
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/sicoes/reportes")
public class ReportesController {

    @Autowired
    private SicoesService sicoesService;

    /**
     * Muestra el panel principal de reportes
     */
    @GetMapping("")
    public String mostrarPanelReportes(Model model, HttpSession session) {
        Long idSisAdministrador = obtenerIdSisAdministrador(session);

        model.addAttribute("proyectos", sicoesService.listarProyectosActivos());
        model.addAttribute("unidades", sicoesService.listarUnidades());
        model.addAttribute("direcciones", sicoesService.listarDirecciones());
        model.addAttribute("modalidades", sicoesService.listarModalidadesContratacion());
        model.addAttribute("gestiones", sicoesService.listarGestionesDisponibles());

        return "sicoes/reportes/panel";
    }

    /**
     * Genera reporte de contrataciones por proyecto
     */
    @GetMapping("/por-proyecto")
    public String generarReportePorProyecto(
            @RequestParam("idProyecto") Long idProyecto,
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model) {

        try {
            model.addAttribute("proyecto", sicoesService.buscarProyectoPorId(idProyecto));
            model.addAttribute("contrataciones", sicoesService.listarContratacionesPorProyecto(idProyecto));
            model.addAttribute("estadisticas", sicoesService.obtenerEstadisticasProyecto(idProyecto));
            return "sicoes/reportes/por-proyecto";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte: " + e.getMessage());
            return "sicoes/reportes/panel";
        }
    }

    /**
     * Genera reporte de contrataciones por contratado
     */
    @GetMapping("/por-contratado")
    public String generarReportePorContratado(
            @RequestParam(value = "idContratado", required = false) Long idContratado,
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model,
            HttpSession session) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);

            if (idContratado != null) {
                model.addAttribute("contratado", sicoesService.buscarContratadoPorId(idContratado));
                model.addAttribute("contrataciones", sicoesService.listarContratacionesPorContratado(idContratado));
            } else {
                model.addAttribute("contratados", sicoesService.listarTodosContratados(idSisAdministrador));
            }

            return "sicoes/reportes/por-contratado";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte");
            return "sicoes/reportes/panel";
        }
    }

    /**
     * Genera reporte de contrataciones por unidad
     */
    @GetMapping("/por-unidad")
    public String generarReportePorUnidad(
            @RequestParam(value = "idUnidad", required = false) Long idUnidad,
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model,
            HttpSession session) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);

            if (idUnidad != null) {
                model.addAttribute("unidad", sicoesService.buscarUnidadPorId(idUnidad));
                model.addAttribute("proyectos", sicoesService.listarProyectosPorUnidad(String.valueOf(idUnidad), idSisAdministrador));
            }

            model.addAttribute("estadisticas", sicoesService.obtenerEstadisticasPorUnidad(idUnidad, gestion));
            return "sicoes/reportes/por-unidad";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte");
            return "sicoes/reportes/panel";
        }
    }

    /**
     * Genera reporte de contrataciones por dirección
     */
    @GetMapping("/por-direccion")
    public String generarReportePorDireccion(
            @RequestParam(value = "idDireccion", required = false) Long idDireccion,
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model,
            HttpSession session) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);

            if (idDireccion != null) {
                model.addAttribute("direccion", sicoesService.buscarDireccionPorId(idDireccion));
                model.addAttribute("proyectos", sicoesService.listarProyectosPorDireccion(idDireccion, idSisAdministrador));
            }

            model.addAttribute("estadisticas", sicoesService.obtenerEstadisticasPorDireccion(idDireccion, gestion));
            return "sicoes/reportes/por-direccion";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte");
            return "sicoes/reportes/panel";
        }
    }

    /**
     * Genera reporte de contrataciones por modalidad
     */
    @GetMapping("/por-modalidad")
    public String generarReportePorModalidad(
            @RequestParam(value = "modalidad", required = false) String modalidad,
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model,
            HttpSession session) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);

            if (modalidad != null && !modalidad.isEmpty()) {
                model.addAttribute("contrataciones", sicoesService.listarContratacionesPorModalidad(modalidad, idSisAdministrador));
                model.addAttribute("modalidadSeleccionada", modalidad);
            }

            model.addAttribute("estadisticas", sicoesService.obtenerEstadisticasPorModalidad(modalidad, gestion));
            return "sicoes/reportes/por-modalidad";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte");
            return "sicoes/reportes/panel";
        }
    }

    /**
     * Genera reporte consolidado general
     */
    @GetMapping("/consolidado")
    public String generarReporteConsolidado(
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model,
            HttpSession session) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            model.addAttribute("estadisticas", sicoesService.obtenerEstadisticasGenerales(gestion, idSisAdministrador));
            return "sicoes/reportes/consolidado";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte consolidado");
            return "sicoes/reportes/panel";
        }
    }

    /**
     * Exporta reporte a Excel
     */
    @GetMapping("/exportar")
    public void exportarReporte(
            @RequestParam("tipo") String tipo,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "gestion", required = false) String gestion,
            HttpServletResponse response,
            HttpSession session) {

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=reporte_sicoes_" + tipo + ".xlsx");

            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            sicoesService.exportarReporte(tipo, id, gestion, idSisAdministrador, response);

        } catch (Exception e) {
            // Log error
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
