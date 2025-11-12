package uap.usic.siga.controladores.pg.administrarPostulantes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uap.usic.siga.entidadesPg.PgPstDocumentos;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.entidadesPg.Postulantes;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.entidadesPg.TiposDocumentos;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PostulantesServicios;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/aPostulantes")
public class AdministrarPostulantes {
	@Autowired
	private PersonasServicios prsServicios;

	@Autowired
	private ProgramasService prgServicios;

	@Autowired
	private PostulantesServicios pstServicios;

	private String URL = "uap/usic/siga/pg/administrarPostulantes/admPostulantes";

	private String REDIRECT = "redirect:/aPostulantes/listarPostulantesPorPrograma=";

	@GetMapping("/inicioPostulantes")
	public String inicioPostulantesString(HttpSession session, Model model) {
		// List<TiposDocumentos> listaDocumentos = Arrays.asList(
		// new TiposDocumentos("Fotocopia legalizada del Titulo en Provision Nacional"),
		// new TiposDocumentos("Fotocopia legalizada del Diploma Academico"),
		// new TiposDocumentos("Copia de la Hoja de Vida"),
		// new TiposDocumentos("Fotografia 4x4 fondo rojo"));

		// for(var doc : listaDocumentos){
		// pstServicios.registrarTiposDocumentos(doc);}

		getDesplegarListasComunes(model, session);
		model.addAttribute("busqueda", "find");
		return URL;
	}

	@GetMapping("listarPostulantesPorPrograma={idPrograma}")
	public String listarPostulantesPorProgramasGET(@PathVariable("idPrograma") Long idPrograma, HttpSession session,
			Model model) {
		var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		model.addAttribute("programas", prg);
		model.addAttribute("listaPostulantes", prg.getPostulantes());
		model.addAttribute("lPostulantes", pstServicios.listarPostulantesPorIdGET());
		model.addAttribute("busqueda", "find");
		return URL;
	}

	@PostMapping("/listarPostulantesPorPrograma")
	public String listarPostulantesPorProgramasPOST(@ModelAttribute("programa") Programas programa,
			@RequestParam("idPrograma") Long idPrograma, Model model, HttpSession session) throws IOException {
		var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		model.addAttribute("programas", prg);
		model.addAttribute("listaPostulantes", prg.getPostulantes());
		model.addAttribute("lPostulantes", pstServicios.listarPostulantesPorIdGET());
		model.addAttribute("busqueda", "find");
		return URL;
	}

	// ============================= NUEVO POSTULANTE PREINSCRITO
	// ====================================
	@PostMapping(value = "/formPostulantes")
	public String inicioFormPostulante(@ModelAttribute("postulante") Postulantes postulante,
			@RequestParam("idPrograma") Long idPrograma, HttpSession session, Model model) throws IOException {
		model.addAttribute("operation", "regNuevo");
		model.addAttribute("programas", prgServicios.buscarProgramaPorIdPrograma(idPrograma));
		getDesplegarListasComunes(model, session);
		return URL;
	}

	@PostMapping(value = "/formPostulantesExistentes")
	public String inicioFormPostulanteExistente(@ModelAttribute("postulante") Postulantes postulante,
			HttpSession session, Model model, @RequestParam("ciPostulante") String ciPostulante,
			@RequestParam("idPrograma") Long idPrograma) throws IOException {
		var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		var pst = pstServicios.buscarPostulantesPorCedulaIdentidadGET(ciPostulante);

		// Las validaciones de si existe el postulante estan en scriptPostulantes.js
		model.addAttribute("postulante", pst);
		model.addAttribute("operation", "regExistente");
		model.addAttribute("programas", prg);
		getDesplegarListasComunes(model, session);
		return URL;
	}

	@PostMapping(value = "/registrarPostulantes")
	public String registrarPostulante(@Valid @ModelAttribute("postulante") Postulantes postulante, BindingResult result,
			HttpSession session, Model model, @RequestParam("idPrograma") Long idPrograma) throws IOException {
		var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		try {
			postulante.setProgramasPostulados(Arrays.asList(prg));
			pstServicios.registrarPostulantesSET(postulante);
			model.addAttribute("programas", prg);
			model.addAttribute("listaPostulantes", prg.getPostulantes());
			getDesplegarListasComunes(model, session);
			model.addAttribute("busqueda", "find");
			return REDIRECT + idPrograma;
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("programas", prg);
			model.addAttribute("alerta", "existente");
			model.addAttribute("registro", "nuevo");
			model.addAttribute("operation", "regNuevo");
			getDesplegarListasComunes(model, session);
			return URL;
		}
	}

	@PostMapping(value = "/agregarPostulacion")
	public String agregarPostulante(@Valid @ModelAttribute("postulante") Postulantes postulante, BindingResult result,
			HttpSession session, Model model, @RequestParam("idPrograma") Long idPrograma,
			@RequestParam("idPostulante") Long idPostulante) throws IOException {
		var pst = pstServicios.buscarPostulantesPorIdGET(idPostulante);
		var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		pst.getProgramasPostulados().add(prg);
		postulante.setProgramasPostulados(pst.getProgramasPostulados());

		//Guardar valores al nuevo objeto
		postulante.setDireccion(pst.getDireccion());
		postulante.setCiudadResidencia(pst.getCiudadResidencia());
		postulante.setProfesion(pst.getProfesion());;
		postulante.setPrsGradosAcademicos(pst.getPrsGradosAcademicos());
		
		pstServicios.modificarPostulantesSET(postulante);
		getDesplegarListasComunes(model, session);
		model.addAttribute("programas", prg);
		model.addAttribute("listaPostulantes", prg.getPostulantes());
		model.addAttribute("busqueda", "find");
		return REDIRECT + idPrograma;
	}

	// ================MODIFICAR POSTULANTE PREINSCRITO=============================
	@PostMapping(value = "/inicioModificarPostulantes")
	public String inicioModificarPostulantes(@ModelAttribute("postulante") Postulantes postulante,
			@RequestParam("idPostulante") Long idPostulante, @RequestParam("idPrograma") Long idPrograma,
			Model model, HttpSession session) throws IOException {
		var pst = pstServicios.buscarPostulantesPorIdGET(idPostulante);
		var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);

		model.addAttribute("programas", prgServicios.buscarProgramaPorIdPrograma(idPrograma));
		model.addAttribute("postulante", pst);
		model.addAttribute("programas", prg);
		model.addAttribute("operation", "modif");
		model.addAttribute("lEstadoCivil", prsServicios.listarEstadoCivil());
		model.addAttribute("lGradoAcademico", prsServicios.listarGradosAcademicos());
		getDesplegarListasComunes(model, session);
		return URL;
	}

	@PostMapping(value = "/actualizarPostulantes")
	public String actualizarPostulantes(@Valid Postulantes postulante, BindingResult result,
			Model model, HttpSession session, HttpServletRequest request,
			@RequestParam("idPrograma") Long idPrograma, @RequestParam("idPostulante") Long idPostulante)
			throws IOException {
		var pst = pstServicios.buscarPostulantesPorIdGET(idPostulante);
		try {
			postulante.setProgramasPostulados(pst.getProgramasPostulados());
			pstServicios.modificarPostulantesSET(postulante);
			return REDIRECT + idPrograma;
		} catch (DataIntegrityViolationException e) {
			var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
			model.addAttribute("operation", "modif");
			model.addAttribute("postulante", pst);
			model.addAttribute("programas", prg);
			getDesplegarListasComunes(model, session);
			return URL;
		}

	}

	// =================ELIMINAR POSTULANTE PREINSCRITO======================
	@PostMapping("/inicioEliminarPostulantes")
	public String inicioEliminarPostulantes(@ModelAttribute("postulante") Postulantes postulante,
			@RequestParam("idPostulante") Long idPostulante, @RequestParam("idPrograma") Long idPrograma,
			Model model, HttpSession session) throws IOException {
		model.addAttribute("programas", prgServicios.buscarProgramaPorIdPrograma(idPrograma));
		model.addAttribute("postulante", pstServicios.buscarPostulantesPorIdGET(idPostulante));

		model.addAttribute("operation", "elim");
		getDesplegarListasComunes(model, session);

		return URL;
	}

	@PostMapping(value = "/eliminarPostulacion")
	public String eliminarPostulantes(@Valid Postulantes postulantes, BindingResult result, HttpSession session,
			Model model, @RequestParam("idPostulante") Long idPostulante, @RequestParam("idPrograma") Long idPrograma)
			throws IOException {
		var pst = pstServicios.buscarPostulantesPorIdGET(idPostulante);
		var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		pst.getProgramasPostulados().remove(prg);
		// remover el programa y hacer merge de la lista sin el programa

		postulantes.setProgramasPostulados(pst.getProgramasPostulados());
		pstServicios.modificarPostulantesSET(pst);
		getDesplegarListasComunes(model, session);
		model.addAttribute("busqueda", "find");
		return REDIRECT + idPrograma;
	}

	// ===================ADMITIR POSTULANTE=====================
	@PostMapping("/inicioFormularioAdmitidos")
	public String inicioFormularioAdmitidos(@ModelAttribute("postulante") Postulantes postulante,
			HttpSession session,
			Model model, @RequestParam("ciPostulante") String ciPostulante, @RequestParam("idPrograma") Long idPrograma)
			throws IOException {
		// Lista estatica, siento que seria mejor mover esto a una tabla donde contengan
		// los tipos de documentos estaticos
		// o ponerlo en la vista en un input text readonly aunque eso haria mas largo el
		// HTML
		List<PgPstDocumentos> listaDocumentos = Arrays.asList(
				new PgPstDocumentos("Fotocopia legalizada del Titulo en Provision Nacional"),
				new PgPstDocumentos("Fotocopia legalizada del Diploma Academico"),
				new PgPstDocumentos("Copia de la Hoja de Vida"),
				new PgPstDocumentos("Fotografia 4x4 fondo rojo"));
		listaDocumentos.forEach(e -> e.setEstado('F'));

		model.addAttribute("lTipoAdmision", pstServicios.listarPgAdmTiposAdmisiones());
		Postulantes pstRegistrado = pstServicios.buscarPostulantesPorCedulaIdentidadGET(ciPostulante);
		
		model.addAttribute("postulante", pstRegistrado);
		List<PgPstProgramas> listaProgramasAdmitidos = pstServicios.buscarPgPstProgramasPorCiPostulante(ciPostulante);

		if (listaProgramasAdmitidos.isEmpty()) {
			model.addAttribute("operation", "admitirNuevo");
		} else {
			model.addAttribute("operation", "admitirRegistrado");
		}

		model.addAttribute("lEstadoCivil", prsServicios.listarEstadoCivil());
		model.addAttribute("lGradoAcademico", prsServicios.listarGradosAcademicos());
		model.addAttribute("lDocumentos", listaDocumentos);
		model.addAttribute("programas", prgServicios.buscarProgramaPorIdPrograma(idPrograma));
		getDesplegarListasComunes(model, session);
		return URL;
	}

	// ====================REGISTRAR NUEVO POSTULANTE ADMITIDO=====================
	@PostMapping(value = "/registrarAdmitido")
	public String registrarAdmitidos(@Valid Postulantes postulantes, BindingResult result,
			Model model, HttpSession session, HttpServletRequest request,
			@RequestParam("pstDocumento") List<String> pstDocumento,
			@RequestParam("pstDocumentoEstado") List<Integer> pstDocumentoEstado,
			@RequestParam("pstDocumentoObservacion") List<String> pstDocumentoObservacion,
			@RequestParam("idPostulante") Long idPostulante, @RequestParam("idPrograma") Long idPrograma,
			@RequestParam("idTipoAdmision") Long idTipoAdmision) throws IOException {

		var pst = pstServicios.buscarPostulantesPorIdGET(idPostulante);
		var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		// A la hora de admitir se elimina el programa postulado de la lista
		// Esto sera una eliminacion permanente, en pgpstpersonasprogramas se manejara
		// por estado
		pst.getProgramasPostulados().remove(prg);

		var pstprg = new PgPstProgramas(postulantes, prg, pstServicios.buscarPgAdmTiposAdmisionesPorId(idTipoAdmision));
		List<PgPstProgramas> listaProgramasAdmitidos = List.of(pstprg);
		
		List<PgPstDocumentos> listaDocumentosFormulario = new ArrayList<>();
		// Se mantendra hasta 4 hasta nuevas disposiciones o usar una tabla externa para
		// consumirla
		// Supongo que habra mas requisitos para extranjeros asi que esto variara
		for (int i = 0; i < 4; i++) {
			var pstDoc = new PgPstDocumentos();
			pstDoc.setPstDocumento(pstDocumento.get(i));
			// Recibimos una lista de numeros del formulario que se envia atravez del input
			// hidden
			// Si el checkbox no fue presionado se mandara 0, si fue presionado se enviara 1
			pstDoc.setEstado((pstDocumentoEstado.get(i) == 0) ? 'F' : 'A');
			pstDoc.setObservacion(pstDocumentoObservacion.get(i));
			pstDoc.setPgPstProgramas(pstprg);
			listaDocumentosFormulario.add(pstDoc);
		}
		pstprg.setPgPstDocumentos(listaDocumentosFormulario);

		postulantes.setProgramasPostulados(pst.getProgramasPostulados());
		postulantes.setProgramasAdmitidos(listaProgramasAdmitidos);
		pstServicios.modificarPostulantesSET(postulantes);

		getDesplegarListasComunes(model, session);
		return REDIRECT + idPrograma;
	}

	// =============REGISTRA POSTULANTE ADMITIDO EXISTENTE==============
	@PostMapping(value = "/agregarAdmitido")
	public String agregarAdmitidos(@Valid Postulantes postulantes, BindingResult result,
			Model model, HttpSession session, HttpServletRequest request,
			@RequestParam("pstDocumento") List<String> pstDocumento,
			@RequestParam("pstDocumentoEstado") List<Integer> pstDocumentoEstado,
			@RequestParam("pstDocumentoObservacion") List<String> pstDocumentoObservacion,
			@RequestParam("idTipoAdmision") Long idTipoAdmision,
			@RequestParam("idPrograma") Long idPrograma, @RequestParam("idPostulante") Long idPostulante)
			throws IOException {

		var pst = pstServicios.buscarPostulantesPorIdGET(idPostulante);
		var prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		pst.getProgramasPostulados().remove(prg);

		var pstprg = new PgPstProgramas(pst, prg, pstServicios.buscarPgAdmTiposAdmisionesPorId(idTipoAdmision));
	
		List<PgPstDocumentos> listaDocumentosFormulario = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			var pstDoc = new PgPstDocumentos();
			pstDoc.setPstDocumento(pstDocumento.get(i));
			// Recibimos una lista de numeros del formulario que se envia atravez del input
			// hidden
			// Si el checkbox no fue presionado se mandara 0, si fue presionado se enviara 1
			pstDoc.setEstado((pstDocumentoEstado.get(i) == 0) ? 'F' : 'A');
			pstDoc.setObservacion(pstDocumentoObservacion.get(i));
			pstDoc.setPgPstProgramas(pstprg);
			listaDocumentosFormulario.add(pstDoc);
		}
		pstprg.setPgPstDocumentos(listaDocumentosFormulario);

		// Persistimos un nuevo registro de la tabla intermedia
		pst.getProgramasAdmitidos().add(pstprg);
		pst.setProgramasAdmitidos(pst.getProgramasAdmitidos());

		pstServicios.modificarPostulantesSET(pst);
		pstServicios.modificarPostulantesSET(postulantes);

		getDesplegarListasComunes(model, session);
		model.addAttribute("busqueda", "find");
		return REDIRECT + idPrograma;
	}

	@GetMapping(value = "/api/postulantes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Postulantes>> listaPostulantesResponse(Model model, HttpSession session) {
		List<Postulantes> pst = pstServicios.listarPostulantesPorIdGET();
		return new ResponseEntity<List<Postulantes>>(pst, HttpStatus.OK);
	}

	@GetMapping(value = "/api/postulantes-programa={idPrograma}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Postulantes>> listaPostulantesProgramaResponse(Model model, HttpSession session,
			@PathVariable("idPrograma") Long idPrograma) {
		Programas prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
		return new ResponseEntity<List<Postulantes>>(prg.getPostulantes(), HttpStatus.OK);
	}

	@GetMapping(value = "/api/postulante-ci={ciPostulante}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Postulantes> buscarPostulanteCiResponse(Model model, HttpSession session,
			@PathVariable("ciPostulante") String ci) {
		Postulantes pst = pstServicios.buscarPostulantesPorCedulaIdentidadGET(ci);
		return new ResponseEntity<Postulantes>(pst, HttpStatus.OK);
	}

	@GetMapping(value = "/api/postulante-id={idPostulante}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Postulantes> buscarPostulantesIdResponse(Model model, HttpSession session,
			@PathVariable("idPostulante") Long id) {
		Postulantes pst = pstServicios.buscarPostulantesPorIdGET(id);
		return new ResponseEntity<Postulantes>(pst, HttpStatus.OK);
	}

	public void getDesplegarListasComunes(Model model, HttpSession session) {
		// model.addAttribute("lPostulantes", pstServicios.listarPostulantesPorIdGET());
		// model.addAttribute("idPrograma", this.idPrograma);
		model.addAttribute("lPais", prsServicios.listarPaises());
		model.addAttribute("lTiposSexos", prsServicios.listarTiposSexos());
		model.addAttribute("lCiExpedido", prsServicios.listarCedulaIdentidadexpedidos());
		model.addAttribute("lProgramas", prgServicios.listaProgramasJPLQ());
	}
}