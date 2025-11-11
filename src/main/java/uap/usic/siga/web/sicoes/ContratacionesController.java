package uap.usic.siga.web.sicoes;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.service.sicoes.SicoesService;

/**
 * Controlador para la gestión de contrataciones SICOES
 * Incluye: registro, seguimiento y administración de contrataciones
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/sicoes/contrataciones")
public class ContratacionesController {

    @Autowired
    private SicoesService sicoesService;

    /**
     * Lista todas las contrataciones
     */
    @GetMapping("")
    public String listarContrataciones(
            @RequestParam(value = "gestion", required = false) String gestion,
            @RequestParam(value = "modalidad", required = false) String modalidad,
            Model model,
            HttpSession session) {

        Long idSisAdministrador = obtenerIdSisAdministrador(session);

        if (gestion != null && !gestion.isEmpty()) {
            model.addAttribute("contrataciones", sicoesService.listarContratacionesPorGestion(gestion, idSisAdministrador));
        } else if (modalidad != null && !modalidad.isEmpty()) {
            model.addAttribute("contrataciones", sicoesService.listarContratacionesPorModalidad(modalidad, idSisAdministrador));
        } else {
            model.addAttribute("contrataciones", sicoesService.listarTodasContrataciones(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "sicoes/contrataciones/listar";
    }

    /**
     * Muestra el formulario para registrar una nueva contratación
     */
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        cargarDatosComunes(model);
        return "sicoes/contrataciones/formulario";
    }

    /**
     * Guarda una nueva contratación
     */
    @PostMapping("/guardar")
    public String guardarContratacion(
            @RequestParam("idProyecto") Long idProyecto,
            @RequestParam("numeroContrato") String numeroContrato,
            @RequestParam("objeto") String objeto,
            @RequestParam("modalidad") String modalidad,
            @RequestParam("montoEstimado") Double montoEstimado,
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam(value = "fechaFin", required = false) String fechaFin,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            sicoesService.registrarContratacion(
                    idProyecto, numeroContrato, objeto, modalidad, montoEstimado,
                    fechaInicio, fechaFin, idSisAdministrador);
            redirectAttributes.addFlashAttribute("mensaje", "Contratación registrada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar contratación: " + e.getMessage());
        }
        return "redirect:/sicoes/contrataciones";
    }

    /**
     * Muestra el formulario para editar una contratación
     */
    @GetMapping("/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("contratacion", sicoesService.buscarContratacionPorId(id));
        cargarDatosComunes(model);
        return "sicoes/contrataciones/formulario";
    }

    /**
     * Actualiza una contratación existente
     */
    @PostMapping("/actualizar")
    public String actualizarContratacion(
            @RequestParam("id") Long id,
            @RequestParam("objeto") String objeto,
            @RequestParam("montoEstimado") Double montoEstimado,
            @RequestParam(value = "fechaFin", required = false) String fechaFin,
            @RequestParam(value = "estado", required = false) String estado,
            RedirectAttributes redirectAttributes) {

        try {
            sicoesService.actualizarContratacion(id, objeto, montoEstimado, fechaFin, estado);
            redirectAttributes.addFlashAttribute("mensaje", "Contratación actualizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar contratación");
        }
        return "redirect:/sicoes/contrataciones";
    }

    /**
     * Elimina una contratación
     */
    @PostMapping("/eliminar")
    public String eliminarContratacion(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            sicoesService.eliminarContratacion(id);
            redirectAttributes.addFlashAttribute("mensaje", "Contratación eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar contratación");
        }
        return "redirect:/sicoes/contrataciones";
    }

    /**
     * Aprueba una contratación
     */
    @PostMapping("/aprobar")
    public String aprobarContratacion(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            sicoesService.aprobarContratacion(id);
            redirectAttributes.addFlashAttribute("mensaje", "Contratación aprobada");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al aprobar contratación");
        }
        return "redirect:/sicoes/contrataciones/editar?id=" + id;
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("proyectos", sicoesService.listarProyectosActivos());
        model.addAttribute("modalidades", sicoesService.listarModalidadesContratacion());
        model.addAttribute("estados", sicoesService.listarEstadosContratacion());
        model.addAttribute("gestiones", sicoesService.listarGestionesDisponibles());
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
