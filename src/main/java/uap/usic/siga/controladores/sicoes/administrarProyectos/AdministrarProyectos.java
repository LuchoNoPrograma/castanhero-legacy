package uap.usic.siga.controladores.sicoes.administrarProyectos;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
import uap.usic.siga.servicios.UserService;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 * Rectorado - USIC
 * Fecha: 2020-01-20
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/aProyectos")
public class AdministrarProyectos {
    
    @Autowired
    private SicoesServicios scServicios;
   
    @Autowired
    private UsuariosServicios uServicio;
    
    @Autowired
    private InstitucionesServicios iServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/inicioProyectos")
    public String formInicioProyectos(HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
    }

    @PostMapping(value = "/scdNuevoProyecto")
    public String scsNuevoProyecto(@ModelAttribute("scsProyectos") ScsProyectos scsProyectos, HttpSession session, Model model) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
    }
    
    @PostMapping(value = "/registrarProyectosSicoes")
    public String registrarProyectosSicoes(@Valid ScsProyectos scsProyectos, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
        }
        
        Usuarios usuarios = userService.findByIdEagerly((Long) session.getAttribute("idUsr"));
        scsProyectos.setUsuarios(usuarios);
        ScsProyectos gScsProyectos = scServicios.registrarScsProyectos(scsProyectos);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
    }

    @PostMapping("/inicioModificarProyecto")
    public String inicioModificarProyecto(@ModelAttribute("scsProyectos") ScsProyectos scsProyectos,
           @RequestParam("idScsProyecto") Long idScsProyecto, Model model, HttpSession session) throws IOException {

        model.addAttribute("scsProyectos", scServicios.buscarScsProyectosPorIdScsProyecto(idScsProyecto));
        model.addAttribute("urlUp", "actualizarContratacion");
        model.addAttribute("operation", "modif");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
    }

    @PostMapping(value = "/actualizarProyectosSicoes")
    public String actualizarProyectosSicoes(@Valid ScsProyectos scsProyectos, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
        }
          
        Usuarios usuarios = userService.findByIdEagerly((Long) session.getAttribute("idUsr"));
        scsProyectos.setUsuarios(usuarios);
        scServicios.actualizarScsProyectos(scsProyectos);;
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
    }

    @PostMapping("/inicioEliminarProyecto")
    public String inicioEliminarProyecto(@ModelAttribute("scsProyectos") ScsProyectos scsProyectos,
           @RequestParam("idScsProyecto") Long idScsProyecto, Model model, HttpSession session) throws IOException {

        model.addAttribute("scsProyectos", scServicios.buscarScsProyectosPorIdScsProyecto(idScsProyecto));
        model.addAttribute("urlUp", "actualizarContratacion");
        model.addAttribute("operation", "delet");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
    }

    @PostMapping(value = "/eliminarProyectosSicoes")
    public String eliminarProyectosSicoes(@Valid ScsProyectos scsProyectos, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
        }
        
  
        Usuarios usuarios = userService.findByIdEagerly((Long) session.getAttribute("idUsr"));
        scsProyectos.setUsuarios(usuarios);
        scsProyectos.setIdEstado("X");
        scServicios.actualizarScsProyectos(scsProyectos);;
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/administrarProyectos/administrarProyectos";
    }

    
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        model.addAttribute("regProy", "registrarProyectosSicoes");
        model.addAttribute("lScsProyectos", scServicios.listarScsProyectos());
        model.addAttribute("lUnidades", iServicios.listarUnidadesFuncionales());
        

    }

   
}
