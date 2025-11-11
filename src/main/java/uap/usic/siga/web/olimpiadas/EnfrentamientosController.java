package uap.usic.siga.web.olimpiadas;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.domain.olimpiadas.DetalleEnfrentamiento;
import uap.usic.siga.domain.olimpiadas.Enfrentamiento;
import uap.usic.siga.domain.olimpiadas.EquipoParticipante;
import uap.usic.siga.domain.olimpiadas.Etapa;
import uap.usic.siga.service.olimpiadas.OlimpiadasService;

/**
 * Controlador para la gestión de enfrentamientos de las olimpiadas
 * Maneja las operaciones CRUD de enfrentamientos y la visualización de resultados
 */
@Controller
@RequestMapping("/olimpiadas/enfrentamientos")
public class EnfrentamientosController {

    private static final Logger logger = LoggerFactory.getLogger(EnfrentamientosController.class);

    @Autowired
    private OlimpiadasService olimpiadasService;

    /**
     * Lista todas las etapas disponibles
     */
    @GetMapping("/etapas")
    public String listarEtapas(Model model) {
        model.addAttribute("busqueda", "etapa");
        model.addAttribute("etapas", olimpiadasService.listarEtapas());
        return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";
    }

    /**
     * Lista enfrentamientos de una etapa específica
     */
    @GetMapping("/etapas/{idEtapa}")
    public String listarEnfrentamientosPorEtapa(@PathVariable("idEtapa") Long idEtapa, Model model) {
        model.addAttribute("busqueda", "find");
        model.addAttribute("idEtapa", idEtapa);
        model.addAttribute("enfrentamientos", olimpiadasService.listarEnfrentamientosPorEtapa(idEtapa));

        // Log para debugging (puede removerse en producción)
        logger.debug("Listando enfrentamientos para la etapa ID: {}", idEtapa);

        return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";
    }

    /**
     * Muestra el formulario para crear un nuevo enfrentamiento
     */
    @PostMapping("/nuevo")
    public String mostrarFormularioNuevo(
            @ModelAttribute("enfrentamiento") Enfrentamiento enfrentamiento,
            @RequestParam("idEtapa") Long idEtapa,
            Model model) {

        model.addAttribute("operation", "reg");
        model.addAttribute("encuentros", olimpiadasService.listarEncuentros());
        model.addAttribute("equipos", olimpiadasService.listarEquiposParticipantes());
        model.addAttribute("equiposRivales", olimpiadasService.listarEquiposParticipantes());
        model.addAttribute("idEtapa", idEtapa);

        return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";
    }

    /**
     * Registra un nuevo enfrentamiento con sus equipos participantes
     */
    @PostMapping("/registrar")
    public String registrarEnfrentamiento(
            @Valid @ModelAttribute("enfrentamiento") Enfrentamiento enfrentamiento,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("operation", "reg");
            model.addAttribute("encuentros", olimpiadasService.listarEncuentros());
            model.addAttribute("equipos", olimpiadasService.listarEquiposParticipantes());
            model.addAttribute("idEtapa", enfrentamiento.getIdEtapa());
            return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";
        }

        try {
            logger.info("Iniciando registro de enfrentamiento");

            // Asignar etapa al enfrentamiento
            Etapa etapa = olimpiadasService.buscarEtapa(enfrentamiento.getIdEtapa());
            enfrentamiento.setEtapa(etapa);

            // Registrar enfrentamiento
            Enfrentamiento enfrentamientoRegistrado = olimpiadasService.registrarEnfrentamiento(enfrentamiento);
            logger.debug("Enfrentamiento registrado con ID: {}", enfrentamientoRegistrado.getIdEnfrentamiento());

            // Registrar detalle para el primer equipo
            EquipoParticipante equipo1 = olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipante());
            DetalleEnfrentamiento detalle1 = new DetalleEnfrentamiento(enfrentamientoRegistrado, equipo1);
            olimpiadasService.registrarDetalleEnfrentamiento(detalle1);
            logger.debug("Detalle registrado para equipo ID: {}", equipo1.getIdEquipoParticipante());

            // Registrar detalle para el equipo rival
            EquipoParticipante equipo2 = olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipanteRival());
            DetalleEnfrentamiento detalle2 = new DetalleEnfrentamiento(enfrentamientoRegistrado, equipo2);
            olimpiadasService.registrarDetalleEnfrentamiento(detalle2);
            logger.debug("Detalle registrado para equipo rival ID: {}", equipo2.getIdEquipoParticipante());

            redirectAttributes.addFlashAttribute("mensaje", "Enfrentamiento registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");

            return "redirect:/olimpiadas/enfrentamientos/etapas/" + etapa.getIdEtapa();

        } catch (Exception e) {
            logger.error("Error al registrar enfrentamiento", e);
            model.addAttribute("mensaje", "Error al registrar enfrentamiento: " + e.getMessage());
            model.addAttribute("tipoMensaje", "error");
            model.addAttribute("operation", "reg");
            model.addAttribute("encuentros", olimpiadasService.listarEncuentros());
            model.addAttribute("equipos", olimpiadasService.listarEquiposParticipantes());
            model.addAttribute("idEtapa", enfrentamiento.getIdEtapa());
            return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";
        }
    }

    /**
     * Muestra el detalle de un enfrentamiento con sus puntuaciones
     */
    @GetMapping("/{idEnfrentamiento}/detalle")
    public String verDetalleEnfrentamiento(@PathVariable("idEnfrentamiento") Long idEnfrentamiento, Model model) {
        try {
            Enfrentamiento enfrentamiento = olimpiadasService.buscarEnfrentamiento(idEnfrentamiento);

            // Cargar equipos
            EquipoParticipante equipo = olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipante());
            EquipoParticipante equipoRival = olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipanteRival());

            model.addAttribute("enfrentamiento", enfrentamiento);
            model.addAttribute("equipo", equipo);
            model.addAttribute("equipoRival", equipoRival);

            // Cargar puntuaciones de ambos equipos
            model.addAttribute("puntuacionesEquipo",
                olimpiadasService.listarPuntuacionesPorEquipoYEnfrentamiento(
                    enfrentamiento.getIdEquipoParticipante(), idEnfrentamiento));
            model.addAttribute("puntuacionesEquipoRival",
                olimpiadasService.listarPuntuacionesPorEquipoYEnfrentamiento(
                    enfrentamiento.getIdEquipoParticipanteRival(), idEnfrentamiento));

            // Calcular totales
            Integer totalEquipo = olimpiadasService.calcularTotalPuntos(
                enfrentamiento.getIdEquipoParticipante(), idEnfrentamiento);
            Integer totalEquipoRival = olimpiadasService.calcularTotalPuntos(
                enfrentamiento.getIdEquipoParticipanteRival(), idEnfrentamiento);

            model.addAttribute("totalEquipo", totalEquipo);
            model.addAttribute("totalEquipoRival", totalEquipoRival);

            return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/detalleEnfrentamiento";

        } catch (Exception e) {
            logger.error("Error al cargar detalle de enfrentamiento", e);
            model.addAttribute("mensaje", "Error al cargar el enfrentamiento: " + e.getMessage());
            model.addAttribute("tipoMensaje", "error");
            return "redirect:/olimpiadas/enfrentamientos/etapas";
        }
    }

    /**
     * Muestra la vista del panel de encuentros (pantalla pública)
     */
    @GetMapping("/{idEnfrentamiento}/panel")
    public String mostrarPanelEncuentro(@PathVariable("idEnfrentamiento") Long idEnfrentamiento, Model model) {
        try {
            Enfrentamiento enfrentamiento = olimpiadasService.buscarEnfrentamiento(idEnfrentamiento);

            // Cargar equipos
            EquipoParticipante equipo = olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipante());
            EquipoParticipante equipoRival = olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipanteRival());

            model.addAttribute("equipo", equipo);
            model.addAttribute("equipoRival", equipoRival);

            // Cargar puntuaciones de ambos equipos
            model.addAttribute("puntuacionesEquipo",
                olimpiadasService.listarPuntuacionesPorEquipoYEnfrentamiento(
                    enfrentamiento.getIdEquipoParticipante(), idEnfrentamiento));
            model.addAttribute("puntuacionesEquipoRival",
                olimpiadasService.listarPuntuacionesPorEquipoYEnfrentamiento(
                    enfrentamiento.getIdEquipoParticipanteRival(), idEnfrentamiento));

            return "uap/usic/siga/olimpiadas/administrarPanelEncuentros/administrarPanelEncuentros";

        } catch (Exception e) {
            logger.error("Error al cargar panel de encuentro", e);
            return "redirect:/olimpiadas/enfrentamientos/etapas";
        }
    }

    /**
     * Modifica un enfrentamiento existente
     */
    @PostMapping("/{idEnfrentamiento}/modificar")
    public String modificarEnfrentamiento(
            @PathVariable("idEnfrentamiento") Long idEnfrentamiento,
            @Valid @ModelAttribute("enfrentamiento") Enfrentamiento enfrentamiento,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("operation", "edit");
            model.addAttribute("encuentros", olimpiadasService.listarEncuentros());
            model.addAttribute("equipos", olimpiadasService.listarEquiposParticipantes());
            return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";
        }

        try {
            enfrentamiento.setIdEnfrentamiento(idEnfrentamiento);
            Etapa etapa = olimpiadasService.buscarEtapa(enfrentamiento.getIdEtapa());
            enfrentamiento.setEtapa(etapa);

            olimpiadasService.modificarEnfrentamiento(enfrentamiento);

            redirectAttributes.addFlashAttribute("mensaje", "Enfrentamiento modificado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");

            return "redirect:/olimpiadas/enfrentamientos/etapas/" + etapa.getIdEtapa();

        } catch (Exception e) {
            logger.error("Error al modificar enfrentamiento", e);
            redirectAttributes.addFlashAttribute("mensaje", "Error al modificar enfrentamiento: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            return "redirect:/olimpiadas/enfrentamientos/etapas";
        }
    }
}
