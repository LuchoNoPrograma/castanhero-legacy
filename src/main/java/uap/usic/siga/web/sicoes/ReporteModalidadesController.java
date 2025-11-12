package uap.usic.siga.web.sicoes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uap.usic.siga.service.sicoes.SicoesService;

import java.util.List;

@Controller
@RequestMapping("/sicoes/reportes/modalidades")
@Slf4j
@RequiredArgsConstructor
public class ReporteModalidadesController {

    private final SicoesService sicoesService;

    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("modalidades", sicoesService.listarModalidades());
        model.addAttribute("operation", "form");
        return "uap/usic/siga/sicoes/reportes/modalidades/index";
    }

    @PostMapping("/listar")
    public String listar(@RequestParam("idModalidad") Long idModalidad,
                        @RequestParam("gestion") Integer gestion, Model model) {
        List contrataciones;

        if (gestion != 0 && idModalidad != -1) {
            contrataciones = sicoesService.listarContratacionesPorModalidadYGestion(idModalidad, gestion);
        } else if (gestion == 0) {
            contrataciones = sicoesService.listarContratacionesPorModalidad(idModalidad);
        } else {
            contrataciones = sicoesService.listarContratacionesPorGestion(gestion);
        }

        model.addAttribute("contrataciones", contrataciones);
        model.addAttribute("gestion", gestion);
        model.addAttribute("idModalidad", idModalidad);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/modalidades/index";
    }

    @PostMapping("/imprimir")
    public String imprimir(@RequestParam("idModalidad") Long idModalidad,
                          @RequestParam("gestion") Integer gestion, Model model) {
        model.addAttribute("contrataciones", sicoesService.listarContratacionesPorModalidad(idModalidad));
        model.addAttribute("modalidad", sicoesService.buscarModalidadPorId(idModalidad));
        model.addAttribute("gestion", gestion);
        return "uap/usic/siga/sicoes/reportes/modalidades/imprimir";
    }
}
