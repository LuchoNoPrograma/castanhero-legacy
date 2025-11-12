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
import uap.usic.siga.entidadesPg.Matriculas;
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
@RequestMapping("/aMatriculas")
public class AdministrarMatriculas {
    

   
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
    
    @GetMapping("/inicioMatriculas")
    public String formInicioMatriculas(HttpSession session, Model model ) {
    	
    	
		model.addAttribute("lMatriculas", estServicios.listarMatriculado());	
       
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/pg/administrarEstudiantes/administrarMatriculas";
    }

    @PostMapping(value = "/scdNuevoMatriculas")
    public String scsNuevoMatriculas(@ModelAttribute("matriculas") Matriculas matriculas, HttpSession session, Model model) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarEstudiantes/administrarMatriculas";
    }
    
    @PostMapping(value = "/registrarMatriculas")
    public String registrarMatriculas(@ModelAttribute("matriculas")@Valid Matriculas matriculas, BindingResult result, HttpSession session, Model model) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "redirect:/aMatriculas/inicioMatriculas";
        }
        
         estServicios.registrarMatricula(matriculas);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "redirect:/aMatriculas/inicioMatriculas";
    }

    @PostMapping("/inicioModificarMatriculas")
    public String inicioModificarMatriculas(@ModelAttribute("matriculas") Matriculas matriculas,
           @RequestParam("idMatricula") Long idMatricula, Model model, HttpSession session) throws IOException {

        model.addAttribute("matriculas",estServicios.buscarMatriculasPorIdMatriculas(idMatricula));
        //model.addAttribute("urlUp", "actualizarPersonas");
        model.addAttribute("operation", "modif");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarEstudiantes/administrarMatriculas";
    }

    @PostMapping(value = "/actualizarMatriculas")
    public String actualizarMatriculas(@Valid Matriculas matriculas, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "redirect:/aMatriculas/inicioMatriculas";
        }
        
        
        estServicios.modificarMatricula(matriculas);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "redirect:/aMatriculas/inicioMatriculas";
    }

    @PostMapping("/inicioEliminarMatriculas")
    public String inicioEliminarMatriculas(@ModelAttribute("matriculas") Matriculas matriculas,
    		@RequestParam("idMatricula") Long idMatricula, Model model, HttpSession session) throws IOException {

    	model.addAttribute("matriculas",estServicios.buscarMatriculasPorIdMatriculas(idMatricula));
        model.addAttribute("urlUp", "actualizarContratacion");
        model.addAttribute("operation", "elim");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/pg/administrarEstudiantes/administrarMatriculas";
    }

    @PostMapping(value = "/eliminarMatriculas")
    public String eliminarMatriculas(@Valid Matriculas matriculas, BindingResult result, HttpSession session, Model model,
    		@RequestParam("idMatricula") Long idMatricula) throws IOException {

    
        
       
    	matriculas.setEstado('X');
        estServicios.eliminarMatricula(matriculas);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "redirect:/aMatriculas/inicioMatriculas";
    }

    
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        model.addAttribute("urlNProy", "scdNuevoPersona");
        model.addAttribute("regProy", "registrarPersonas");
        model.addAttribute("lEstudiantes", estServicios.listarEstudiantes());
        model.addAttribute("lMatriculas", estServicios.listarMatriculado());
        model.addAttribute("lTipoDescuentos", estServicios.listarTiposDescuentos());   
        // model.addAttribute("urlModProy", "inicioModificarPersonas");
        // model.addAttribute("confModProy", "actualizarPersonas");
        // model.addAttribute("iniElimProy", "actualizarPersonas");
        // model.addAttribute("confElimProy", "eliminarPersonas");

    }

   
}
