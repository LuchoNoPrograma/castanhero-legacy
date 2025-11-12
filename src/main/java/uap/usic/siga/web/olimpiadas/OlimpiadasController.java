package uap.usic.siga.web.olimpiadas;

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

import uap.usic.siga.service.olimpiadas.OlimpiadasService;

/**
 * Controlador para la gestión de olimpiadas y competencias académicas
 * Incluye: enfrentamientos, puntuaciones, equipos y participantes
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/olimpiadas")
@Slf4j
@RequiredArgsConstructor
public class OlimpiadasController {

    private final OlimpiadasService olimpiadasService;

    /**
     * Lista todas las olimpiadas
     */
    @GetMapping("")
    public String listarOlimpiadas(
            @RequestParam(value = "gestion", required = false) String gestion,
            Model model,
            HttpSession session) {

        Long idSisAdministrador = obtenerIdSisAdministrador(session);

        if (gestion != null && !gestion.isEmpty()) {
            model.addAttribute("olimpiadas", olimpiadasService.listarOlimpiadasPorGestion(gestion, idSisAdministrador));
        } else {
            model.addAttribute("olimpiadas", olimpiadasService.listarTodasOlimpiadas(idSisAdministrador));
        }

        cargarDatosComunes(model);
        return "olimpiadas/listar";
    }

    /**
     * Muestra el formulario para crear una nueva olimpiada
     */
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        cargarDatosComunes(model);
        return "olimpiadas/formulario";
    }

    /**
     * Guarda una nueva olimpiada
     */
    @PostMapping("/guardar")
    public String guardarOlimpiada(
            @RequestParam("nombre") String nombre,
            @RequestParam("gestion") String gestion,
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            Long idSisAdministrador = obtenerIdSisAdministrador(session);
            olimpiadasService.crearOlimpiada(nombre, gestion, fechaInicio, fechaFin, descripcion, idSisAdministrador);
            redirectAttributes.addFlashAttribute("mensaje", "Olimpiada creada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear olimpiada: " + e.getMessage());
        }
        return "redirect:/olimpiadas";
    }

    // ========== Enfrentamientos ==========

    /**
     * Lista los enfrentamientos de una olimpiada
     */
    @GetMapping("/enfrentamientos")
    public String listarEnfrentamientos(
            @RequestParam("idOlimpiada") Long idOlimpiada,
            Model model) {

        model.addAttribute("olimpiada", olimpiadasService.buscarOlimpiadaPorId(idOlimpiada));
        model.addAttribute("enfrentamientos", olimpiadasService.listarEnfrentamientosPorOlimpiada(idOlimpiada));
        return "olimpiadas/enfrentamientos/listar";
    }

    /**
     * Crea un nuevo enfrentamiento
     */
    @PostMapping("/enfrentamientos/guardar")
    public String guardarEnfrentamiento(
            @RequestParam("idOlimpiada") Long idOlimpiada,
            @RequestParam("idEquipo1") Long idEquipo1,
            @RequestParam("idEquipo2") Long idEquipo2,
            @RequestParam("fecha") String fecha,
            @RequestParam(value = "ronda", required = false) String ronda,
            RedirectAttributes redirectAttributes) {

        try {
            olimpiadasService.crearEnfrentamiento(idOlimpiada, idEquipo1, idEquipo2, fecha, ronda);
            redirectAttributes.addFlashAttribute("mensaje", "Enfrentamiento creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear enfrentamiento: " + e.getMessage());
        }
        return "redirect:/olimpiadas/enfrentamientos?idOlimpiada=" + idOlimpiada;
    }

    /**
     * Registra el resultado de un enfrentamiento
     */
    @PostMapping("/enfrentamientos/resultado")
    public String registrarResultado(
            @RequestParam("idEnfrentamiento") Long idEnfrentamiento,
            @RequestParam("puntajeEquipo1") Integer puntajeEquipo1,
            @RequestParam("puntajeEquipo2") Integer puntajeEquipo2,
            @RequestParam("idOlimpiada") Long idOlimpiada,
            RedirectAttributes redirectAttributes) {

        try {
            olimpiadasService.registrarResultadoEnfrentamiento(idEnfrentamiento, puntajeEquipo1, puntajeEquipo2);
            redirectAttributes.addFlashAttribute("mensaje", "Resultado registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar resultado");
        }
        return "redirect:/olimpiadas/enfrentamientos?idOlimpiada=" + idOlimpiada;
    }

    // ========== Puntuaciones ==========

    /**
     * Muestra la tabla de puntuaciones de una olimpiada
     */
    @GetMapping("/puntuaciones")
    public String mostrarPuntuaciones(
            @RequestParam("idOlimpiada") Long idOlimpiada,
            Model model) {

        model.addAttribute("olimpiada", olimpiadasService.buscarOlimpiadaPorId(idOlimpiada));
        model.addAttribute("puntuaciones", olimpiadasService.obtenerTablaPuntuaciones(idOlimpiada));
        model.addAttribute("equipos", olimpiadasService.listarEquiposPorOlimpiada(idOlimpiada));
        return "olimpiadas/puntuaciones/tabla";
    }

    /**
     * Actualiza la puntuación de un equipo
     */
    @PostMapping("/puntuaciones/actualizar")
    public String actualizarPuntuacion(
            @RequestParam("idEquipo") Long idEquipo,
            @RequestParam("idOlimpiada") Long idOlimpiada,
            @RequestParam("puntos") Integer puntos,
            @RequestParam(value = "ajuste", required = false) String ajuste,
            RedirectAttributes redirectAttributes) {

        try {
            olimpiadasService.actualizarPuntuacionEquipo(idEquipo, idOlimpiada, puntos, ajuste);
            redirectAttributes.addFlashAttribute("mensaje", "Puntuación actualizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar puntuación");
        }
        return "redirect:/olimpiadas/puntuaciones?idOlimpiada=" + idOlimpiada;
    }

    // ========== Equipos ==========

    /**
     * Lista los equipos de una olimpiada
     */
    @GetMapping("/equipos")
    public String listarEquipos(
            @RequestParam("idOlimpiada") Long idOlimpiada,
            Model model) {

        model.addAttribute("olimpiada", olimpiadasService.buscarOlimpiadaPorId(idOlimpiada));
        model.addAttribute("equipos", olimpiadasService.listarEquiposPorOlimpiada(idOlimpiada));
        return "olimpiadas/equipos/listar";
    }

    /**
     * Registra un nuevo equipo en la olimpiada
     */
    @PostMapping("/equipos/guardar")
    public String guardarEquipo(
            @RequestParam("idOlimpiada") Long idOlimpiada,
            @RequestParam("nombre") String nombre,
            @RequestParam("institucion") String institucion,
            RedirectAttributes redirectAttributes) {

        try {
            olimpiadasService.registrarEquipo(idOlimpiada, nombre, institucion);
            redirectAttributes.addFlashAttribute("mensaje", "Equipo registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar equipo");
        }
        return "redirect:/olimpiadas/equipos?idOlimpiada=" + idOlimpiada;
    }

    /**
     * Carga datos comunes necesarios en las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("gestiones", olimpiadasService.listarGestionesDisponibles());
        model.addAttribute("estados", olimpiadasService.listarEstadosOlimpiada());
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
