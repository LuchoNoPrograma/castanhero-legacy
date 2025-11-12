package uap.usic.siga.web.autenticacion;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.dto.UserDto;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.EmailService;
import uap.usic.siga.servicios.UserService;

/**
 * Controlador de registro de nuevos usuarios
 * Gestiona el proceso de creación de cuentas en el sistema
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    /**
     * Muestra el formulario de registro
     */
    @GetMapping("/register")
    public String mostrarFormularioRegistro(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "website/register";
    }

    /**
     * Procesa el registro de un nuevo usuario
     * Valida que el email y username no existan previamente
     */
    @PostMapping("/submit-registration")
    public String registrarUsuario(
            @ModelAttribute("userDto") @Valid UserDto userDto,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Validar que el email no esté registrado
        Usuarios emailExistente = userService.findByEmail(userDto.getEmail());
        if (emailExistente != null) {
            bindingResult.rejectValue("email", "emailAlreadyExists", "El email ya está registrado");
        }

        // Validar que el username no esté registrado
        Usuarios usernameExistente = userService.findByUsername(userDto.getUsername());
        if (usernameExistente != null) {
            bindingResult.rejectValue("username", "usernameAlreadyExists", "El nombre de usuario ya existe");
        }

        // Si hay errores, volver al formulario
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "website/register";
        }

        // Crear y activar nueva cuenta
        Usuarios nuevoUsuario = userService.createNewAccount(userDto);
        nuevoUsuario.setEnabled(true);
        userService.save(nuevoUsuario);

        redirectAttributes.addFlashAttribute("mensaje", "Usuario registrado exitosamente");
        return "redirect:/login";
    }
}
