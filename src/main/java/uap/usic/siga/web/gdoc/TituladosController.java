package uap.usic.siga.web.gdoc;

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

import uap.usic.siga.service.gdoc.GdocTituladosService;
import uap.usic.siga.service.gdoc.GdocTitulosService;

/**
 * Controlador para la gestión de titulados y títulos universitarios
 * Incluye: registro de graduados, emisión y seguimiento de títulos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/gdoc/titulados")
@Slf4j
@RequiredArgsConstructor
public class TituladosController {

    private final GdocTituladosService tituladosService;

    private final GdocTitulosService titulosService;

    /**
     * Lista todos los titulados
     */
    @GetMapping("")
    public String listarTitulados(
            @RequestParam(value = "gestion", required = false) String gestion,
            @RequestParam(value = "programa", required = false) String programa,
            Model model,
            HttpSession session) {

        Long idSisAdministrador = obtenerIdSisAdministrador(session);

        if (gestion != null && !gestion.isEmpty()) {
            model.addAttribute("titulados", tituladosService.listarTituladosPorGestion(gestion, idSisAdministrador));
        } else if (programa != null && !programa.isEmpty()) {
            model.addAttribute("titulados", tituladosService.listarTituladosPorPrograma(programa, idSisAdministrador));
        } else {
            model.addAttribute("titulados", tituladosService.listarTodosTitulados(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "gdoc/titulados/listar";
    }

    /**
     * Muestra el formulario para registrar un nuevo titulado
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        cargarDatosComunes(model);
        return "gdoc/titulados/formulario";
    }

    /**
     * Registra un nuevo titulado
     */
    @PostMapping("/guardar")
    public String guardarTitulado(
            @RequestParam("idPersona") Long idPersona,
            @RequestParam("idPrograma") Long idPrograma,
            @RequestParam("fechaGraduacion") String fechaGraduacion,
            @RequestParam(value = "modalidadGraduacion", required = false) String modalidadGraduacion,
            @RequestParam(value = "notaFinal", required = false) Double notaFinal,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            tituladosService.registrarTitulado(
                    idPersona, idPrograma, fechaGraduacion, modalidadGraduacion, notaFinal, idSisAdministrador);
            redirectAttributes.addFlashAttribute("mensaje", "Titulado registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar titulado: " + e.getMessage());
        }
        return "redirect:/gdoc/titulados";
    }

    /**
     * Muestra el formulario para editar un titulado
     */
    @GetMapping("/editar")
    public String mostrarFormularioEditar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("titulado", tituladosService.buscarTituladoPorId(id));
        model.addAttribute("titulos", titulosService.listarTitulosPorTitulado(id));
        cargarDatosComunes(model);
        return "gdoc/titulados/formulario";
    }

    /**
     * Actualiza un titulado existente
     */
    @PostMapping("/actualizar")
    public String actualizarTitulado(
            @RequestParam("id") Long id,
            @RequestParam(value = "modalidadGraduacion", required = false) String modalidadGraduacion,
            @RequestParam(value = "notaFinal", required = false) Double notaFinal,
            RedirectAttributes redirectAttributes) {

        try {
            tituladosService.actualizarTitulado(id, modalidadGraduacion, notaFinal);
            redirectAttributes.addFlashAttribute("mensaje", "Titulado actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar titulado");
        }
        return "redirect:/gdoc/titulados";
    }

    // ========== Gestión de Títulos ==========

    /**
     * Lista todos los títulos emitidos
     */
    @GetMapping("/titulos")
    public String listarTitulos(
            @RequestParam(value = "estado", required = false) String estado,
            Model model,
            HttpSession session) {

        Long idSisAdministrador = obtenerIdSisAdministrador(session);

        if (estado != null && !estado.isEmpty()) {
            model.addAttribute("titulos", titulosService.listarTitulosPorEstado(estado, idSisAdministrador));
        } else {
            model.addAttribute("titulos", titulosService.listarTodosTitulos(idSisAdministrador));
        }

        return "gdoc/titulados/titulos";
    }

    /**
     * Registra la emisión de un nuevo título
     */
    @PostMapping("/titulos/emitir")
    public String emitirTitulo(
            @RequestParam("idTitulado") Long idTitulado,
            @RequestParam("numeroTitulo") String numeroTitulo,
            @RequestParam("fechaEmision") String fechaEmision,
            @RequestParam(value = "numeroRegistro", required = false) String numeroRegistro,
            RedirectAttributes redirectAttributes) {

        try {
            titulosService.emitirTitulo(idTitulado, numeroTitulo, fechaEmision, numeroRegistro);
            redirectAttributes.addFlashAttribute("mensaje", "Título emitido exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al emitir título: " + e.getMessage());
        }
        return "redirect:/gdoc/titulados/editar?id=" + idTitulado;
    }

    /**
     * Registra la entrega de un título
     */
    @PostMapping("/titulos/entregar")
    public String entregarTitulo(
            @RequestParam("idTitulo") Long idTitulo,
            @RequestParam("fechaEntrega") String fechaEntrega,
            @RequestParam(value = "receptor", required = false) String receptor,
            RedirectAttributes redirectAttributes) {

        try {
            titulosService.registrarEntregaTitulo(idTitulo, fechaEntrega, receptor);
            redirectAttributes.addFlashAttribute("mensaje", "Entrega de título registrada");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar entrega");
        }
        return "redirect:/gdoc/titulados/titulos";
    }

    /**
     * Anula un título
     */
    @PostMapping("/titulos/anular")
    public String anularTitulo(
            @RequestParam("idTitulo") Long idTitulo,
            @RequestParam("motivo") String motivo,
            RedirectAttributes redirectAttributes) {

        try {
            titulosService.anularTitulo(idTitulo, motivo);
            redirectAttributes.addFlashAttribute("mensaje", "Título anulado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al anular título");
        }
        return "redirect:/gdoc/titulados/titulos";
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("programas", tituladosService.listarProgramasDisponibles());
        model.addAttribute("modalidadesGraduacion", tituladosService.listarModalidadesGraduacion());
        model.addAttribute("personas", tituladosService.listarPersonasDisponibles());
        model.addAttribute("gestiones", tituladosService.listarGestionesDisponibles());
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
