package uap.usic.siga.controladores.pg.administrarEstudiantes;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
import uap.usic.siga.entidadesPg.Estudiantes;
import uap.usic.siga.servicios.EstudiantesServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.ProgramasService;
import uap.usic.siga.servicios.SicoesServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 * Rectorado - USIC
 * Fecha: 2020-01-20
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/aEstudiantes")
public class AdministrarEstudiantes {
    

   
    @Autowired
    private UsuariosServicios uServicio;
    
    @Autowired
    private InstitucionesServicios iServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @Autowired
    private ProgramasService proServicios;
    
    @Autowired
    private EstudiantesServicios estServicios;
    
    @GetMapping("/inicioEstudiantes")
    public String formInicioEstudiantes(HttpSession session, Model model , @Param("palabraClave") String palabraClave) {
    	
    	model.addAttribute("palabraClave", palabraClave);
		if(palabraClave != null) {
			model.addAttribute("lEstudiantes", estServicios.listarEstudiantesPorFiltro(palabraClave));	
		}else {
			model.addAttribute("lEstudiantes", estServicios.listarEstudiantes());	
		}
    	
       
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/pg/administrarEstudiantes/administrarEstudiantes";
    }

    @PostMapping(value = "/scdNuevoEstudiante")
    public String scsNuevoEstudiante(@ModelAttribute("estudiantes") Estudiantes estudiantes, HttpSession session, Model model) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarEstudiantes/administrarEstudiantes";
    }
    
    @PostMapping(value = "/registrarEstudiante")
    public String registrarEstudiante(@ModelAttribute("estudiantes")@Valid Estudiantes estudiantes, BindingResult result, HttpSession session, Model model) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "redirect:/aEstudiantes/inicioEstudiantes";
        }
        
         estServicios.registrarEstudiante(estudiantes);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "redirect:/aEstudiantes/inicioEstudiantes";
    }

    @PostMapping("/inicioModificarEstudiantes")
    public String inicioModificarEstudiantes(@ModelAttribute("estudiantes") Estudiantes estudiantes,
           @RequestParam("idEstudiante") Long idEstudiante, Model model, HttpSession session) throws IOException {

        model.addAttribute("estudiantes",estServicios.buscarEstudiantePorIdEstudiante(idEstudiante));
        //model.addAttribute("urlUp", "actualizarPersonas");
        model.addAttribute("operation", "modif");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarEstudiantes/administrarEstudiantes";
    }

    @PostMapping(value = "/actualizarEstudiantes")
    public String actualizarEstudiantes(@Valid Estudiantes estudiantes, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "redirect:/aEstudiantes/inicioEstudiantes";
        }
        
        
        estServicios.modificarEstudiante(estudiantes);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "redirect:/aEstudiantes/inicioEstudiantes";
    }

    @PostMapping("/inicioEliminarEstudiantes")
    public String inicioEliminarEstudiantes(@ModelAttribute("estudiantes") Estudiantes estudiantes,
    		@RequestParam("idEstudiante") Long idEstudiante, Model model, HttpSession session) throws IOException {

    	model.addAttribute("estudiantes",estServicios.buscarEstudiantePorIdEstudiante(idEstudiante));
        model.addAttribute("urlUp", "actualizarContratacion");
        model.addAttribute("operation", "elim");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarEstudiantes/administrarEstudiantes";
    }

    @PostMapping(value = "/eliminarEstudiantes")
    public String eliminarPersonas(@Valid Estudiantes estudiantes, BindingResult result, HttpSession session, Model model,
            @RequestParam("idEstudiante") Long idEstudiante) throws IOException {

    
        
       
    	estudiantes.setEstado('X');
        estServicios.eliminarEstudiante(estudiantes);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "redirect:/aEstudiantes/inicioEstudiantes";
    }

    
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        model.addAttribute("urlNProy", "scdNuevoPersona");
        model.addAttribute("regProy", "registrarPersonas");
        model.addAttribute("lPersonas", pServicios.listarPersonas());
        model.addAttribute("lProgramas", proServicios.listaProgramasJPLQ());
        model.addAttribute("lTiposEstudiantes", estServicios.listarTipoEstudiante());
        model.addAttribute("lEstudiantes", estServicios.listarEstudiantes());   
        // model.addAttribute("urlModProy", "inicioModificarPersonas");
        // model.addAttribute("confModProy", "actualizarPersonas");
        // model.addAttribute("iniElimProy", "actualizarPersonas");
        // model.addAttribute("confElimProy", "eliminarPersonas");

    }

   
}
