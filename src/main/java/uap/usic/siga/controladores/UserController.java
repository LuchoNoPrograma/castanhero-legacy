package uap.usic.siga.controladores;

import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.dto.UserDto;
import uap.usic.siga.entidades.AuthorityType;
import uap.usic.siga.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.EmailService;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private InstitucionesServicios iServicio;

    @Autowired
    private AdministrativosServicios aServicio;

    @Autowired
    private EmailService emailService;

    
    
    @GetMapping(value = "/signupForm")
    public String inicioUsuarios(@ModelAttribute("usuarios") UserDto userDto, Model model) {
        model.addAttribute("busqueda", "find");
        getDesplegarListasComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    @RequestMapping(value = {"/list", ""})
    public String users(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    @PostMapping(value = "/formNuevoUsuario")
    public String addUser(@ModelAttribute("userDto") UserDto userDto, Model model) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }
    
    @PostMapping(value = "/registrarUsuario")
    // public ModelAndView saveUser(ModelAndView modelAndView, @ModelAttribute("userDto") @Valid final UserDto userDto, BindingResult bindingResult, HttpServletRequest request, Errors errors){
    	public String saveUser(Model model, ModelAndView modelAndView, @ModelAttribute("userDto") @Valid final UserDto userDto, BindingResult bindingResult, HttpServletRequest request, Errors errors){

    	Personas personas = pServicio.buscarPersonasPorIdPersona(userDto.getPersonas().getIdPersona());
        Usuarios emailExists = userService.findByEmail(personas.getEmail());
        Usuarios userNameExists = userService.findByUsername(userDto.getUsername());
        userDto.setName(userDto.getUsername());
        userDto.setEmail(personas.getEmail());
        model.addAttribute("busqueda", "find");
       // model.addAttribute("email",personas.getEmail());
       
        //System.out.println(emailExists);

        
        if (emailExists != null) {
            modelAndView.setViewName("website/register");
            bindingResult.rejectValue("email", "emailAlreadyExists");
        }

        if (userNameExists!= null) {
            modelAndView.setViewName("website/register");
            bindingResult.rejectValue("username", "usernameAlreadyExists");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("website/register");
        }
        else { // new user so we create user and send confirmation e-mail

            Usuarios user = userService.createNewAccount(userDto);
            // Disable user until they click on confirmation link in email

            user.setEnabled(true);
            userService.save(user);

		
            /*String appUrl = request.getScheme() + "://" + request.getServerName();

            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Registration Confirmation");
            registrationEmail.setText("Please confirm the registration");
            registrationEmail.setFrom("email@email.com");

            emailService.sendEmail(registrationEmail);*/

            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to "
                                    + userDto.getEmail());
            modelAndView.setViewName("website/registered");
        }

        //return modelAndView;
        
        
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
        
    }

/*
    @PostMapping(value = "/signup")
    public String addUser(@ModelAttribute("usuarios") @Valid Usuarios user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model);
            model.addAttribute("operation", "reg");
            return "addUser";
        }
        Usuarios dbUser = this.userService.findByEmail(user.getEmail());
        if (dbUser != null) {
            getDesplegarListasComunes(model);
            model.addAttribute("operation", "reg");
            return "addUser";
        }

        Usuarios bUsuarios = pServicio.buscarUsuariosPorIdPersonaIdEstado(user.getPersonas().getIdPersona(), "A");
        if (bUsuarios == null) {

            user.setDateCreated(new Date());
            Set<Roles> roles = Collections.singleton(new Roles(AuthorityType.ROLE_USER));
            user.setRoles(roles);
            userService.addUser(user);
            getDesplegarListasComunes(model);
            model.addAttribute("busqueda", "find");
        } else {
            model.addAttribute("mensage", "err");
            model.addAttribute("operation", "reg");
            getDesplegarListasComunes(model);
            return "addUser";
        }
        return "addUser";

    }
*/
    //Modificar Usuarios
    @PostMapping(value = "/inicioModificarUsuarios")
    public String modificarUsuarios(@ModelAttribute("usuarios") Usuarios usuarios, Model model,
            @RequestParam("id") Long id) {

        model.addAttribute("usuarios", uServicio.buscarUsuariosPorIdUsuario(id));
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    @PostMapping(value = "/confirmarModificacionUsuarios")
    public String confirmarModificacionUsuarios(@ModelAttribute("usuarios") @Valid Usuarios user, BindingResult result, Model model
    ) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model);
            model.addAttribute("operation", "mod");
            return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
        }
        /*
        Usuarios dbUser = this.userService.getUserByUsername(user.getUsuario());
        if (dbUser != null) {
            getDesplegarListasComunes(model);
            model.addAttribute("mensage", "err");
            model.addAttribute("operation", "mod");
            return "addUser";
        }
*/
        //user.setDateCreated(new Date()); -- Coco
  /*
        Set<Roles> roles = Collections.singleton(new Roles(AuthorityType.ROLE_USER));
        user.setRoles(roles);
        */
         user.setDateCreated(new Date());
       // userService.updateUser(user);
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");

        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
        //Fin Modificar usuarios
    }

    //Inicio Eliminar usuarios
    @PostMapping(value = "/inicioEliminarUsuarios")
    public String inicioEliminarUsuarios(@ModelAttribute("usuarios") Usuarios usuarios, Model model,
            @RequestParam("id") Long id) {

        model.addAttribute("usuarios", uServicio.buscarUsuariosPorIdUsuario(id));
        model.addAttribute("operation", "elim");
        getDesplegarListasComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    /*
    @PostMapping(value = "/confirmarEliminacionUsuario")
    public String confirmarEliminacionUsuarios(@ModelAttribute("usuarios") @Valid Usuarios user, BindingResult result, Model model) {

        Set<Roles> roles = Collections.singleton(new Roles(AuthorityType.ROLE_USER));
        user.setRoles(roles);
         user.setDateCreated(new Date());
        user.setIdEstado("X");
        uServicio.eliminarUsuarios(user);
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");

        return "addUser";

    }
    */
//------------------------Inicio Usuario Administrador----------------------------

    @RequestMapping(value = "/addAdminForm", method = RequestMethod.GET)
    public String addAdmin(@ModelAttribute("usuarios") Usuarios usuarios, Model model) {
        getDesplegarListasComunes(model);
        model.addAttribute("busquedaAdm", "findAdm");

        model.addAttribute("lPersonas", aServicio.listarPersonaslAdministrativos());

        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    @PostMapping(value = "/formNuevoUsuarioAdm")
    public String addUserAdm(@ModelAttribute("usuarios") Usuarios usuarios, Model model) {

        model.addAttribute("operationAdm", "regAdm");
        getDesplegarListasComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }
/*
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addAdmin(@Valid @ModelAttribute("usuarios") Usuarios user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("operationAdm", "regAdm");
            getDesplegarListasComunes(model);
            return "addUser";
        }
        
        Usuarios dbUser = this.userService.getUserByUsername(user.getUsuario());
        if (dbUser != null) {
            model.addAttribute("operationAdm", "regAdm");
            model.addAttribute("mensage", "err");
            getDesplegarListasComunes(model);
            return "addUser";
        }

        user.setDateCreated(new Date());

        Set<Roles> roles = Collections.singleton(new Roles(AuthorityType.ROLE_ADMIN));
        user.setRoles(roles);
        userService.addUser(user);
        model.addAttribute("busquedaAdm", "findAdm");
        getDesplegarListasComunes(model);
        return "addUser";
    }
*/
    @RequestMapping(value = "/adminList", method = RequestMethod.GET)
    public String getAllAdmins(Model model) {
      //  model.addAttribute("userList", userService.getAllAdmins());
        return "allUsers";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    InsSedes getUserProfile(@PathVariable("id") long id) {
        return iServicio.buscarInsSedes(id);
        //return "Controller";
    }

    public void getDesplegarListasComunes(Model model) {

        model.addAttribute("lRoles", uServicio.listarRoles());
    //    model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("lPersonas", pServicio.listarPersonalAdministrativoActivos());
        model.addAttribute("lUsuarios", uServicio.listarUsuariosRegistrados());
        model.addAttribute("urlNuevoUAdm", "formNuevoUsuarioAdm");
        model.addAttribute("registrarUAdm", "addAdmin");
    }
}