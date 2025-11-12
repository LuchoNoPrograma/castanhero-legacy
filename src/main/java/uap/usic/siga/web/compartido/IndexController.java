package uap.usic.siga.web.compartido;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para la página principal del sistema
 * Gestiona las vistas de inicio y bienvenida
 *
 * @author Sistema SIGA
 * @version 2.0
 */
@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String mostrarPaginaInicio(HttpSession session) {
        session.removeAttribute("dModal");
        return "website/index";
    }
}
