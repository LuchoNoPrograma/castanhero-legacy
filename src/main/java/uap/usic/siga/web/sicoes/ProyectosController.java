package uap.usic.siga.web.sicoes;

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

import uap.usic.siga.service.sicoes.SicoesService;

/**
 * Controlador para la gestión de proyectos SICOES
 * Incluye: registro, seguimiento y administración de proyectos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/sicoes/proyectos")
@Slf4j
@RequiredArgsConstructor
public class ProyectosController {

    private final SicoesService sicoesService;

    /**
     * Lista todos los proyectos
     */
    @GetMapping("")
    public String listarProyectos(
            @RequestParam(value = "gestion", required = false) String gestion,
            @RequestParam(value = "unidad", required = false) String unidad,
            Model model,
            HttpSession session) {

        Long idSisAdministrador = obtenerIdSisAdministrador(session);

        if (gestion != null && !gestion.isEmpty()) {
            model.addAttribute("proyectos", sicoesService.listarProyectosPorGestion(gestion, idSisAdministrador));
        } else if (unidad != null && !unidad.isEmpty()) {
            model.addAttribute("proyectos", sicoesService.listarProyectosPorUnidad(unidad, idSisAdministrador));
        } else {
            model.addAttribute("proyectos", sicoesService.listarTodosProyectos(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "sicoes/proyectos/listar";
    }

    /**
     * Muestra el formulario para crear un nuevo proyecto
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        cargarDatosComunes(model);
        return "sicoes/proyectos/formulario";
    }

    /**
     * Guarda un nuevo proyecto
     */
    @PostMapping("/guardar")
    public String guardarProyecto(
            @RequestParam("nombre") String nombre,
            @RequestParam("codigo") String codigo,
            @RequestParam("idUnidad") Long idUnidad,
            @RequestParam("idDireccion") Long idDireccion,
            @RequestParam("presupuesto") Double presupuesto,
            @RequestParam("gestion") String gestion,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            sicoesService.registrarProyecto(
                    nombre, codigo, idUnidad, idDireccion, presupuesto,
                    gestion, descripcion, idSisAdministrador);
            redirectAttributes.addFlashAttribute("mensaje", "Proyecto registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar proyecto: " + e.getMessage());
        }
        return "redirect:/sicoes/proyectos";
    }

    /**
     * Muestra el formulario para editar un proyecto
     */
    @GetMapping("/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("proyecto", sicoesService.buscarProyectoPorId(id));
        model.addAttribute("contrataciones", sicoesService.listarContratacionesPorProyecto(id));
        cargarDatosComunes(model);
        return "sicoes/proyectos/formulario";
    }

    /**
     * Actualiza un proyecto existente
     */
    @PostMapping("/actualizar")
    public String actualizarProyecto(
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("presupuesto") Double presupuesto,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "estado", required = false) String estado,
            RedirectAttributes redirectAttributes) {

        try {
            sicoesService.actualizarProyecto(id, nombre, presupuesto, descripcion, estado);
            redirectAttributes.addFlashAttribute("mensaje", "Proyecto actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar proyecto");
        }
        return "redirect:/sicoes/proyectos";
    }

    /**
     * Cierra un proyecto
     */
    @PostMapping("/cerrar")
    public String cerrarProyecto(
            @RequestParam("id") Long id,
            @RequestParam(value = "observaciones", required = false) String observaciones,
            RedirectAttributes redirectAttributes) {

        try {
            sicoesService.cerrarProyecto(id, observaciones);
            redirectAttributes.addFlashAttribute("mensaje", "Proyecto cerrado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al cerrar proyecto");
        }
        return "redirect:/sicoes/proyectos";
    }

    /**
     * Muestra el detalle y avance de un proyecto
     */
    @GetMapping("/detalle")
    public String verDetalleProyecto(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("proyecto", sicoesService.buscarProyectoPorId(id));
        model.addAttribute("contrataciones", sicoesService.listarContratacionesPorProyecto(id));
        model.addAttribute("estadisticas", sicoesService.obtenerEstadisticasProyecto(id));
        return "sicoes/proyectos/detalle";
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("unidades", sicoesService.listarUnidades());
        model.addAttribute("direcciones", sicoesService.listarDirecciones());
        model.addAttribute("estadosProyecto", sicoesService.listarEstadosProyecto());
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
