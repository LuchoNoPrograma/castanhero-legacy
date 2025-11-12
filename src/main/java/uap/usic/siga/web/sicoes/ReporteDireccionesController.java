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
@RequestMapping("/sicoes/reportes/direcciones")
@Slf4j
@RequiredArgsConstructor
public class ReporteDireccionesController {

    private final SicoesService sicoesService;

    private final InstitucionesServicios institucionesServicios;

    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("direcciones", institucionesServicios.listarDireccionesFuncionales());
        model.addAttribute("operation", "form");
        return "uap/usic/siga/sicoes/reportes/direcciones/index";
    }

    @PostMapping("/listar")
    public String listar(@RequestParam("idDireccionFuncional") Long idDireccion,
                        @RequestParam("gestion") Integer gestion, Model model) {
        List contrataciones;

        if (gestion != 0 && idDireccion != -1) {
            contrataciones = sicoesService.listarContratacionesPorDireccionYGestion(idDireccion, gestion);
        } else if (gestion == 0) {
            contrataciones = sicoesService.listarContratacionesPorDireccion(idDireccion);
        } else {
            contrataciones = sicoesService.listarContratacionesPorGestion(gestion);
        }

        model.addAttribute("contrataciones", contrataciones);
        model.addAttribute("gestion", gestion);
        model.addAttribute("idDireccion", idDireccion);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/direcciones/index";
    }

    @PostMapping("/imprimir")
    public String imprimir(@RequestParam("idDireccionFuncional") Long idDireccion,
                          @RequestParam("gestion") Integer gestion, Model model) {
        model.addAttribute("contrataciones", sicoesService.listarContratacionesPorDireccion(idDireccion));
        model.addAttribute("direccion", institucionesServicios.buscarDireccionFuncionalPorIdDireccionaFuncional(idDireccion));
        model.addAttribute("gestion", gestion);
        return "uap/usic/siga/sicoes/reportes/direcciones/imprimir";
    }
}
