package uap.usic.siga.web.olimpiadas;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import uap.usic.siga.domain.olimpiadas.Puntuacion;
import uap.usic.siga.service.olimpiadas.OlimpiadasService;

/**
 * Controlador para la gestión de puntuaciones de las olimpiadas
 * Maneja las operaciones CRUD de puntuaciones y su asignación a participantes
 */
@Controller
@RequestMapping("/olimpiadas/puntuaciones")
public class PuntuacionesController {

    @Autowired
    private OlimpiadasService olimpiadasService;

    /**
     * Lista todas las etapas disponibles
     */
    @GetMapping("/etapas")
    public String listarEtapas(Model model) {
        model.addAttribute("busqueda", "etapa");
        model.addAttribute("etapas", olimpiadasService.listarEtapas());
        return "uap/usic/siga/olimpiadas/administrarPuntuaciones/administrarPuntuaciones";
    }

    /**
     * Lista enfrentamientos de una etapa específica
     */
    @GetMapping("/etapas/{idEtapa}/enfrentamientos")
    public String listarEnfrentamientosPorEtapa(@PathVariable("idEtapa") Long idEtapa, Model model) {
        model.addAttribute("busqueda", "find");
        model.addAttribute("idEtapa", idEtapa);
        model.addAttribute("enfrentamientos", olimpiadasService.listarEnfrentamientosPorEtapa(idEtapa));
        return "uap/usic/siga/olimpiadas/administrarPuntuaciones/administrarPuntuaciones";
    }

    /**
     * Muestra el formulario para asignar puntuaciones en un enfrentamiento
     */
    @GetMapping("/enfrentamientos/{idEnfrentamiento}/asignar")
    public String mostrarFormularioAsignacion(
            @PathVariable("idEnfrentamiento") Long idEnfrentamiento,
            @ModelAttribute("puntuacion") Puntuacion puntuacion,
            Model model) {

        Enfrentamiento enfrentamiento = olimpiadasService.buscarEnfrentamiento(idEnfrentamiento);

        model.addAttribute("operation", "reg");
        model.addAttribute("enfrentamiento", enfrentamiento);

        // Cargar participantes de ambos equipos
        model.addAttribute("participantesEquipo",
            olimpiadasService.listarParticipantesPorEquipo(enfrentamiento.getIdEquipoParticipante()));
        model.addAttribute("participantesEquipoRival",
            olimpiadasService.listarParticipantesPorEquipo(enfrentamiento.getIdEquipoParticipanteRival()));

        // Cargar equipos
        model.addAttribute("equipo",
            olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipante()));
        model.addAttribute("equipoRival",
            olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipanteRival()));

        // Cargar puntuaciones existentes
        model.addAttribute("puntuacionesEquipo",
            olimpiadasService.listarPuntuacionesPorEquipoYEnfrentamiento(
                enfrentamiento.getIdEquipoParticipante(), idEnfrentamiento));
        model.addAttribute("puntuacionesEquipoRival",
            olimpiadasService.listarPuntuacionesPorEquipoYEnfrentamiento(
                enfrentamiento.getIdEquipoParticipanteRival(), idEnfrentamiento));

        model.addAttribute("idEnfrentamiento", idEnfrentamiento);

        return "uap/usic/siga/olimpiadas/administrarPuntuaciones/administrarPuntuaciones";
    }

    /**
     * Asigna puntos a un participante en un enfrentamiento
     */
    @PostMapping("/asignar")
    public String asignarPuntos(
            @RequestParam("idParticipante") Long idParticipante,
            @RequestParam("idEquipoParticipante") Long idEquipoParticipante,
            @RequestParam("idEnfrentamiento") Long idEnfrentamiento,
            @RequestParam(value = "puntaje", defaultValue = "5") Integer puntaje,
            @RequestParam(value = "gestion", required = false) Integer gestion,
            @RequestParam(value = "periodo", required = false) Integer periodo,
            RedirectAttributes redirectAttributes) {

        try {
            Puntuacion puntuacion = new Puntuacion();

            // Asignar relaciones
            puntuacion.setParticipante(olimpiadasService.buscarParticipante(idParticipante));
            puntuacion.setEquipoParticipante(olimpiadasService.buscarEquipoParticipante(idEquipoParticipante));
            puntuacion.setEnfrentamiento(olimpiadasService.buscarEnfrentamiento(idEnfrentamiento));
            puntuacion.setPuntaje(puntaje);

            // Asignar gestión y periodo si están presentes
            if (gestion != null) {
                puntuacion.setGestion(gestion);
            }
            if (periodo != null) {
                puntuacion.setPeriodo(periodo);
            }

            olimpiadasService.registrarPuntuacion(puntuacion);

            redirectAttributes.addFlashAttribute("mensaje", "Puntuación asignada exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al asignar puntuación: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }

        return "redirect:/olimpiadas/puntuaciones/enfrentamientos/" + idEnfrentamiento + "/asignar";
    }

    /**
     * Muestra el formulario para iniciar un nuevo enfrentamiento desde puntuaciones
     */
    @PostMapping("/enfrentamientos/nuevo")
    public String mostrarFormularioNuevoEnfrentamiento(
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
    @PostMapping("/enfrentamientos/registrar")
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
            // Asignar etapa al enfrentamiento
            Etapa etapa = olimpiadasService.buscarEtapa(enfrentamiento.getIdEtapa());
            enfrentamiento.setEtapa(etapa);

            // Registrar enfrentamiento
            Enfrentamiento enfrentamientoRegistrado = olimpiadasService.registrarEnfrentamiento(enfrentamiento);

            // Registrar detalle para el primer equipo
            EquipoParticipante equipo1 = olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipante());
            DetalleEnfrentamiento detalle1 = new DetalleEnfrentamiento(enfrentamientoRegistrado, equipo1);
            olimpiadasService.registrarDetalleEnfrentamiento(detalle1);

            // Registrar detalle para el equipo rival
            EquipoParticipante equipo2 = olimpiadasService.buscarEquipoParticipante(enfrentamiento.getIdEquipoParticipanteRival());
            DetalleEnfrentamiento detalle2 = new DetalleEnfrentamiento(enfrentamientoRegistrado, equipo2);
            olimpiadasService.registrarDetalleEnfrentamiento(detalle2);

            redirectAttributes.addFlashAttribute("mensaje", "Enfrentamiento registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar enfrentamiento: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }

        model.addAttribute("busqueda", "find");
        model.addAttribute("idEtapa", enfrentamiento.getIdEtapa());
        model.addAttribute("enfrentamientos",
            olimpiadasService.listarEnfrentamientosPorEtapa(enfrentamiento.getIdEtapa()));

        return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";
    }
}
