package uap.usic.siga.controladores.olimpiadas.administrarPuntuaciones;

import java.io.IOException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.entidades.OoDetalleEnfrentamientos;
import uap.usic.siga.entidades.OoEnfrentamientos;
import uap.usic.siga.entidades.OoEquiposParticipantes;
import uap.usic.siga.entidades.OoEtapas;
import uap.usic.siga.entidades.OoParticipantes;
import uap.usic.siga.entidades.OoPuntuaciones;
import uap.usic.siga.servicios.OlimpiadasServicios;

@Controller
@RequestMapping("/puntuaO")
public class AdministrarPuntuaciones {

  @Autowired
  private OlimpiadasServicios oServicios;

  @PostMapping(value = "/asigPunto")
  public String asignarPuntos(@RequestParam("idParticipante") Long idParticipante,
      @RequestParam("idEquipoParticipante") Long idEquipoParticipante,
      @RequestParam("idEnfrentamientos") Long idEnfrentamientos) {

    OoPuntuaciones puntuaciones = new OoPuntuaciones();

    puntuaciones.setOoParticipantes(oServicios.buscarParticipantes(idParticipante));
    puntuaciones.setOoEquiposParticipantes(oServicios.buscarEquiposParticipantes(idEquipoParticipante));
    puntuaciones.setOoEnfrentamientos(oServicios.buscarEnfrentamientos(idEnfrentamientos));
    puntuaciones.setPuntaje(5);

    puntuaciones.setGestion(2022);
    puntuaciones.setPeriodo(1);

    oServicios.registrarPuntuaciones(puntuaciones);

    return "redirect:/puntuaO/nuevaPuntuacion?idEnfrentamiento="+idEnfrentamientos;
  }

  @GetMapping("/listarEtapas")
  public String listarEtapas(Model model, HttpSession session) {
    model.addAttribute("busqueda", "etapa");
    model.addAttribute("lEtapas", oServicios.listarOoEtapas());
    return "uap/usic/siga/olimpiadas/administrarPuntuaciones/administrarPuntuaciones";
  }

  @GetMapping(value = "/listarEnfrenta/{id}")
  public String listarEnfrentamientos(@PathVariable("id") long idEtapa, Model model, HttpSession session) {
    model.addAttribute("busqueda", "find");
    model.addAttribute("idEtapa", idEtapa);
    model.addAttribute("lEnfrentamientos", oServicios.listarOoEnfrentamientosPorIdEtapa(idEtapa));
    return "uap/usic/siga/olimpiadas/administrarPuntuaciones/administrarPuntuaciones";
  }

  @GetMapping(value = "/nuevaPuntuacion")
  public String formNuevaPuntuacion(@ModelAttribute("ooPuntuaciones") OoPuntuaciones ooPuntuaciones,
      @RequestParam("idEnfrentamiento") long idEnfrentamiento,
       Model model, HttpSession session) {

    model.addAttribute("operation", "reg");

    OoEnfrentamientos enfrentamientos = oServicios.buscarEnfrentamientos(idEnfrentamiento);

    model.addAttribute("enfrentamientos", enfrentamientos);

    model.addAttribute("lEquipos", oServicios.listarParticipantesdEquipoParticipante(enfrentamientos.getIdEquipoParticipante()));
    model.addAttribute("lEquiposR", oServicios.listarParticipantesdEquipoParticipante(enfrentamientos.getIdEquipoParticipanteR()));

    model.addAttribute("equipo", oServicios.buscarEquiposParticipantes(enfrentamientos.getIdEquipoParticipante()));
    model.addAttribute("equipoR", oServicios.buscarEquiposParticipantes(enfrentamientos.getIdEquipoParticipanteR()));

    model.addAttribute("pEquipo", oServicios.listarPuntuacionesEquipoParticipanteEnfrentamiento(enfrentamientos.getIdEquipoParticipante(), enfrentamientos.getIdEnfrentamientos()));
    model.addAttribute("pEquipoR", oServicios.listarPuntuacionesEquipoParticipanteEnfrentamiento(enfrentamientos.getIdEquipoParticipanteR(), enfrentamientos.getIdEnfrentamientos()));
    
    model.addAttribute("idEnfrentamientos", idEnfrentamiento);
    return "zelda/puntuaciones";
  }

  @PostMapping(value = "/nuevaPuntuacion")
  public String formularioNuevaPuntuacion(@ModelAttribute("ooPuntuaciones") OoPuntuaciones ooPuntuaciones,
      @RequestParam("idEnfrentamiento") long idEnfrentamiento,
      @RequestParam("idEquipoParticipante") long idEquipoParticipante,
      @RequestParam("idEquipoParticipante") long idEquipoParticipanteR, Model model, HttpSession session) {
    model.addAttribute("operation", "reg");

    OoEnfrentamientos enfrentamientos = oServicios.buscarEnfrentamientos(idEnfrentamiento);

    model.addAttribute("enfrentamientos", enfrentamientos);

    model.addAttribute("lEquipos", oServicios.listarParticipantesdEquipoParticipante(enfrentamientos.getIdEquipoParticipante()));
    model.addAttribute("lEquiposR", oServicios.listarParticipantesdEquipoParticipante(enfrentamientos.getIdEquipoParticipanteR()));

    model.addAttribute("equipo", oServicios.buscarEquiposParticipantes(enfrentamientos.getIdEquipoParticipante()));
    model.addAttribute("equipoR", oServicios.buscarEquiposParticipantes(enfrentamientos.getIdEquipoParticipanteR()));

    

    model.addAttribute("idEnfrentamientos", idEnfrentamiento);
    return "uap/usic/siga/olimpiadas/administrarPuntuaciones/administrarPuntuaciones";
  }

  @PostMapping(value = "/inicioPuntuacion")
  public String inicioFormEnfrentamiento(@ModelAttribute("ooEnfrentamientos") OoEnfrentamientos ooEnfrentamientos,
      @RequestParam("idEtapa") long idEtapa, Model model, HttpSession session) {
    model.addAttribute("operation", "reg");
    model.addAttribute("lEncuentros", oServicios.listarOoEncuentros());
    model.addAttribute("lEquipos", oServicios.listarEquiposParticipantes());
    model.addAttribute("lEquiposR", oServicios.listarEquiposParticipantes());
    model.addAttribute("idEtapa", idEtapa);
    return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";
  }

  @PostMapping(value = "/regEnfrenta")
  public String registrarEquipoParticipantes(
      @Valid @ModelAttribute("ooEnfrentamientos") OoEnfrentamientos ooEnfrentamientos, BindingResult result,
      HttpSession session, Model model) throws IOException {
    // @RequestParam("idEtapa") long idEtapa, BindingResult result, HttpSession
    // session, Model model) throws IOException {
    if (result.hasErrors()) {
      model.addAttribute("operation", "reg");
      model.addAttribute("lEncuentros", oServicios.listarOoEncuentros());
      model.addAttribute("lEquipos", oServicios.listarEquiposParticipantes());
      model.addAttribute("idEtapa", ooEnfrentamientos.getIdEtapa());
      return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";
    }

    OoEtapas ooEtapas = new OoEtapas();
    ooEtapas = oServicios.buscarOoEtapasPorIdEtapa(ooEnfrentamientos.getIdEtapa());
    ooEnfrentamientos.setOoEtapas(ooEtapas);
    OoEnfrentamientos ooEnfrenta2 = oServicios.registrarOoEnfrentamientos(ooEnfrentamientos);

    OoEquiposParticipantes ooEquipo = new OoEquiposParticipantes();
    ooEquipo = oServicios.buscarEquiposParticipantes(ooEnfrentamientos.getIdEquipoParticipante());

    OoDetalleEnfrentamientos ooDetalle = new OoDetalleEnfrentamientos();
    ooDetalle.setOoEquiposParticipantes(ooEquipo);
    ooDetalle.setOoEnfrentamientos(ooEnfrenta2);
    oServicios.registrarOoDetalleEnfrentamientos(ooDetalle);

    OoEquiposParticipantes ooEquipoRival = new OoEquiposParticipantes();
    ooEquipoRival = oServicios.buscarEquiposParticipantes(ooEnfrentamientos.getIdEquipoParticipanteR());

    OoDetalleEnfrentamientos ooDetalleRival = new OoDetalleEnfrentamientos();
    ooDetalleRival.setOoEquiposParticipantes(ooEquipoRival);
    ooDetalleRival.setOoEnfrentamientos(ooEnfrenta2);
    oServicios.registrarOoDetalleEnfrentamientos(ooDetalleRival);

    model.addAttribute("busqueda", "find");
    model.addAttribute("idEtapa", ooEnfrentamientos.getIdEtapa());
    model.addAttribute("lEnfrentamientos",
        oServicios.listarOoEnfrentamientosPorIdEtapa(ooEnfrentamientos.getIdEtapa()));
    return "uap/usic/siga/olimpiadas/administrarEnfrentamientos/administrarEnfrenta";

  }

}