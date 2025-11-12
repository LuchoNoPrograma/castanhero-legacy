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
 * Controlador para la gestión de contratados (proveedores/consultores)
 * Incluye: registro de empresas y personas contratadas
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/sicoes/contratados")
@Slf4j
@RequiredArgsConstructor
public class ContratadosController {

    private final SicoesService sicoesService;

    /**
     * Lista todos los contratados
     */
    @GetMapping("")
    public String listarContratados(
            @RequestParam(value = "tipo", required = false) String tipo,
            Model model,
            HttpSession session) {

        Long idSisAdministrador = obtenerIdSisAdministrador(session);

        if (tipo != null && !tipo.isEmpty()) {
            model.addAttribute("contratados", sicoesService.listarContratadosPorTipo(tipo, idSisAdministrador));
        } else {
            model.addAttribute("contratados", sicoesService.listarTodosContratados(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "sicoes/contratados/listar";
    }

    /**
     * Muestra el formulario para registrar un nuevo contratado
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        cargarDatosComunes(model);
        return "sicoes/contratados/formulario";
    }

    /**
     * Guarda un nuevo contratado
     */
    @PostMapping("/guardar")
    public String guardarContratado(
            @RequestParam("nombre") String nombre,
            @RequestParam("tipo") String tipo,
            @RequestParam("nit") String nit,
            @RequestParam(value = "direccion", required = false) String direccion,
            @RequestParam(value = "telefono", required = false) String telefono,
            @RequestParam(value = "email", required = false) String email,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            sicoesService.registrarContratado(
                    nombre, tipo, nit, direccion, telefono, email, idSisAdministrador);
            redirectAttributes.addFlashAttribute("mensaje", "Contratado registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar contratado: " + e.getMessage());
        }
        return "redirect:/sicoes/contratados";
    }

    /**
     * Muestra el formulario para editar un contratado
     */
    @GetMapping("/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("contratado", sicoesService.buscarContratadoPorId(id));
        model.addAttribute("contrataciones", sicoesService.listarContratacionesPorContratado(id));
        cargarDatosComunes(model);
        return "sicoes/contratados/formulario";
    }

    /**
     * Actualiza un contratado existente
     */
    @PostMapping("/actualizar")
    public String actualizarContratado(
            @RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "direccion", required = false) String direccion,
            @RequestParam(value = "telefono", required = false) String telefono,
            @RequestParam(value = "email", required = false) String email,
            RedirectAttributes redirectAttributes) {

        try {
            sicoesService.actualizarContratado(id, nombre, direccion, telefono, email);
            redirectAttributes.addFlashAttribute("mensaje", "Contratado actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar contratado");
        }
        return "redirect:/sicoes/contratados";
    }

    /**
     * Inhabilita un contratado
     */
    @PostMapping("/inhabilitar")
    public String inhabilitarContratado(
            @RequestParam("id") Long id,
            @RequestParam(value = "motivo", required = false) String motivo,
            RedirectAttributes redirectAttributes) {

        try {
            sicoesService.inhabilitarContratado(id, motivo);
            redirectAttributes.addFlashAttribute("mensaje", "Contratado inhabilitado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al inhabilitar contratado");
        }
        return "redirect:/sicoes/contratados";
    }

    /**
     * Habilita un contratado previamente inhabilitado
     */
    @PostMapping("/habilitar")
    public String habilitarContratado(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            sicoesService.habilitarContratado(id);
            redirectAttributes.addFlashAttribute("mensaje", "Contratado habilitado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al habilitar contratado");
        }
        return "redirect:/sicoes/contratados";
    }

    /**
     * Muestra el historial de contrataciones de un contratado
     */
    @GetMapping("/historial")
    public String verHistorial(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("contratado", sicoesService.buscarContratadoPorId(id));
        model.addAttribute("historial", sicoesService.obtenerHistorialContrataciones(id));
        return "sicoes/contratados/historial";
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("tiposContratado", sicoesService.listarTiposContratado());
        model.addAttribute("estadosContratado", sicoesService.listarEstadosContratado());
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
