package uap.usic.siga.controladores.pg.administrarProgramas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uap.usic.siga.dto.pgDto.ProgramasResponse;
import uap.usic.siga.entidadesPg.PgPrgArancel;
import uap.usic.siga.entidadesPg.PgPrgPlanes;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/aProgramas")
public class AdministrarProgramas {
	
	@Autowired
	private ProgramasService mService;

	private String URL="uap/usic/siga/pg/administrarProgramas/admProgramas";
	private String REDIRECT="redirect:/aProgramas/inicioProgramas";
	private String REDIRECTMODAL="redirect:/aProgramas/inicioModal";
	
	//==========================Lista de Programas Registrados=======================
    @GetMapping("/inicioModal")
	public String inicioProgramasModalString(@ModelAttribute("programas") Programas programa ,Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "uap/usic/siga/pg/administrarProgramas/admProgramaModal";
	}
	//==========================Lista de Programas Registrados=======================
    @GetMapping("/inicioProgramas")
	public String inicioProgramasString( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return URL;
	}
	//===============================================================================
	//==========================Lista de Programas Registrados=======================
    @GetMapping("/inicioListag")
	public String inicioGeneralString( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "uap/usic/siga/pg/administrarProgramas/admIndexPrograma";
	}
	//===============================================================================
	
	//===========================Registro Programas==================================
	@PostMapping(value = "/formProgramas")
	public String inicioFormFacultades(@ModelAttribute("programas") Programas programa, Model model, HttpSession session) {
	    model.addAttribute("operation", "reg");
	    getDesplegarListasComunes(model, session);
	    return URL;
	}
	
	// ================================== Guardar Datos ===============================
	@PostMapping(value = "/registroProgramas")
	public String registrarFacultades(@Valid @ModelAttribute("programas")  Programas programa, BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("operation", "reg");
			getDesplegarListasComunes(model, session);
			 
			return URL;
		}

	short aux;
	model.addAttribute(aux=programa.getCantidadModulos());
	programa.setDisponibilidad(aux);
	mService.registrarProgramaSET(programa);

	model.addAttribute("busqueda", "find");
	model.addAttribute("mensaje", "se registro correctamente l programa");
	getDesplegarListasComunes(model, session);

	return REDIRECTMODAL;

	}
	//=====================================Fin Registro Programas=====================
	//===================================== Inicio Modificacion =====================
	@GetMapping("/inicioModificarProgramas={idPrograma}")
    public String inicioModificarProgramas(@ModelAttribute("programas") Programas programas,
	@PathVariable("idPrograma")Long idPrograma, Model model, HttpSession session) throws IOException {

        model.addAttribute("programas",mService.buscarProgramaPorIdPrograma(idPrograma));
        model.addAttribute("urlUp", "actualizarProgramas");
        model.addAttribute("operation", "modif");
        getDesplegarListasComunes(model, session);
        return URL;
    }
	

	@PostMapping(value = "/actualizarProgramas")
    public String actualizarProgramas(@Valid Programas programas, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/pg/administrarProgramas/admProgramas";
        }
        
        mService.modificarPersonas(programas);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return REDIRECT;
    }

	//===============================================================================
	//===================================== Inicio Eliminar =====================
	 @GetMapping("/inicioEliminarPrograma={idPrograma}")
	public String inicioEliminarPrograma(@ModelAttribute("programas") Programas programas,
	@PathVariable("idPrograma")Long idPrograma, Model model, HttpSession session) throws IOException {

		model.addAttribute("programas",mService.buscarProgramaPorIdPrograma(idPrograma));
		model.addAttribute("urlUp", "actualizarPrograma");
		model.addAttribute("operation", "delet");
		getDesplegarListasComunes(model, session);

		return URL;
	}

	@PostMapping(value = "/eliminarProgramas")
	public String eliminarProgramas(@Valid Programas programas, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

	    if (result.hasErrors()) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("operation", "reg");
	        return URL;
	    }

		PgPrgPlanes plan;
		ArrayList lista;
		lista=(ArrayList) mService.ListaPorProgramas(programas.getIdPrograma());
	
		for (int i=0;i<lista.size();i++) {
			plan=(PgPrgPlanes) lista.get(i);
			plan.setEstado('X');
			mService.modificarPlanes(plan);
		}

		programas.setEstado('X');
		mService.modificarPersonas(programas);
				
		getDesplegarListasComunes(model, session);
		model.addAttribute("busqueda", "find");
		
		return REDIRECT;
		
	}
	
	//===========================Registro Arancel ==================================
	@GetMapping(value = "/formArancel={idPrograma}")
	public String inicioForArancel(@ModelAttribute("aranceles") PgPrgArancel arancel, 
	@PathVariable("idPrograma")Long idPrograma,Model model, HttpSession session) {
		model.addAttribute("programas",mService.buscarProgramaPorIdPrograma(idPrograma));
		model.addAttribute("operation", "reg-arancel");
		getDesplegarListasComunes(model, session);
		return URL;
	}

	@PostMapping(value = "/registroArancel")
	public String registrarArancel(@Valid @ModelAttribute("aranceles")  PgPrgArancel arancel, BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("operation", "reg-arancel");
			getDesplegarListasComunes(model, session);
			 
			return URL;
		}
		mService.registrararancelSET(arancel);

		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return REDIRECT;
	}

    
	//=========================Listas Comunes======================================
	public void getDesplegarListasComunes(Model model, HttpSession session) {	
		model.addAttribute("urlNProy", "scdNuevoPersona");
    	model.addAttribute("lProgramas", mService.listaProgramasJPLQ());
		model.addAttribute("lPlanes", mService.listaPlanesJPLQ()); //Muestra los Select del Formulario
		model.addAttribute("lModalidad", mService.listaModalidadJPLQ());
		model.addAttribute("lGrado", mService.listaGradosAcademicosJPLQ());
		model.addAttribute("lArancel", mService.listaAranceles());
	}
	//============API============
}