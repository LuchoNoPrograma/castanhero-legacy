package uap.usic.siga.web.compartido;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.servicios.InstitucionesServicios;

/**
 * Controlador para la gestión de instituciones y unidades educativas
 * Incluye: colegios, universidades y centros educativos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/instituciones")
@Slf4j
@RequiredArgsConstructor
public class InstitucionesController {

    private final InstitucionesServicios institucionesServicios;

    /**
     * Lista todas las instituciones
     */
    @GetMapping("")
    public String listarInstituciones(
            @RequestParam(value = "tipo", required = false) String tipo,
            Model model) {

        if (tipo != null && !tipo.isEmpty()) {
            model.addAttribute("instituciones", institucionesServicios.listarInstitucionesPorTipo(tipo));
        } else {
            model.addAttribute("instituciones", institucionesServicios.listarTodasInstituciones());
        }

        model.addAttribute("tiposInstitucion", institucionesServicios.listarTiposInstitucion());
        return "instituciones/listar";
    }

    /**
     * Muestra el formulario para registrar una nueva institución
     */
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("tiposInstitucion", institucionesServicios.listarTiposInstitucion());
        model.addAttribute("departamentos", institucionesServicios.listarDepartamentos());
        return "instituciones/formulario";
    }

    /**
     * Guarda una nueva institución
     */
    @PostMapping("/guardar")
    public String guardarInstitucion(
            @RequestParam("nombre") String nombre,
            @RequestParam("tipo") String tipo,
            @RequestParam(value = "direccion", required = false) String direccion,
            @RequestParam(value = "telefono", required = false) String telefono,
            @RequestParam(value = "departamento", required = false) String departamento,
            RedirectAttributes redirectAttributes) {

        try {
            institucionesServicios.guardarInstitucion(nombre, tipo, direccion, telefono, departamento);
            redirectAttributes.addFlashAttribute("mensaje", "Institución registrada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar institución: " + e.getMessage());
        }
        return "redirect:/instituciones";
    }

    /**
     * Muestra el formulario para editar una institución
     */
    @GetMapping("/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("institucion", institucionesServicios.buscarInstitucionPorId(id));
        model.addAttribute("tiposInstitucion", institucionesServicios.listarTiposInstitucion());
        model.addAttribute("departamentos", institucionesServicios.listarDepartamentos());
        return "instituciones/formulario";
    }

    /**
     * Actualiza una institución existente
     */
    @PostMapping("/actualizar")
    public String actualizarInstitucion(
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "direccion", required = false) String direccion,
            @RequestParam(value = "telefono", required = false) String telefono,
            RedirectAttributes redirectAttributes) {

        try {
            institucionesServicios.actualizarInstitucion(id, nombre, direccion, telefono);
            redirectAttributes.addFlashAttribute("mensaje", "Institución actualizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar institución");
        }
        return "redirect:/instituciones";
    }

    /**
     * Elimina una institución
     */
    @PostMapping("/eliminar")
    public String eliminarInstitucion(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            institucionesServicios.eliminarInstitucion(id);
            redirectAttributes.addFlashAttribute("mensaje", "Institución eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar institución");
        }
        return "redirect:/instituciones";
    }
}
