package uap.usic.siga.web.admin;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.dto.RoleDto;
import uap.usic.siga.entidades.Roles;
import uap.usic.siga.servicios.RoleService;

/**
 * Controlador de administración de roles del sistema
 * Gestiona CRUD de roles y permisos
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/adminPage")
public class RolesController {

    private static final String REDIRECT_ROLES = "redirect:/adminPage/roles";
    private static final String VIEW_EDIT_ROLE = "adminPage/role/editRole";
    private static final String VIEW_NEW_ROLE = "adminPage/role/newRole";
    private static final String VIEW_ROLES = "adminPage/role/roles";

    @Autowired
    private RoleService roleService;

    /**
     * Lista todos los roles del sistema
     */
    @GetMapping("/roles")
    public ModelAndView listarRoles() {
        ModelAndView modelAndView = new ModelAndView(VIEW_ROLES);
        modelAndView.addObject("roles", roleService.findAll());
        return modelAndView;
    }

    /**
     * Muestra el formulario para editar un rol existente
     */
    @GetMapping("/roles/{id}")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        Optional<Roles> rol = roleService.findById(id);

        if (!rol.isPresent()) {
            return new ModelAndView(REDIRECT_ROLES);
        }

        ModelAndView modelAndView = new ModelAndView(VIEW_EDIT_ROLE);
        modelAndView.addObject("role", rol.get());
        return modelAndView;
    }

    /**
     * Actualiza un rol existente
     */
    @PostMapping("/roles/{id}")
    public String actualizarRol(
            @PathVariable Long id,
            @ModelAttribute("oldRole") @Valid Roles rol,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        Optional<Roles> rolPersistido = roleService.findById(id);
        if (!rolPersistido.isPresent()) {
            return REDIRECT_ROLES;
        }

        List<Roles> todosLosRoles = roleService.findAll();

        // Validar que el nombre no esté en uso por otro rol
        boolean nombreEnUso = roleService.checkIfRoleNameIsTaken(todosLosRoles, rol, rolPersistido.get());

        if (nombreEnUso) {
            bindingResult.rejectValue("name", "roleNameAlreadyExists", "El nombre del rol ya existe");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("role", rol);
            return VIEW_EDIT_ROLE;
        }

        roleService.save(rol);
        redirectAttributes.addFlashAttribute("mensaje", "Rol actualizado exitosamente");
        redirectAttributes.addFlashAttribute("roleHasBeenUpdated", true);

        return REDIRECT_ROLES;
    }

    /**
     * Muestra el formulario para crear un nuevo rol
     */
    @GetMapping("/roles/newRole")
    public String mostrarFormularioNuevoRol(Model model) {
        model.addAttribute("newRole", new RoleDto());
        return VIEW_NEW_ROLE;
    }

    /**
     * Guarda un nuevo rol en el sistema
     */
    @PostMapping("/roles/newRole")
    public String guardarNuevoRol(
            @ModelAttribute("newRole") @Valid Roles nuevoRol,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        // Validar que el nombre no exista
        boolean nombreExiste = roleService.findByName(nuevoRol.getName()) != null;

        if (nombreExiste) {
            bindingResult.rejectValue("name", "roleNameAlreadyExists", "El nombre del rol ya existe");
        }

        if (bindingResult.hasErrors()) {
            return VIEW_NEW_ROLE;
        }

        roleService.save(nuevoRol);
        redirectAttributes.addFlashAttribute("mensaje", "Rol creado exitosamente");
        redirectAttributes.addFlashAttribute("roleHasBeenSaved", true);

        return REDIRECT_ROLES;
    }
}
