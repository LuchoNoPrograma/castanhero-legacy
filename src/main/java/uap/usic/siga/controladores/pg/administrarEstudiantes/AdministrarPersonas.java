package uap.usic.siga.controladores.pg.administrarEstudiantes;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.ScsContrataciones;
import uap.usic.siga.entidades.ScsProyectos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SicoesServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 * Rectorado - USIC
 * Fecha: 2020-01-20
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/aPersonas")
public class AdministrarPersonas {
    
    @Autowired
    private SicoesServicios scServicios;
   
    @Autowired
    private UsuariosServicios uServicio;
    
    @Autowired
    private InstitucionesServicios iServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @GetMapping("/inicioPersonas")
    public String formInicioPersonas(HttpSession session, Model model , @Param("palabraClave") String palabraClave) {
    	
    	model.addAttribute("palabraClave", palabraClave);
		if(palabraClave != null) {
			model.addAttribute("lPersonas", pServicios.listarPersonasPorFiltroGET(palabraClave));	
		}else {
			model.addAttribute("lPersonas", pServicios.listarPersonas());	
		}
    	
       
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/pg/administrarEstudiantes/administrarPersonas";
    }

    @PostMapping(value = "/scdNuevoPersona")
    public String scsNuevoPersona(@ModelAttribute("personas") Personas personas, HttpSession session, Model model) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarEstudiantes/administrarPersonas";
    }
    
    @PostMapping(value = "/registrarPersonas")
    public String registrarPersonas(@ModelAttribute("personas")@Valid Personas personas, BindingResult result, HttpSession session, Model model) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/pg/administrarEstudiantes/administrarPersonas";
        }
        
         pServicios.registrarPersonas(personas);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/pg/administrarEstudiantes/administrarPersonas";
    }

    @PostMapping("/inicioModificarPersonas")
    public String inicioModificarPersonas(@ModelAttribute("personas") Personas personas,
           @RequestParam("idPersona") Long idPersona, Model model, HttpSession session) throws IOException {

        model.addAttribute("personas",pServicios.buscarPersonasPorIdPersona(idPersona));
        //model.addAttribute("urlUp", "actualizarPersonas");
        model.addAttribute("operation", "modif");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarEstudiantes/administrarPersonas";
    }

    @PostMapping(value = "/actualizarPersonas")
    public String actualizarPersonas(@Valid Personas personas, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/pg/administrarEstudiantes/administrarPersonas";
        }
        
        
        pServicios.modificarPersonas(personas);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/pg/administrarEstudiantes/administrarPersonas";
    }

    @PostMapping("/inicioEliminarPersonas")
    public String inicioEliminarPersonas(@ModelAttribute("personas") Personas personas,
           @RequestParam("idPersona") Long idPersona, Model model, HttpSession session) throws IOException {

    	 model.addAttribute("personas",pServicios.buscarPersonasPorIdPersona(idPersona));
        model.addAttribute("urlUp", "actualizarContratacion");
        model.addAttribute("operation", "elim");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarEstudiantes/administrarPersonas";
    }

    @PostMapping(value = "/eliminarPersonas")
    public String eliminarPersonas(@Valid Personas personas, BindingResult result, HttpSession session, Model model,
            @RequestParam("idPersona") Long idPersona) throws IOException {

    
        
       
        personas.setIdEstado("X");
        pServicios.eliminarPersonas(personas);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/pg/administrarEstudiantes/administrarPersonas";
    }

    
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        model.addAttribute("urlNProy", "scdNuevoPersona");
        model.addAttribute("regProy", "registrarPersonas");
        model.addAttribute("lPersonas", pServicios.listarPersonas());
        model.addAttribute("lPais", pServicios.listarPaises());
        model.addAttribute("lTiposSexos", pServicios.listarTiposSexos());
        model.addAttribute("lEstadoCivil", pServicios.listarEstadoCivil());
        model.addAttribute("lCiExpedido", pServicios.listarCedulaIdentidadexpedidos());
        model.addAttribute("lGradoAcademico", pServicios.listarGradosAcademicos());    
        // model.addAttribute("urlModProy", "inicioModificarPersonas");
        // model.addAttribute("confModProy", "actualizarPersonas");
        // model.addAttribute("iniElimProy", "actualizarPersonas");
        // model.addAttribute("confElimProy", "eliminarPersonas");

    }

   
}
