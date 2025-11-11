package uap.usic.siga.controladores.administrarEquipos;

import java.io.IOException;


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

import uap.usic.siga.entidades.OoEquiposParticipantes;


import uap.usic.siga.servicios.OlimpiadasServicios;


@Controller
@RequestMapping("/equiposp")
public class AdministrarEquiposParticipantes {
	
	@Autowired
	private OlimpiadasServicios oServicios; 
	
	@GetMapping("/admEquipos")
	public String inicioEquiposParticipantes( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		model.addAttribute("lEquipos", oServicios.listarEquiposParticipantes());
		return "uap/usic/siga/olimpiadas/administrarEquiposParticipantes/admEquiposParticipantes";
	}
	
	// ========= Muestra Formulario ===================
	@PostMapping(value = "/inicioForm")
	public String inicioFormEquipoParticipantes(@ModelAttribute("equipos_participantes") OoEquiposParticipantes equiposParticipantes , Model model, HttpSession session) {
	      model.addAttribute("operation", "reg");
	      model.addAttribute("lComp", oServicios.listarCompeticiones());
	      model.addAttribute("lUni", oServicios.listarUnidadesEducativas());
	      return "uap/usic/siga/olimpiadas/administrarEquiposParticipantes/admEquiposParticipantes";
	}

	// ===================== Guardar Datos en BD =========
	@PostMapping(value = "/registroEquipoParticipantes")
	public String registrarEquipoParticipantes(@Valid @ModelAttribute("equipos_participantes") OoEquiposParticipantes equiposParticipantes, BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			  model.addAttribute("operation", "reg");
			  model.addAttribute("lComp", oServicios.listarCompeticiones());
			  model.addAttribute("lUni", oServicios.listarUnidadesEducativas());
		      return "uap/usic/siga/olimpiadas/administrarEquiposParticipantes/administrarEquiposParticipantes";
		}
		
		oServicios.registrarEquiposParticipantes(equiposParticipantes);

		model.addAttribute("busqueda", "find");
		model.addAttribute("lEquipos",  oServicios.listarEquiposParticipantes());
		return "uap/usic/siga/olimpiadas/administrarEquiposParticipantes/admEquiposParticipantes";
	}

	@GetMapping(value = "/inicioModEquipo/{id}")
	public String inicioFormModEquipoParticipantes(@ModelAttribute("equiposParticipantes") OoEquiposParticipantes equiposParticipantes , 
			@PathVariable("id") long idEquipoParticipante, Model model, HttpSession session) {
			
		  model.addAttribute("equiposParticipantes", oServicios.buscarEquiposParticipantes(idEquipoParticipante));
	      model.addAttribute("operation", "modf");
	      model.addAttribute("lComp", oServicios.listarCompeticiones());
	      model.addAttribute("lUni", oServicios.listarUnidadesEducativas());
	      return "uap/usic/siga/olimpiadas/administrarEquiposParticipantes/admEquiposParticipantes";
	}
	
	@PostMapping(value = "/modificarEquipoParticipantes")
	public String modificarEquiposParticipanes(@Valid @ModelAttribute("equipos_participantes") OoEquiposParticipantes equiposParticipantes, BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			  model.addAttribute("equiposParticipantes", oServicios.buscarEquiposParticipantes(equiposParticipantes.getIdEquipoParticipante()));
		      model.addAttribute("operation", "modf");
		      model.addAttribute("lComp", oServicios.listarCompeticiones());
		      model.addAttribute("lUni", oServicios.listarUnidadesEducativas());
		      return "uap/usic/siga/olimpiadas/administrarEquiposParticipantes/admEquiposParticipantes";
		}
		
	   oServicios.modificarEquiposParticipantes(equiposParticipantes); 

		model.addAttribute("busqueda", "find");
		model.addAttribute("lEquipos", oServicios.listarEquiposParticipantes());
		return "uap/usic/siga/olimpiadas/administrarEquiposParticipantes/admEquiposParticipantes";
	}

}
