package uap.usic.siga.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador principal del panel de administración
 * Redirige a la página de gestión de usuarios por defecto
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("/adminPage")
public class MainController {

    /**
     * Redirige a la página principal de administración de usuarios
     */
    @GetMapping("")
    public String mostrarPaginaAdmin() {
        return "redirect:/adminPage/users";
    }
}
