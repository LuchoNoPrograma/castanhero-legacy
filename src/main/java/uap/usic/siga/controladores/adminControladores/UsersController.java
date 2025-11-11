package uap.usic.siga.controladores.adminControladores;

import uap.usic.siga.dto.UserDto;
import uap.usic.siga.dto.UserUpdateDto;
import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.paginaciones.InitialPagingSizes;
import uap.usic.siga.paginaciones.Pager;
import uap.usic.siga.servicios.RoleService;
import uap.usic.siga.servicios.UserDtoService;
import uap.usic.siga.servicios.UserService;
import uap.usic.siga.servicios.UserUpdateDtoService;
import uap.usic.siga.servicios.usuariosBusquedas.UserFinder;
import uap.usic.siga.servicios.usuariosBusquedas.UserSearchErrorResponse;
import uap.usic.siga.servicios.usuariosBusquedas.UserSearchParameters;
import uap.usic.siga.servicios.usuariosBusquedas.UserSearchResult;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static uap.usic.siga.paginaciones.InitialPagingSizes.*;

/**
 * Created by Keno&Kemo on 20.11.2017..
 */
@Controller
@RequestMapping("/adminPage")
public class UsersController {
    public static final String EDIT_USER_FORM = "adminPage/user/editUser";
    public static final String REDIRECT_ADMIN_PAGE_USERS = "redirect:/adminPage/users";

    private final UserService userService;
    private final RoleService roleService;
    private final UserUpdateDtoService userUpdateDtoService;
    private final UserDtoService userDtoService;
    private final UserFinder userFinder;
    private final UserSearchErrorResponse userSearchErrorResponse;

    public UsersController(UserService userService,
                           RoleService roleService,
                           UserUpdateDtoService userUpdateDtoService,
                           UserDtoService userDtoService,
                           UserFinder userFinder,
                           UserSearchErrorResponse userSearchErrorResponse) {
        this.userService = userService;
        this.roleService = roleService;
        this.userUpdateDtoService = userUpdateDtoService;
        this.userDtoService = userDtoService;
        this.userFinder = userFinder;
        this.userSearchErrorResponse = userSearchErrorResponse;
    }

    /**
     * Get all users or search users if searching parameters exist
     
    @GetMapping("/users")
    public ModelAndView getUsers (ModelAndView modelAndView, UserSearchParameters searchParams) {
        int selectedPageSize = searchParams.getPageSize().orElse(INITIAL_PAGE_SIZE);
        int selectedPage = (searchParams.getPage().orElse(0) < 1) ? INITIAL_PAGE : (searchParams.getPage().get() - 1);

        PageRequest pageRequest = of(selectedPage, selectedPageSize, Sort.by(ASC, "id"));
        UserSearchResult userSearchResult = new UserSearchResult();

        if (searchParams.getPropertyValue().isEmpty() || searchParams.getPropertyValue().get().isEmpty())
            userSearchResult.setUserPage(userDtoService.findAllPageable(pageRequest));

        else {
            userSearchResult = userFinder.searchUsersByProperty(pageRequest, searchParams);

            if (userSearchResult.isNumberFormatException())
                return userSearchErrorResponse.respondToNumberFormatException(userSearchResult, modelAndView);

            if (userSearchResult.getUserPage().getTotalElements() == 0) {
                modelAndView = userSearchErrorResponse.respondToEmptySearchResult(modelAndView, pageRequest);
                userSearchResult.setUserPage(userDtoService.findAllPageable(pageRequest));
            }
            modelAndView.addObject("usersProperty", searchParams.getUsersProperty().get());
            modelAndView.addObject("propertyValue", searchParams.getPropertyValue().get());
        }

        Pager pager = new Pager(userSearchResult.getUserPage().getTotalPages(),
                                userSearchResult.getUserPage().getNumber(),
                                BUTTONS_TO_SHOW,
                                userSearchResult.getUserPage().getTotalElements());
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("users", userSearchResult.getUserPage());
        modelAndView.addObject("selectedPageSize", selectedPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.setViewName("adminPage/user/users");
        return modelAndView;
    }
*/
    
    @GetMapping("/users/{id}")
    public String getEditUserForm(@PathVariable Long id, Model model) {
        UserUpdateDto userUpdateDto = userUpdateDtoService.findById(id);
        List<Roles> allRoles = roleService.findAll();

        userUpdateDto.setRoles(userService.getAssignedRolesList(userUpdateDto));

        model.addAttribute("userUpdateDto", userUpdateDto);
        model.addAttribute("allRoles", allRoles);
        return EDIT_USER_FORM;
    }

    @PostMapping("/users/{id}")
    public String updateUser(Model model,
                             @PathVariable Long id,
                             @ModelAttribute("oldUser") @Valid UserUpdateDto userUpdateDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        Optional<Usuarios> persistedUser = userService.findById(id);
        List<Roles> allRoles = roleService.findAll();

        boolean emailAlreadyExists = userService.findByEmailAndIdNot(userUpdateDto.getEmail(), id) != null;
        boolean usernameAlreadyExists = userService.findByUsernameAndIdNot(userUpdateDto.getUsername(), id) != null;
        boolean validationFailed = emailAlreadyExists || usernameAlreadyExists || bindingResult.hasErrors();

        if (emailAlreadyExists) bindingResult.rejectValue("email", "emailAlreadyExists");
        if (usernameAlreadyExists) bindingResult.rejectValue("username", "usernameAlreadyExists");
        if (validationFailed) {
            model.addAttribute("userUpdateDto", userUpdateDto);
            model.addAttribute("rolesList", allRoles);
            model.addAttribute("org.springframework.validation.BindingResult.userUpdateDto", bindingResult);
            return EDIT_USER_FORM;
        }
       userService.save(userService.getUpdatedUser(persistedUser.get(), userUpdateDto));
        redirectAttributes.addFlashAttribute("userHasBeenUpdated", true);
        return REDIRECT_ADMIN_PAGE_USERS;
    }

    @GetMapping("/users/newUser")
    public String getAddNewUserForm(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "adminPage/user/newUser";
    }

    @PostMapping("/users/newUser")
    public String saveNewUser(@ModelAttribute("newUser") @Valid UserDto newUser,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        boolean emailAlreadyExists = userService.findByEmail(newUser.getEmail()) != null;
        boolean usernameAlreadyExists = userService.findByUsername(newUser.getUsername()) != null;
        boolean validationFailed = emailAlreadyExists || usernameAlreadyExists || bindingResult.hasErrors();
        String formWithErrors = "adminPage/user/newUser";

        if (emailAlreadyExists) bindingResult.rejectValue("email", "emailAlreadyExists");
        if (usernameAlreadyExists) bindingResult.rejectValue("username", "usernameAlreadyExists");
        if (validationFailed) return formWithErrors;

        Usuarios user = userService.createNewAccount(newUser);
        user.setEnabled(true);

       // userService.save(user);
        redirectAttributes.addFlashAttribute("userHasBeenSaved", true);
        return REDIRECT_ADMIN_PAGE_USERS;
    }
}
