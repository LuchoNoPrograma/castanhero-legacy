package uap.usic.siga.controladores.pg.administrarProgramas;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
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

import uap.usic.siga.entidadesPg.PgPrgPlanes;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/aPlanes")
public class AdministrarPlanes {
	
	@Autowired
	private ProgramasService mService;

	private String URL="uap/usic/siga/pg/administrarPlanes/admPlanes";
	private String REDIREC="redirect:/aPlanes/listaPorProgramas=";
	
	//==========================Lista de Programas Registrados=======================
    @GetMapping("/inicioPlanes")
	public String inicioProgramasString( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "uap/usic/siga/pg/administrarPlanes/admPlanes";
	}
	//===============================================================================
	//===========================Registro Programas==================================
	@GetMapping(value = "/formPlanes={idPrograma}")
	public String inicioFormPlanes(@ModelAttribute("planes") PgPrgPlanes planes,
	@PathVariable("idPrograma")Long idPrograma, Model model, HttpSession session) {
		model.addAttribute("programas",mService.buscarProgramaPorIdPrograma(idPrograma));
	    model.addAttribute("operation", "reg");
	    getDesplegarListasComunes(model, session);
	    return URL;
	}
	// ================================== Guardar Datos ===============================
	@PostMapping(value = "/registroPlanes")
	public String registrarFacultades(@Valid @ModelAttribute("pgPrgPlanes") PgPrgPlanes pgPrgPlanes, 
		BindingResult result, HttpSession session, Model model) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("operation", "reg");
			getDesplegarListasComunes(model, session);
			 
			return URL;
		}
	Long idPro;
	idPro=pgPrgPlanes.getPrograma().getIdPrograma();
	mService.registPrgPlanesSet(pgPrgPlanes);

	model.addAttribute("listaPlanes", "list");
	getDesplegarListasComunes(model, session);
	vistaProgramas(idPro, model, session);
	return REDIREC+idPro;
	}
	//=====================================Fin Registro Programas=====================
	//===================================== Inicio Modificacion =====================
	@GetMapping("/inicioModificarPlanes={idPgPrgPlanes}")
    public String inicioModulo(@ModelAttribute("planes") PgPrgPlanes planes,
		@PathVariable("idPgPrgPlanes")Long idPgPrgPlanes, Model model, HttpSession session) throws IOException {

		planes = mService.buscarPlanesPorIdPlanes(idPgPrgPlanes);
		Long idPro = planes.getPrograma().getIdPrograma();

        model.addAttribute("planes", planes);
		model.addAttribute("programas", mService.buscarProgramaPorIdPrograma(idPro));
        model.addAttribute("urlUp", "actualizarPlanes");
        model.addAttribute("operation", "modif");
        getDesplegarListasComunes(model, session);
        return URL;
    }

	@PostMapping(value = "/actualizarPlanes")
    public String actualizarProgramas(@Valid PgPrgPlanes planes, BindingResult result, 
	HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "modif");
            return "uap/usic/siga/pg/administrarPlanes/admPlanes";
        }
        
		Long idPro=planes.getPrograma().getIdPrograma();
        mService.modificarPlanes(planes);

		vistaProgramas(idPro, model, session);
		model.addAttribute("listaPlanes", "list");
        getDesplegarListasComunes(model, session);
        return REDIREC+idPro;
    }

	//===============================================================================
	//===================================== Inicio Eliminar =====================
	 @GetMapping("/inicioEliminarPlanes={idPgPrgPlanes}")
	public String inicioEliminarPlanes(@ModelAttribute("pgPrgPlanes") PgPrgPlanes pgPrgPlanes,
	@PathVariable("idPgPrgPlanes")Long idPgPrgPlanes, Model model, HttpSession session) throws IOException {

		pgPrgPlanes = mService.buscarPlanesPorIdPlanes(idPgPrgPlanes);
		Long idPro=pgPrgPlanes.getPrograma().getIdPrograma();
		
		model.addAttribute("planes", mService.buscarPlanesPorIdPlanes(idPgPrgPlanes));
		model.addAttribute("programas", mService.buscarProgramaPorIdPrograma(idPro));
	    model.addAttribute("urlUp", "actualizarPlanes");
	    model.addAttribute("operation", "delet");
	    getDesplegarListasComunes(model, session);
	    
		return "uap/usic/siga/pg/administrarPlanes/admPlanes";
	}

	    @PostMapping(value = "/eliminarPlanes")
	    public String eliminarProgramas(@Valid PgPrgPlanes pgPrgPlanes, BindingResult result, 
		HttpSession session, Model model, HttpServletRequest request) throws IOException {

	        if (result.hasErrors()) {
	            getDesplegarListasComunes(model, session);
	            model.addAttribute("operation", "reg");
	            return "uap/usic/siga/pg/administrarPlanes/admPlanes";
	        }
	  
			Long idPro;
			idPro=pgPrgPlanes.getPrograma().getIdPrograma();
	        
	        pgPrgPlanes.setEstado('X');
	        mService.modificarPlanes(pgPrgPlanes);
	        
	        getDesplegarListasComunes(model, session);
	        vistaProgramas(idPro, model, session);
			model.addAttribute("listaPlanes", "list");

	        return REDIREC+idPro;
	    }

	//============================ Lista Plan Por Programa ========================

	@GetMapping("/listaPorProgramas={idPrograma}")
    public String inicioPorPrograma(@ModelAttribute("programa") Programas programa,
	@PathVariable("idPrograma")Long idPrograma, Model model, HttpSession session) throws IOException {
        model.addAttribute("listaPla", mService.ListaPorProgramas(idPrograma));
		model.addAttribute("programas", mService.buscarProgramaPorIdPrograma(idPrograma));
		model.addAttribute("listaPlanes", "list");
        return URL;
    }
    
    //=========================Listas Comunes======================================
    public void getDesplegarListasComunes(Model model, HttpSession session) {
    	
		model.addAttribute("urlNProy", "scdNuevoPersona");
    	model.addAttribute("lProgramas", mService.listaProgramasJPLQ());
		model.addAttribute("lPlanes", mService.listaPlanesJPLQ()); //Muestra los Select del Formulario
		model.addAttribute("lModalidad", mService.listaModalidadJPLQ());
		model.addAttribute("lGrado", mService.listaGradosAcademicosJPLQ());
    }

	public void vistaProgramas(@PathVariable("idPrograma")Long idPrograma, Model model, HttpSession session){

		model.addAttribute("listaPla", mService.ListaPorProgramas(idPrograma));
		model.addAttribute("programas", mService.buscarProgramaPorIdPrograma(idPrograma));

	}


}
