package uap.usic.siga.controladores.pg.administrarProgramas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.entidadesPg.EjecucionesModulos;
import uap.usic.siga.entidadesPg.PgArchivosAdjuntos;
import uap.usic.siga.entidadesPg.PgPrgModulos;
import uap.usic.siga.entidadesPg.PgPrgPlanes;
import uap.usic.siga.entidadesPg.PgPrgPlnGrupo;
import uap.usic.siga.entidadesPg.PgPrgPlnVersiones;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.servicios.ModuloServicio;
import uap.usic.siga.servicios.ProgramasService;
import uap.usic.siga.servicios.UserService;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/aLanzamientos")
public class AdministrarLanzamientos {
	@Autowired private ProgramasService mService;
	@Autowired private UserService userService;
	@Autowired private ProgramasService prgServicios;
	@Autowired private ModuloServicio modServicios;
	
	//==========================Lista de Programas Registrados=======================
    @GetMapping("/inicioProgramas")
	public String inicioProgramasString( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "uap/usic/siga/pg/administrarLanzamientos/admLanz";
	}
	//============================================================================
	//========================== Lista de Versiones Registradas =======================
    @GetMapping("/listaVersionesActivas")
	public String listaVersionesString( Model model, HttpSession session) {
		model.addAttribute("listVer", "activas");
		getDesplegarListasComunes(model, session);
		return "uap/usic/siga/pg/administrarLanzamientos/admLanz";
	}
	//============================================================================

	@PostMapping("/listaVersionesPorPrograma")
	public String listaVersionesPorIdPrograma(@ModelAttribute("version") PgPrgPlnVersiones version,Model model, 
		@RequestParam("idPrograma") Long idPrograma ,
		@RequestParam("idPgPrgPlanes") Long idPgPrgPlanes,
		HttpSession session) {
			model.addAttribute("listaVer", mService.ListaVersionesPorIdPrograma(idPrograma));
			model.addAttribute("programas",mService.buscarProgramaPorIdPrograma(idPrograma));
			model.addAttribute("planes", mService.buscarPlanesPorIdPlanes(idPgPrgPlanes));

			model.addAttribute("listaVersiones", "lista");
			getDesplegarListasComunes(model, session);
		return "uap/usic/siga/pg/administrarLanzamientos/admLanz";
	}

	//===================================== Inicio Publicar =====================
	//===========================Registro Version ==================================
	@PostMapping(value = "/formLanz")
	public String inicioFormLanzamiento(@ModelAttribute("version") PgPrgPlnVersiones version, 
	@RequestParam("idPrograma") Long idPrograma,
	@RequestParam("idPgPrgPlanes") Long idPgPrgPlanes,
	Model model, HttpSession session) {
	    model.addAttribute("operation", "reg");
		model.addAttribute("programa",mService.buscarProgramaPorIdPrograma(idPrograma));
		model.addAttribute("planes", mService.buscarPlanesPorIdPlanes(idPgPrgPlanes));
	    getDesplegarListasComunes(model, session);
	    return "uap/usic/siga/pg/administrarLanzamientos/admLanz";
	}
	// ================================== Guardar Datos ===============================
	@PostMapping(value = "/registroLanz")
	public String registrarFacultades(@Valid @ModelAttribute("version") PgPrgPlnVersiones version,
	@RequestParam("idPrograma") Long idPrograma,
	@RequestParam("idPgPrgPlanes") Long idPgPrgPlanes,
	BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("busqueda", "reg");
			getDesplegarListasComunes(model, session);
			 
			return "uap/usic/siga/pg/administrarLanzamientos/admLanz";
		}
		
		 	Usuarios usuarios = userService.findByIdEagerly((Long) session.getAttribute("idUsr"));        
	        MultipartFile multipartFile = version.getFile();
	        PgArchivosAdjuntos pgArchivosAdjuntos = new PgArchivosAdjuntos();
	       
	        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
	    
	        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Pg/version");
	        model.addAttribute("di", rutaArchivo);
	        Integer ad = adjuntarArchivo.adjuntarArchivosPg(version, rutaArchivo);

	        pgArchivosAdjuntos.setNombreArchivo(version.getNombreArchivo());
	        pgArchivosAdjuntos.setTipoArchivo(multipartFile.getContentType());
	        pgArchivosAdjuntos.setUsuarios(usuarios);
	        pgArchivosAdjuntos.setRutaArchivo("Pg/version/");
	        PgArchivosAdjuntos pgArchivosAdjuntos1 = mService.registrarPgArchivosAdjuntosSET(pgArchivosAdjuntos);

	        version.setPgArchivosAdjuntos(pgArchivosAdjuntos1);
	        version.setUsuarios(usuarios);
	        

	mService.registrarVersionPlnSet(version);
	Programas programa;
	PgPrgPlanes plan;

	model.addAttribute(plan= mService.buscarPlanesPorIdPlanes(idPgPrgPlanes));

	plan.setEstado('L');
	mService.modificarPlanes(plan);

	model.addAttribute(programa = mService.buscarProgramaPorIdPrograma(idPrograma));

	programa.setEstado('L');
	mService.modificarPersonas(programa);

	model.addAttribute("listaVersiones", "lista");
	model.addAttribute("listaVer", mService.ListaVersionesPorIdPrograma(idPrograma));
	model.addAttribute("programa",mService.buscarProgramaPorIdPrograma(idPrograma));
	model.addAttribute("planes", mService.buscarPlanesPorIdPlanes(idPgPrgPlanes));
	model.addAttribute("mensaje", "Se Registro Correctamente La Version");
	getDesplegarListasComunes(model, session);
	return "uap/usic/siga/pg/administrarLanzamientos/admLanz";
	}

	@RequestMapping(value = "/openFile/{id}", method = RequestMethod.GET, produces = "application/pdf")
	    public @ResponseBody
	    FileSystemResource abrirArchivoMedianteResourse(HttpServletResponse response, @PathVariable("id") long idPgPrgPlnVersiones) throws FileNotFoundException {
		 	PgArchivosAdjuntos pgArchivoAdjuntos = mService.buscarPgArchivosAdjuntosPorIdPgPrgPlnVersionesGET(idPgPrgPlnVersiones);
	        File file = new File("C:/"+ pgArchivoAdjuntos.getRutaArchivo() + pgArchivoAdjuntos.getNombreArchivo());
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
	        response.setHeader("Content-Length", String.valueOf(file.length()));
	        return new FileSystemResource(file);
	    }
	//=====================================Fin Registro Version =====================

	//===========================Registro Grupo ==================================
	@PostMapping(value = "/formGrupo")
	public String inicioFormGrupo(@ModelAttribute("grupos") PgPrgPlnGrupo grupos,
	@RequestParam("idPgPrgPlnVersiones") Long idPgPrgPlnVersiones,
	Model model, HttpSession session) {

		model.addAttribute("version", mService.buscarVersionPorIdGET(idPgPrgPlnVersiones));
	    model.addAttribute("opet", "registro");
	    getDesplegarListasComunes(model, session);
	    return "uap/usic/siga/pg/administrarLanzamientos/admLanz";

	}
	
	@PostMapping(value = "/registroGrupo")
	public String registrarGrupo(@Valid @ModelAttribute("grupos") PgPrgPlnGrupo grupos,
	@RequestParam("idPgPrgPlnVersiones") Long idPgPrgPlnVersiones, BindingResult result, HttpSession session, Model model) throws IOException {
		
		if (result.hasErrors()) {
			model.addAttribute("opet", "registro");
			getDesplegarListasComunes(model, session);
			 
			return "uap/usic/siga/pg/administrarLanzamientos/admLanz";
		}
	
	PgPrgPlnVersiones version;
	version=mService.buscarVersionPorIdGET(idPgPrgPlnVersiones);
	grupos.setVersion(version);
	mService.registrarGrupo(grupos);
	getDesplegarListasComunes(model, session);

	List<PgPrgModulos> modulos = prgServicios.listaModulosPorIdPlan(idPgPrgPlnVersiones);
	modulos.forEach(e -> System.out.println(e.getModulo()));

	for(var modulo : modulos){
		var em = new EjecucionesModulos();
		em.setModulo(modulo);
		em.setGrupo(grupos);
		modServicios.registrarEjecucionesModulos(em);
	}

	model.addAttribute("version", mService.buscarVersionPorIdGET(idPgPrgPlnVersiones));
	model.addAttribute("lGrupoVersion", mService.ListaGruposPorIdPgPrgPlnVersiones(idPgPrgPlnVersiones));
	model.addAttribute("opet", "listaVersion");

	return "uap/usic/siga/pg/administrarLanzamientos/admLanz";

	}

	@PostMapping(value = "/ListaGrupoPorVersion")
	public String inicioListaGrupo(@ModelAttribute("grupo") PgPrgPlnGrupo grupo,
	@RequestParam("idPgPrgPlnVersiones") Long idPgPrgPlnVersiones,
	Model model, HttpSession session) {

		model.addAttribute("version", mService.buscarVersionPorIdGET(idPgPrgPlnVersiones));
		model.addAttribute("lGrupoVersion", mService.ListaGruposPorIdPgPrgPlnVersiones(idPgPrgPlnVersiones));
	    model.addAttribute("opet", "listaVersion");
	    getDesplegarListasComunes(model, session);
	    return "uap/usic/siga/pg/administrarLanzamientos/admLanz";

	}
    
    //=========================Listas Comunes======================================
    public void getDesplegarListasComunes(Model model, HttpSession session) {
    	
		model.addAttribute("urlNProy", "scdNuevoPersona");
    	model.addAttribute("lProgramas", mService.listaProgramasJPLQ());
		model.addAttribute("lPlanes", mService.listaPlanesJPLQ()); //Muestra los Select del Formulario
		model.addAttribute("lModalidad", mService.listaModalidadJPLQ());
		model.addAttribute("lGrado", mService.listaGradosAcademicosJPLQ());
		model.addAttribute("lVersiones", mService.listaPgPrgPlnVersionesJPLQ());
		model.addAttribute("lGrupos", mService.listaPgPrgPlnGrupoJPLQ());
    }


}
