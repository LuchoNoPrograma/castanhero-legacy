package uap.usic.siga.web.autenticacion;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.dto.UserDto;
import uap.usic.siga.entidades.MnuFunciones;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.seguridad.IAuthenticationFacade;
import uap.usic.siga.servicios.AdministradorServicios;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UserDtoService;

/**
 * Controlador de autenticación del sistema
 * Gestiona login, logout y configuración de sesión de usuario
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@SessionAttributes({
    "idMnuTipoFuncion", "userId", "currentUser", "currentUserId",
    "lTiposEnlaces", "personas", "lEnlaces", "lTiposFunciones",
    "gestion", "periodo", "nombreSis", "lFunciones", "idPersona",
    "dModal", "userModal", "dModal2", "idUsr"
})
@Controller
@RequestMapping("")
public class LoginController {

    private static final Logger log = LogManager.getLogger(LoginController.class);

    @Autowired
    private PersonasServicios personasServicios;

    @Autowired
    private AdministrativosServicios administrativosServicios;

    @Autowired
    private MenuesServicios menuesServicios;

    @Autowired
    private AdministradorServicios administradorServicios;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private UserDtoService userDtoService;

    /**
     * Muestra la página de inicio de sesión
     */
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "website/login";
    }

    /**
     * Muestra el formulario de login con mensaje de error
     */
    @GetMapping("/login-error")
    public String mostrarErrorLogin(Model model) {
        model.addAttribute("loginError", true);
        return "website/login";
    }

    /**
     * Cierra la sesión del usuario actual
     */
    @GetMapping("/logout")
    public String cerrarSesion(SessionStatus sessionStatus) {
        SecurityContextHolder.getContext().setAuthentication(null);
        sessionStatus.setComplete();
        return "redirect:/index";
    }

    /**
     * Configura la sesión después del login exitoso
     * Carga datos del usuario, menús y funciones disponibles
     */
    @GetMapping("/postLogin")
    public String configurarSesionPostLogin(Model model, HttpSession session) {
        UserDto userDto = userDtoService.findByUsername(authenticationFacade.getAuthentication().getName());

        // Configurar datos de persona y usuario
        Personas persona = userDto.getPersonas();
        session.setAttribute("idPersona", persona.getIdPersona());
        session.setAttribute("idUsr", userDto.getId());
        session.setAttribute("userId", userDto.getId());
        session.setAttribute("idUser", userDto.getId());
        session.setAttribute("currentUserId", userDto.getId());
        session.setAttribute("personas", persona);
        session.setAttribute("sNombres", persona.getNombres());
        session.setAttribute("sPaterno", persona.getPaterno());
        session.setAttribute("sMaterno", persona.getMaterno());
        session.setAttribute("dModal", "inicio");

        // Cargar funciones y menús disponibles
        model.addAttribute("personas", persona);
        model.addAttribute("id", persona);
        model.addAttribute("yeti", "modal");

        session.setAttribute("userModal", menuesServicios.listarMenusTiposFuncionesPorIdPersona(persona.getIdPersona()));
        session.setAttribute("lTiposFunciones", menuesServicios.listarMenusTiposFuncionesPorIdPersona(persona.getIdPersona()));

        model.addAttribute("lFunciones", menuesServicios.listarMenusTiposFuncionesPorIdPersona(persona.getIdPersona()));
        model.addAttribute("lTiposFuncionaso", menuesServicios.listarMenusTiposFuncionesPorIdPersona(persona.getIdPersona()));

        return "website/index";
    }

    /**
     * Selecciona y carga los menús de una función específica
     */
    @PostMapping("/listarMenues")
    public String seleccionarFuncion(
            @RequestParam("idMnuTipoFuncion") Long idMnuTipoFuncion,
            Model model,
            HttpSession session) {

        log.info("Seleccionando función: {}", idMnuTipoFuncion);

        session.removeAttribute("dModal");

        UserDto userDto = userDtoService.findByUsername(authenticationFacade.getAuthentication().getName());
        Personas persona = personasServicios.buscarPersonaPorIdUsuario(userDto.getId());

        // Buscar sistema administrador
        SisAdministrador sisAdministrador = administradorServicios
            .buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(idMnuTipoFuncion, persona.getIdPersona());

        // Cargar menús y enlaces
        model.addAttribute("lTiposEnlaces", menuesServicios.listarMenuesTiposEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));
        model.addAttribute("lEnlaces", menuesServicios.listarMenuesEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));

        session.setAttribute("lEnlaces", menuesServicios.listarMenuesEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));
        session.setAttribute("lTiposEnlaces", menuesServicios.listarMenuesTiposEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));

        // Configurar funciones del sistema
        MnuFunciones funcionesSis = menuesServicios.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(
            persona.getIdPersona(), idMnuTipoFuncion, sisAdministrador.getIdSisAdministrador());

        session.setAttribute("mFunciones", funcionesSis);
        session.setAttribute("userModal", "falso");
        session.setAttribute("gestion", sisAdministrador.getGestion());
        session.setAttribute("periodo", sisAdministrador.getPeriodo());
        session.setAttribute("nombreSiss", sisAdministrador.getNombreSis());
        session.setAttribute("sisAdministrador", sisAdministrador);
        session.setAttribute("idMnuTipoFuncion", idMnuTipoFuncion);
        session.setAttribute("dModal2", "stop");

        model.addAttribute("bFuncionesSis", funcionesSis);
        model.addAttribute("SisAdminitrador", funcionesSis);

        return "website/index";
    }

    /**
     * Página de acceso denegado
     */
    @GetMapping("/error-forbidden")
    public String mostrarErrorAccesoDenegado() {
        return "error-forbidden";
    }
}
