package uap.usic.siga.controladores.pg.enlaceDocentes;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uap.usic.siga.servicios.DocentesServicios;
import uap.usic.siga.servicios.ModuloServicio;

@Controller
@RequestMapping("/aDocentes")
public class AdministrarNotas {
    @Autowired private ModuloServicio modServicio;
    @Autowired private DocentesServicios docServicios;

    private String URL = "";
    private String REDIRECT = "";

    @GetMapping("/ingresarNotas")
    private String ingresarNotas(Model model, HttpSession session){
        model.addAttribute("docente", docServicios.buscarDocentePorId(1L));
        model.addAttribute("lEstudiantes", modServicio);
        return URL;
    }
}
