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

import uap.usic.siga.entidadesPg.PgPrgModulos;
import uap.usic.siga.entidadesPg.PgPrgPlanes;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/aModulos")
public class AdministrarModulos {

    @Autowired
	private ProgramasService mService;

    private String URL="uap/usic/siga/pg/administrarModulos/admModulos";
    private String REDIREC="redirect:/aModulos/listaPorPorPlan=";
	
	//==========================Lista de Programas Registrados=======================
    @GetMapping("/inicioModulos")
	public String inicioModulosString( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "uap/usic/siga/pg/administrarModulos/admModulos";
	}
	//===============================================================================
	//=================================== Registro  Modulos =========================
	@GetMapping("/FronModulos={idPgPrgPlanes}")
    public String inicioModulo(@ModelAttribute("modulos") PgPrgModulos modulos,
    @PathVariable("idPgPrgPlanes")Long idPgPrgPlanes, Model model, HttpSession session) throws IOException {

        vistaModulos(idPgPrgPlanes, model, session);
        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);

        return URL;
    }

	@PostMapping(value = "/registroModulos")
	public String registrarModulosP(@Valid @ModelAttribute("modulos") PgPrgModulos modulos,
     @RequestParam("idPgPrgPlanes") Long idPgPrgPlanes,
     @RequestParam("idPrograma") Long idPrograma,
     BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("operation", "reg");
			getDesplegarListasComunes(model, session);
			 
			return "uap/usic/siga/pg/administrarModulos/admModulos";
		}
    
    Programas programa;
    model.addAttribute(programa=mService.buscarProgramaPorIdPrograma(idPrograma));
    Short num;
    Short aux;
    model.addAttribute(aux =programa.getCantidadModulos());
    model.addAttribute(num =programa.getDisponibilidad());

    if (num == 0) {
        
        model.addAttribute("listaP", mService.ListaPorPlanes(idPgPrgPlanes));
	    model.addAttribute("planes", mService.buscarPlanesPorIdPlanes(idPgPrgPlanes));
        model.addAttribute("programas", mService.buscarProgramaPorIdPrograma(idPrograma));
	    model.addAttribute("listaPlanes", "list");
	    model.addAttribute("mensaje", "Llego al Limite de Modulos Disponibles ");
	    getDesplegarListasComunes(model, session);
	    return "uap/usic/siga/pg/administrarModulos/admModulos";
    }

    Long tipo;
    model.addAttribute(tipo =programa.getGrado().getId_grado());

    String prefijo;

    if(tipo == 5){
        prefijo = "10";
    }else{
        if(tipo == 2 ){
        prefijo = "20";
        }else{
            if(tipo == 3){
                prefijo = "30";
            }else{
                if(tipo == 4){
                    prefijo = "40";
                }else{
                    prefijo = "1000";
                }
            }
        }
     }


    if((aux-num) == 0){
        String sigla;
        model.addAttribute(sigla =programa.getSigla()+prefijo+"1");
    }

    String sigla;
    model.addAttribute(sigla =programa.getSigla()+prefijo+((aux-num)+1));

    Short nueva = (short) (num -1);

    programa.setDisponibilidad(nueva);
    mService.modificarPersonas(programa);

    modulos.setSigla(sigla);
	mService.registrarPrgModulosSet(modulos);

    vistaModulos(idPgPrgPlanes, model, session);
	model.addAttribute("listaPlanes", "list");
	model.addAttribute("mensaje", "Se Registro Correctamente el modulo: "+modulos.getModulo());
	getDesplegarListasComunes(model, session);

	return REDIREC+idPgPrgPlanes;

	}
	//========================== Inicio Modificacion ===============================

	@GetMapping("/inicioModificarModulos={idModulo}")
    public String inicioModificarModulos(@ModelAttribute("pgPrgModulos") PgPrgModulos pgPrgModulos,
    @PathVariable("idModulo")Long idModulo, Model model, HttpSession session) throws IOException {

        pgPrgModulos=mService.buscarNodulosPorIdModulo(idModulo);
        PgPrgPlanes plan;
        Long idPlan=pgPrgModulos.getPlan().getIdPgPrgPlanes();

        plan=mService.buscarPlanesPorIdPlanes(idPlan);
        vistaModulos(idPlan, model, session);

        model.addAttribute("modulos", pgPrgModulos);
		model.addAttribute("urlUp", "actualizarModulos");
        model.addAttribute("operation", "modif");
        getDesplegarListasComunes(model, session);
        return URL;

    }

	@PostMapping(value = "/actualizarModulos")
    public String actualizarModulos(@Valid PgPrgModulos pgPrgModulos,
    @RequestParam("idPgPrgPlanes") Long idPgPrgPlanes,
    @RequestParam("idPrograma") Long idPrograma, 
    BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/pg/administrarModulos/admModulos";
        }
        PgPrgPlanes plan;
        plan=mService.buscarPlanesPorIdPlanes(idPgPrgPlanes);
        pgPrgModulos.setPlan(plan);
        mService.modificarModulosSET(pgPrgModulos);
        getDesplegarListasComunes(model, session);
        vistaModulos(idPgPrgPlanes, model, session);
		model.addAttribute("listaPlanes", "list");

        return REDIREC+idPgPrgPlanes;
    }

    //==================================== Lista Por Plan ====================================
	@GetMapping("/listaPorPorPlan={idPgPrgPlanes}")
    public String inicioPorPlanes(@PathVariable("idPgPrgPlanes")Long idPgPrgPlanes, 
    Model model, HttpSession session) throws IOException {
    
        vistaModulos(idPgPrgPlanes, model, session);
		model.addAttribute("listaPlanes", "list");

        return URL;
    }

	//===============================================================================

    private void getDesplegarListasComunes(Model model, HttpSession session) {
        model.addAttribute("urlNProy", "scdNuevoPersona");
    	model.addAttribute("lProgramas", mService.listaProgramasJPLQ());
		model.addAttribute("lPlanes", mService.listaPlanesJPLQ()); //Muestra los Select del Formulario
		model.addAttribute("lModalidad", mService.listaModalidadJPLQ());
		model.addAttribute("lGrado", mService.listaGradosAcademicosJPLQ());
        model.addAttribute("lModulos", mService.listaPrgModulosJPLQ());
    }
    
    public void vistaModulos(@PathVariable("idPgPrgPlanes")Long idPgPrgPlanes, Model model, HttpSession session){

        PgPrgPlanes plan;
        plan=mService.buscarPlanesPorIdPlanes(idPgPrgPlanes);
        Long idPro=plan.getPrograma().getIdPrograma();
        model.addAttribute("listaP", mService.ListaPorPlanes(idPgPrgPlanes));
		model.addAttribute("planes", mService.buscarPlanesPorIdPlanes(idPgPrgPlanes));
        model.addAttribute("programas", mService.buscarProgramaPorIdPrograma(idPro));

    }
}
