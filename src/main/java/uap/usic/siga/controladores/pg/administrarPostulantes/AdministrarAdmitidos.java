package uap.usic.siga.controladores.pg.administrarPostulantes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

import uap.usic.siga.entidadesPg.Docentes;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.entidadesPg.Postulantes;
import uap.usic.siga.servicios.DocentesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PostulantesServicios;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/aPostulantesAdmitidos")
public class AdministrarAdmitidos {

	@Autowired
	private PersonasServicios prsServicios;

	@Autowired
	private ProgramasService prgServicios;

	@Autowired
	private PostulantesServicios pstServicios;

	@Autowired
	private DocentesServicios docServicios;

	private String URL = "uap/usic/siga/pg/administrarPostulantes/admAdmitidos";

	private String REDIRECT = "redirect:/aPostulantesAdmitidos/listarAdmitidosPorPrograma=";

	// Ok
	@GetMapping("/inicioPostulantesAdmitidos")
	public String inicioPostulanteAdmitidossString(HttpSession session, Model model) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return URL;
	}

	@GetMapping("/listarAdmitidosPorPrograma={idPrograma}")
	public String listarAdmitidosPorProgramaGET(@PathVariable("idPrograma") Long idPrograma, HttpSession session,
			Model model) {
			var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		try {
			List<PgPstProgramas> pstprg = pstServicios.listarPgPstProgramasPorIdPrograma(idPrograma);
			model.addAttribute("lAdmitidos", pstprg);
			model.addAttribute("programas", prg);
			model.addAttribute("busqueda", "find");
        	getDesplegarListasComunes(model, session);
        	return URL;
		}catch(IndexOutOfBoundsException e){
			//Manejar con try/catch, opcional agregar una vista que diga que no tiene registros
			model.addAttribute("programas", prg);
			model.addAttribute("busqueda", "find");
			return URL;
		}
	}


	@PostMapping("/verDetallesAdmitidos")
	public String verDetallesAdmitidos(@RequestParam("idPstPersona") Long idPstPersona, Model model,
			HttpSession session) throws IOException {
		model.addAttribute("admitido", pstServicios.buscarPgPstProgramasPorIdGET(idPstPersona));
		// model.addAttribute("programa",
		// pstServicios.listarProgramasAdmitidosPorAdmitidoId(idPstPersona));
		model.addAttribute("operation", "list");
		return "uap/usic/siga/pg/certificados/imprimirCertificadoAdmision";
	}

	@PostMapping("/imprimirCertificadoAdmitido")
	public String imprimirCertificadoAdmitido(@RequestParam("idPstPersonaPrograma") Long id, Model model,
			HttpSession session)
			throws IOException {
		model.addAttribute("admitido", pstServicios.buscarPgPstProgramasPorIdGET(id));
		return "uap/usic/siga/pg/certificados/imprimirCertificadoAdmision";
	}

	@PostMapping("/inicioAnularAdmitido")
	public String inicioAnularAdmitido(@ModelAttribute("postulante") Postulantes postulante,
			@RequestParam("idPostulante") Long idPostulante, Model model, HttpSession session) throws IOException {

		model.addAttribute("postulante", pstServicios.buscarPostulantesAdmitidosPorIdGET(idPostulante));
		model.addAttribute("operation", "elim");
		getDesplegarListasComunes(model, session);
		return URL;
	}

	@PostMapping(value = "/anularAdmitido")
	public String anularAdmitido(@Valid Postulantes postulantes, BindingResult result, HttpSession session,
			Model model,
			@RequestParam("idPostulante") Long idPersona) throws IOException {

		postulantes.setEstado('X');

		pstServicios.eliminarPostulantesSET(postulantes);

		getDesplegarListasComunes(model, session);
		model.addAttribute("busqueda", "find");
		return REDIRECT;
	}

	public void getDesplegarListasComunes(Model model, HttpSession session) {

		// model.addAttribute("lPostulantes",
		// pstServicios.listarPostulantesAdmitidosJPQL());
		model.addAttribute("lPostulantes", pstServicios.listarPgPstProgramasPorPaternoJPQL());
		model.addAttribute("lPais", prsServicios.listarPaises());
		model.addAttribute("lTiposSexos", prsServicios.listarTiposSexos());
		model.addAttribute("lCiExpedido", prsServicios.listarCedulaIdentidadexpedidos());
		// model.addAttribute("lGradoAcademico", prsServicios.listarGradosAcademicos());
		model.addAttribute("lProgramas", prgServicios.listaProgramasJPLQ());
	}
}