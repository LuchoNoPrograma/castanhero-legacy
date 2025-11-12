package uap.usic.siga.web.sicoes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uap.usic.siga.service.sicoes.SicoesService;

import java.util.List;

@Controller
@RequestMapping("/sicoes/reportes/proyectos")
@Slf4j
@RequiredArgsConstructor
public class ReporteProyectosController {

    private final SicoesService sicoesService;

    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("proyectos", sicoesService.listarProyectos());
        model.addAttribute("operation", "form");
        return "uap/usic/siga/sicoes/reportes/proyectos/index";
    }

    @PostMapping("/listar")
    public String listar(@RequestParam("idProyecto") Long idProyecto,
                        @RequestParam("gestion") Integer gestion, Model model) {
        List contrataciones;

        if (gestion != 0 && idProyecto != -1) {
            contrataciones = sicoesService.listarContratacionesPorProyectoYGestion(idProyecto, gestion);
        } else if (gestion == 0) {
            contrataciones = sicoesService.listarContratacionesPorProyecto(idProyecto);
        } else {
            contrataciones = sicoesService.listarContratacionesPorGestion(gestion);
        }

        model.addAttribute("contrataciones", contrataciones);
        model.addAttribute("gestion", gestion);
        model.addAttribute("idProyecto", idProyecto);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/proyectos/index";
    }

    @PostMapping("/imprimir")
    public String imprimir(@RequestParam("idProyecto") Long idProyecto,
                          @RequestParam("gestion") Integer gestion, Model model) {
        model.addAttribute("contrataciones", sicoesService.listarContratacionesPorProyecto(idProyecto));
        model.addAttribute("proyecto", sicoesService.buscarProyectoPorId(idProyecto));
        model.addAttribute("gestion", gestion);
        return "uap/usic/siga/sicoes/reportes/proyectos/imprimir";
    }
}
