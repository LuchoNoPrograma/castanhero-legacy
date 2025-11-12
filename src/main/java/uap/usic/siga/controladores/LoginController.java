package uap.usic.siga.controladores;


import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.seguridad.IAuthenticationFacade;

import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.usertype.UserVersionType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.dto.UserDto;
import uap.usic.siga.entidades.MnuFunciones;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.servicios.AdministradorServicios;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UserDtoService;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.servicios.userDetails.UserDetailsImpl;

/**
 * Created by Freddy Morales 2022-09-30..
 */


@SessionAttributes({"idMnuTipoFuncion","userId","currentUser", "currentUserId", "lTiposEnlaces", "personas", "lEnlaces", "lTiposFunciones", "gestion", "periodo", "nombreSis", "lFunciones", "idPersona","dModal","userModal","dModal2","idUsr"})   
@Controller
@RequestMapping("")
public class LoginController {

    private static final Logger log = LogManager.getLogger(LoginController.class);

    @Autowired
    private PersonasServicios pS;

    @Autowired
    private AdministrativosServicios aServicios;

    @Autowired
    private MenuesServicios mServicios;

    @Autowired
    private PersonasServicios pServicios;

    @Autowired
    private UsuariosServicios uServicios;
   
    @Autowired
    private AdministradorServicios adServicio;
    
    @Autowired
    private IAuthenticationFacade auth; //enticationFacade;

    @Autowired
    private UserDtoService userDtoServ;

    @GetMapping(value = "/login")
    public String login (){
        return "website/login";
    }
    
    // Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "website/login";
    }
    
    @GetMapping(value = "/logout")
    public String logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/index";
    }      
    
    @GetMapping(value = "/postLogin")
    public String postLogin(Model model, HttpSession session) {
    	  
    	UserDto userDto = userDtoServ.findByUsername(auth.getAuthentication().getName());
    	model.addAttribute("id", userDto.getPersonas());
    	session.setAttribute("idPersona", userDto.getPersonas().getIdPersona());
    	session.setAttribute("idUsr", userDto.getId());
    	session.setAttribute("personas", userDto.getPersonas());
    	  
    	  //----------- Asiganr Funcioens 
    	  
    	  Personas bPersona = pServicios.buscarPersonaPorIdUsuario(userDto.getId());
          PnlCargos bPnlCargos = aServicios.buscarCargosPorIdPersonaGET(bPersona.getIdPersona());
          session.setAttribute("userId", userDto.getId());
          session.setAttribute("idUser", userDto.getId());
          session.setAttribute("currentUserId", userDto.getId());
          session.setAttribute("sNombres", bPersona.getNombres());
          session.setAttribute("sPaterno", bPersona.getPaterno());
          session.setAttribute("sMaterno", bPersona.getMaterno());
          
          session.setAttribute("dModal", "inicio");
          
          
          model.addAttribute("personas", bPersona);
          model.addAttribute("yeti", "modal");

          session.setAttribute("userModal", mServicios.listarMenusTiposFuncionesPorIdPersona(bPersona.getIdPersona()));
          session.setAttribute("lTiposFunciones", mServicios.listarMenusTiposFuncionesPorIdPersona(bPersona.getIdPersona()));
          
          model.addAttribute("lFunciones", mServicios.listarMenusTiposFuncionesPorIdPersona(bPersona.getIdPersona()));
          model.addAttribute("lTiposFuncionaso", mServicios.listarMenusTiposFuncionesPorIdPersona(bPersona.getIdPersona()));
        
          // session.setAttribute("nombreSis", "system.name");
          //session.setAttribute("mFunciones", mServicios.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(1l, 1l));
         // session.setAttribute("userModal", "verd");
    	  
          //return "redirect:/index";
          return "website/index";
   }
    
    @RequestMapping("/error-forbidden")
    public String errorForbidden() {
        return "error-forbidden";
    }
    
    /* 
    @RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    public String postLogin(Model model, HttpSession session) {
        log.info("postLogin()");

        // read principal out of security context and set it to session
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        Usuarios loggedInUser = ((UserDetailsImpl) authentication.getPrincipal()).getAuthorities().   getUserDetails();

        Personas bPersona = pServicios.buscarPersonaPorIdUsuario(loggedInUser.getId());
        PnlCargos bPnlCargos = aServicios.buscarCargosPorIdPersonaGET(bPersona.getIdPersona());
        model.addAttribute("currentUserId", loggedInUser.getId());
        model.addAttribute("currentUser", loggedInUser.getSurname());
        session.setAttribute("userId", loggedInUser.getId());
        session.setAttribute("sNombres", loggedInUser.getPersonas().getNombres());
        session.setAttribute("sPaterno", loggedInUser.getPersonas().getPaterno());
        session.setAttribute("sMaterno", loggedInUser.getPersonas().getMaterno());
        model.addAttribute("personas", pS.buscarPersonaPorIdUsuario(loggedInUser.getId()));
        model.addAttribute("userModal", "verd");

        model.addAttribute("lFunciones", mServicios.listarMenusTiposFuncionesPorIdPersona(bPersona.getIdPersona()));
        model.addAttribute("lTiposFunciones", mServicios.listarMenusTiposFuncionesPorIdPersona(bPersona.getIdPersona()));
        //  session.setAttribute("nombreSis", "system.name");
        //session.setAttribute("mFunciones", mServicios.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(1l, 1l));
        session.setAttribute("userModal", "verd");

        return "redirect:/index";
    }
    
    private void validatePrinciple(Object principal) {
        if (!(principal instanceof PdfUserDetails)) {
            throw new IllegalArgumentException("Principal can not be null!");
        }
    }
*/
    @PostMapping(value = "/listarMenues")
    public String seleccionarFuncion(@RequestParam("idMnuTipoFuncion") Long idMnuTipoFuncion, Model model, HttpSession session) {
        log.info("seleccionarFuncion()");

        session.removeAttribute("dModal");
        UserDto userDto = userDtoServ.findByUsername(auth.getAuthentication().getName());
        Personas bPersona = pServicios.buscarPersonaPorIdUsuario(userDto.getId());

        
        SisAdministrador bSisAdministrador = adServicio.buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(idMnuTipoFuncion, bPersona.getIdPersona());
        
        model.addAttribute("lTiposEnlaces", mServicios.listarMenuesTiposEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));
        model.addAttribute("lEnlaces", mServicios.listarMenuesEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));
        session.setAttribute("lEnlaces", mServicios.listarMenuesEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));
        session.setAttribute("lTiposEnlaces", mServicios.listarMenuesTiposEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));
                		
        session.setAttribute("mFunciones", mServicios.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(bPersona.getIdPersona(), idMnuTipoFuncion, bSisAdministrador.getIdSisAdministrador()));
        session.setAttribute("userModal", "falso");
        session.setAttribute("gestion", bSisAdministrador.getGestion());
        session.setAttribute("periodo", bSisAdministrador.getPeriodo());
        session.setAttribute("nombreSiss", bSisAdministrador.getNombreSis());
        session.setAttribute("sisAdministrador", bSisAdministrador);
        session.setAttribute("idMnuTipoFuncion", idMnuTipoFuncion);
        
        session.setAttribute("dModal2", "stop");
        session.removeAttribute("dModal");
        
        MnuFunciones bFuncionesSis = mServicios.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(bPersona.getIdPersona(), idMnuTipoFuncion, bSisAdministrador.getIdSisAdministrador());
        model.addAttribute("bFuncionesSis", bFuncionesSis);
        model.addAttribute("SisAdminitrador", bFuncionesSis);          

        //return "redirect:/welcome";
        return "website/index";
    }

}

