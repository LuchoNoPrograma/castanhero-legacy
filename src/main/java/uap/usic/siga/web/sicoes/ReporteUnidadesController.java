package uap.usic.siga.web.sicoes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uap.usic.siga.service.sicoes.SicoesService;
import uap.usic.siga.servicios.InstitucionesServicios;

import java.util.List;

@Controller
@RequestMapping("/sicoes/reportes/unidades")
@Slf4j
@RequiredArgsConstructor
public class ReporteUnidadesController {

    private final SicoesService sicoesService;

    private final InstitucionesServicios institucionesServicios;

    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("unidades", institucionesServicios.listarUnidadesFuncionales());
        model.addAttribute("operation", "form");
        return "uap/usic/siga/sicoes/reportes/unidades/index";
    }

    @PostMapping("/listar")
    public String listar(@RequestParam("idUnidadFuncional") Long idUnidad,
                        @RequestParam("gestion") Integer gestion, Model model) {
        List contrataciones;

        if (gestion != 0 && idUnidad != -1) {
            contrataciones = sicoesService.listarContratacionesPorUnidadYGestion(idUnidad, gestion);
        } else if (gestion == 0) {
            contrataciones = sicoesService.listarContratacionesPorUnidad(idUnidad);
        } else {
            contrataciones = sicoesService.listarContratacionesPorGestion(gestion);
        }

        model.addAttribute("contrataciones", contrataciones);
        model.addAttribute("gestion", gestion);
        model.addAttribute("idUnidad", idUnidad);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/unidades/index";
    }

    @PostMapping("/imprimir")
    public String imprimir(@RequestParam("idUnidadFuncional") Long idUnidad,
                          @RequestParam("gestion") Integer gestion, Model model) {
        model.addAttribute("contrataciones", sicoesService.listarContratacionesPorUnidad(idUnidad));
        model.addAttribute("unidad", institucionesServicios.buscarInsUnidadesFuncionalesPorIdUnidadFuncional(idUnidad));
        model.addAttribute("gestion", gestion);
        return "uap/usic/siga/sicoes/reportes/unidades/imprimir";
    }
}
