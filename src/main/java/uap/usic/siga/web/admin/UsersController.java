package uap.usic.siga.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import uap.usic.siga.dto.UserDto;
import uap.usic.siga.dto.UserUpdateDto;
import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.paginaciones.Pager;
import uap.usic.siga.servicios.RoleService;
import uap.usic.siga.servicios.UserDtoService;
import uap.usic.siga.servicios.UserService;
import uap.usic.siga.servicios.UserUpdateDtoService;
import uap.usic.siga.servicios.usuariosBusquedas.UserFinder;
import uap.usic.siga.servicios.usuariosBusquedas.UserSearchErrorResponse;
import uap.usic.siga.servicios.usuariosBusquedas.UserSearchParameters;
import uap.usic.siga.servicios.usuariosBusquedas.UserSearchResult;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static uap.usic.siga.paginaciones.InitialPagingSizes.*;

/**
 * Controlador de administración de usuarios
 * Gestiona CRUD de usuarios con paginación y búsqueda
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/adminPage")
@Slf4j
@RequiredArgsConstructor
public class UsersController {

    private static final String VIEW_EDIT_USER = "adminPage/user/editUser";
    private static final String VIEW_NEW_USER = "adminPage/user/newUser";
    private static final String VIEW_USERS = "adminPage/user/users";
    private static final String REDIRECT_USERS = "redirect:/adminPage/users";

    private final UserService userService;

    private final RoleService roleService;

    private final UserUpdateDtoService userUpdateDtoService;

    private final UserDtoService userDtoService;

    private final UserFinder userFinder;

    private final UserSearchErrorResponse userSearchErrorResponse;

    /**
     * Lista todos los usuarios con paginación y búsqueda
     */
    @GetMapping("/users")
    public ModelAndView listarUsuarios(UserSearchParameters parametrosBusqueda) {
        ModelAndView modelAndView = new ModelAndView(VIEW_USERS);

        int tamanioPagina = parametrosBusqueda.getPageSize().orElse(INITIAL_PAGE_SIZE);
        int paginaSeleccionada = (parametrosBusqueda.getPage().orElse(0) < 1)
                ? INITIAL_PAGE
                : (parametrosBusqueda.getPage().get() - 1);

        PageRequest pageRequest = of(paginaSeleccionada, tamanioPagina, Sort.by(ASC, "id"));
        UserSearchResult resultadoBusqueda = new UserSearchResult();

        // Búsqueda o listado completo
        if (parametrosBusqueda.getPropertyValue().isEmpty() || parametrosBusqueda.getPropertyValue().get().isEmpty()) {
            resultadoBusqueda.setUserPage(userDtoService.findAllPageable(pageRequest));
        } else {
            resultadoBusqueda = userFinder.searchUsersByProperty(pageRequest, parametrosBusqueda);

            if (resultadoBusqueda.isNumberFormatException()) {
                return userSearchErrorResponse.respondToNumberFormatException(resultadoBusqueda, modelAndView);
            }

            if (resultadoBusqueda.getUserPage().getTotalElements() == 0) {
                modelAndView = userSearchErrorResponse.respondToEmptySearchResult(modelAndView, pageRequest);
                resultadoBusqueda.setUserPage(userDtoService.findAllPageable(pageRequest));
            }

            modelAndView.addObject("usersProperty", parametrosBusqueda.getUsersProperty().get());
            modelAndView.addObject("propertyValue", parametrosBusqueda.getPropertyValue().get());
        }

        // Configurar paginación
        Pager pager = new Pager(
                resultadoBusqueda.getUserPage().getTotalPages(),
                resultadoBusqueda.getUserPage().getNumber(),
                BUTTONS_TO_SHOW,
                resultadoBusqueda.getUserPage().getTotalElements());

        modelAndView.addObject("pager", pager);
        modelAndView.addObject("users", resultadoBusqueda.getUserPage());
        modelAndView.addObject("selectedPageSize", tamanioPagina);
        modelAndView.addObject("pageSizes", PAGE_SIZES);

        return modelAndView;
    }

    /**
     * Muestra el formulario para editar un usuario
     */
    @GetMapping("/users/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        UserUpdateDto userUpdateDto = userUpdateDtoService.findById(id);
        List<Roles> todosLosRoles = roleService.findAll();

        userUpdateDto.setRoles(userService.getAssignedRolesList(userUpdateDto));

        model.addAttribute("userUpdateDto", userUpdateDto);
        model.addAttribute("allRoles", todosLosRoles);

        return VIEW_EDIT_USER;
    }

    /**
     * Actualiza un usuario existente
     */
    @PostMapping("/users/{id}")
    public String actualizarUsuario(
            @PathVariable Long id,
            @ModelAttribute("oldUser") @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        Optional<Usuarios> usuarioPersistido = userService.findById(id);
        if (!usuarioPersistido.isPresent()) {
            return REDIRECT_USERS;
        }

        List<Roles> todosLosRoles = roleService.findAll();

        // Validaciones
        boolean emailEnUso = userService.findByEmailAndIdNot(userUpdateDto.getEmail(), id) != null;
        boolean usernameEnUso = userService.findByUsernameAndIdNot(userUpdateDto.getUsername(), id) != null;

        if (emailEnUso) {
            bindingResult.rejectValue("email", "emailAlreadyExists", "El email ya está en uso");
        }
        if (usernameEnUso) {
            bindingResult.rejectValue("username", "usernameAlreadyExists", "El nombre de usuario ya existe");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("userUpdateDto", userUpdateDto);
            model.addAttribute("rolesList", todosLosRoles);
            return VIEW_EDIT_USER;
        }

        userService.save(userService.getUpdatedUser(usuarioPersistido.get(), userUpdateDto));
        redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado exitosamente");
        redirectAttributes.addFlashAttribute("userHasBeenUpdated", true);

        return REDIRECT_USERS;
    }

    /**
     * Muestra el formulario para crear un nuevo usuario
     */
    @GetMapping("/users/newUser")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("newUser", new UserDto());
        return VIEW_NEW_USER;
    }

    /**
     * Guarda un nuevo usuario en el sistema
     */
    @PostMapping("/users/newUser")
    public String guardarNuevoUsuario(
            @ModelAttribute("newUser") @Valid UserDto nuevoUsuario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        // Validaciones
        boolean emailExiste = userService.findByEmail(nuevoUsuario.getEmail()) != null;
        boolean usernameExiste = userService.findByUsername(nuevoUsuario.getUsername()) != null;

        if (emailExiste) {
            bindingResult.rejectValue("email", "emailAlreadyExists", "El email ya está registrado");
        }
        if (usernameExiste) {
            bindingResult.rejectValue("username", "usernameAlreadyExists", "El nombre de usuario ya existe");
        }

        if (bindingResult.hasErrors()) {
            return VIEW_NEW_USER;
        }

        Usuarios usuario = userService.createNewAccount(nuevoUsuario);
        usuario.setEnabled(true);
        userService.save(usuario);

        redirectAttributes.addFlashAttribute("mensaje", "Usuario creado exitosamente");
        redirectAttributes.addFlashAttribute("userHasBeenSaved", true);

        return REDIRECT_USERS;
    }
}
