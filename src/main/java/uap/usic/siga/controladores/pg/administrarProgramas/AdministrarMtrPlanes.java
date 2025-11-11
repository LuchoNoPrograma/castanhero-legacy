package uap.usic.siga.controladores.pg.administrarProgramas;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uap.usic.siga.entidadesPg.PgMtrPlanes;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/aMtrPlanes")
public class AdministrarMtrPlanes {
    
    @Autowired
	private ProgramasService mService;
	
	//==========================Lista de Programas Registrados=======================
    @GetMapping("/inicioMtrPlanes")
	public String inicioMtrplanesString( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "uap/usic/siga/pg/administrarMtrPlanes/admMtrPlanes";
	}
	//===============================================================================
	
	//===========================Registro Programas==================================
	@PostMapping(value = "/formMtrPlanes")
	public String inicioFormPlanes(@ModelAttribute("planes") PgMtrPlanes planes , Model model, HttpSession session) {
	    model.addAttribute("operation", "reg");
	    getDesplegarListasComunes(model, session);
	    return "uap/usic/siga/pg/administrarMtrPlanes/admMtrPlanes";
	}

    //============================ Guardado Bd =====================================
    @PostMapping(value = "/registroMtrPlanes")
	public String registrarFacultades(@Valid @ModelAttribute("planes") PgMtrPlanes planes, BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("operation", "reg");
			getDesplegarListasComunes(model, session);
			 
			return "uap/usic/siga/pg/administrarMtrPlanes/admMtrPlanes";
		}

	mService.registrarMtrPlanesSet(planes);

	model.addAttribute("busqueda", "find");
	getDesplegarListasComunes(model, session);
	return "uap/usic/siga/pg/administrarMtrPlanes/admMtrPlanes";
	}
    //=============================================================================


    //=========================Listas Comunes======================================
    public void getDesplegarListasComunes(Model model, HttpSession session) {
    	
		model.addAttribute("urlNProy", "scdNuevoPersona");
    	model.addAttribute("lProgramas", mService.listaProgramasJPLQ());
		model.addAttribute("lPlanes", mService.listaPlanesJPLQ()); //Muestra los Select del Formulario
		model.addAttribute("lModalidad", mService.listaModalidadJPLQ());
		model.addAttribute("lGrado", mService.listaGradosAcademicosJPLQ());
        model.addAttribute("lMtrPlanes", mService.listaMtrPlanesJPLQ());
        model.addAttribute("lMtrMaterias", mService.listaMtrMateriasJPLQ());
    }
}
