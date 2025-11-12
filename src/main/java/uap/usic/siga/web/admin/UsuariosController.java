package uap.usic.siga.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.dto.UserDto;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.EmailService;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UserService;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 * Controlador de administración de usuarios del sistema
 * Gestiona CRUD de usuarios normales y administradores
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UsuariosController {

    private final UserService userService;

    private final UsuariosServicios usuariosServicios;

    private final PersonasServicios personasServicios;

    private final InstitucionesServicios institucionesServicios;

    private final AdministrativosServicios administrativosServicios;

    private final EmailService emailService;

    /**
     * Muestra el formulario principal de gestión de usuarios
     */
    @GetMapping("/signupForm")
    public String mostrarFormularioUsuarios(Model model) {
        model.addAttribute("busqueda", "find");
        model.addAttribute("usuarios", new UserDto());
        cargarDatosComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    /**
     * Lista todos los usuarios del sistema
     */
    @GetMapping({"/list", ""})
    public String listarUsuarios(Model model) {
        model.addAttribute("userList", userService.findAll());
        cargarDatosComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    /**
     * Muestra el formulario para crear un nuevo usuario
     */
    @PostMapping("/formNuevoUsuario")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("operation", "reg");
        model.addAttribute("userDto", new UserDto());
        cargarDatosComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    /**
     * Registra un nuevo usuario en el sistema
     */
    @PostMapping("/registrarUsuario")
    public String registrarUsuario(
            @ModelAttribute("userDto") @Valid UserDto userDto,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {

        Personas persona = personasServicios.buscarPersonasPorIdPersona(userDto.getPersonas().getIdPersona());

        // Validaciones
        Usuarios emailExistente = userService.findByEmail(persona.getEmail());
        if (emailExistente != null) {
            bindingResult.rejectValue("email", "emailAlreadyExists", "El email ya está registrado");
        }

        Usuarios usernameExistente = userService.findByUsername(userDto.getUsername());
        if (usernameExistente != null) {
            bindingResult.rejectValue("username", "usernameAlreadyExists", "El nombre de usuario ya existe");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("busqueda", "find");
            cargarDatosComunes(model);
            return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
        }

        // Crear usuario
        userDto.setName(userDto.getUsername());
        userDto.setEmail(persona.getEmail());

        Usuarios nuevoUsuario = userService.createNewAccount(userDto);
        nuevoUsuario.setEnabled(true);
        userService.save(nuevoUsuario);

        redirectAttributes.addFlashAttribute("mensaje", "Usuario registrado exitosamente");
        model.addAttribute("busqueda", "find");
        cargarDatosComunes(model);

        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    /**
     * Muestra el formulario para modificar un usuario existente
     */
    @PostMapping("/inicioModificarUsuarios")
    public String mostrarFormularioModificar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("usuarios", usuariosServicios.buscarUsuariosPorIdUsuario(id));
        model.addAttribute("operation", "mod");
        cargarDatosComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    /**
     * Confirma la modificación de un usuario
     */
    @PostMapping("/confirmarModificacionUsuarios")
    public String confirmarModificacionUsuario(
            @ModelAttribute("usuarios") @Valid Usuarios usuario,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "mod");
            cargarDatosComunes(model);
            return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
        }

        usuario.setDateCreated(new Date());
        // userService.updateUser(usuario);

        redirectAttributes.addFlashAttribute("mensaje", "Usuario modificado exitosamente");
        model.addAttribute("busqueda", "find");
        cargarDatosComunes(model);

        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    /**
     * Muestra el formulario de confirmación para eliminar usuario
     */
    @PostMapping("/inicioEliminarUsuarios")
    public String mostrarFormularioEliminar(
            @RequestParam("id") Long id,
            Model model) {

        model.addAttribute("usuarios", usuariosServicios.buscarUsuariosPorIdUsuario(id));
        model.addAttribute("operation", "elim");
        cargarDatosComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    // ========== Administradores ==========

    /**
     * Muestra el formulario de gestión de usuarios administradores
     */
    @GetMapping("/addAdminForm")
    public String mostrarFormularioAdministradores(Model model) {
        model.addAttribute("busquedaAdm", "findAdm");
        model.addAttribute("lPersonas", administrativosServicios.listarPersonaslAdministrativos());
        model.addAttribute("usuarios", new Usuarios());
        cargarDatosComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    /**
     * Muestra el formulario para crear nuevo administrador
     */
    @PostMapping("/formNuevoUsuarioAdm")
    public String mostrarFormularioNuevoAdministrador(Model model) {
        model.addAttribute("operationAdm", "regAdm");
        cargarDatosComunes(model);
        return "uap/usic/siga/administrarUsuarios/administrarUsuarios";
    }

    /**
     * Lista todos los usuarios administradores
     */
    @GetMapping("/adminList")
    public String listarAdministradores(Model model) {
        // model.addAttribute("userList", userService.getAllAdmins());
        return "allUsers";
    }

    /**
     * Obtiene el perfil de un usuario (API JSON)
     */
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody InsSedes obtenerPerfilUsuario(@PathVariable("id") Long id) {
        return institucionesServicios.buscarInsSedes(id);
    }

    /**
     * Carga datos comunes necesarios en todas las vistas
     */
    private void cargarDatosComunes(Model model) {
        model.addAttribute("lRoles", usuariosServicios.listarRoles());
        model.addAttribute("lPersonas", personasServicios.listarPersonalAdministrativoActivos());
        model.addAttribute("lUsuarios", usuariosServicios.listarUsuariosRegistrados());
        model.addAttribute("urlNuevoUAdm", "formNuevoUsuarioAdm");
        model.addAttribute("registrarUAdm", "addAdmin");
    }
}
