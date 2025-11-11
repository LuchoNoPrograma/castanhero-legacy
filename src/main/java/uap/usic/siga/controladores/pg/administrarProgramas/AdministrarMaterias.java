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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.entidadesPg.PgMtrMaterias;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/aMaterias")
public class AdministrarMaterias {

    @Autowired
	private ProgramasService mService;


    //==========================Lista de Materias Registradas=======================
    @GetMapping("/inicioMaterias")
	public String inicioProgramasString( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "uap/usic/siga/pg/administrarMaterias/admMaterias";
	}
	//===============================================================================
    //===========================Registro Materias==================================
	@PostMapping(value = "/formMaterias")
	public String inicioFormMaterias(@ModelAttribute("materias") PgMtrMaterias materias , Model model, HttpSession session) {
	    model.addAttribute("operation", "reg");
	    getDesplegarListasComunes(model, session);
	    return "uap/usic/siga/pg/administrarMaterias/admMaterias";
	}
	// ================================== Guardar Datos ===============================
	@PostMapping(value = "/registroMaterias")
	public String registrarMaterias(@Valid @ModelAttribute("materias") PgMtrMaterias materias, BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("operation", "reg");
			getDesplegarListasComunes(model, session);
			 
			return "uap/usic/siga/pg/administrarMaterias/admMaterias";
		}

	mService.registrarMtrMateriasSet(materias);

	model.addAttribute("busqueda", "find");
	getDesplegarListasComunes(model, session);
	return "uap/usic/siga/pg/administrarMaterias/admMaterias";
	}
	//=====================================Fin Registro Materias=====================
	//===================================== Inicio Modificacion =====================
	@PostMapping("/inicioModificarMaterias")
    public String inicioModificarMaterias(@ModelAttribute("pgMtrMaterias") PgMtrMaterias pgPrgMaterias,
           @RequestParam("idMateria") Long idMateria, Model model, HttpSession session) throws IOException {

        model.addAttribute("materias", mService.buscarMateriasPorIdMateria(idMateria));
        model.addAttribute("urlUp", "actualizarMaterias");
        model.addAttribute("operation", "modif");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarMaterias/admMaterias";
    }

	@PostMapping(value = "/actualizarMaterias")
    public String actualizarMaterias(@Valid PgMtrMaterias pgPrgMaterias, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/pg/administrarMaterias/admMaterias";
        }
        
        mService.modificarMateriasSET(pgPrgMaterias);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/pg/administrarMaterias/admMaterias";
    }

	//===============================================================================


    //=========================Listas Comunes========================================
    public void getDesplegarListasComunes(Model model, HttpSession session) {
    	
		model.addAttribute("urlNProy", "scdNuevoPersona");
    	model.addAttribute("lProgramas", mService.listaProgramasJPLQ());
		model.addAttribute("lPlanes", mService.listaPlanesJPLQ()); //Muestra los Select del Formulario
		model.addAttribute("lModalidad", mService.listaModalidadJPLQ());
		model.addAttribute("lGrado", mService.listaGradosAcademicosJPLQ());
        model.addAttribute("lMaterias", mService.listaMtrMateriasJPLQ());
        model.addAttribute("lTiposMt", mService.listaMtrTipoMateriasJPLQ());
		model.addAttribute("lMtrPlanes", mService.listaMtrPlanesJPLQ());
    }
    
}
