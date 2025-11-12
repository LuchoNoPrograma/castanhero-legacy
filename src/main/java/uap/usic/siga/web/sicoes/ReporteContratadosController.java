package uap.usic.siga.web.sicoes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uap.usic.siga.service.sicoes.SicoesService;
import uap.usic.siga.servicios.PersonasServicios;

import java.util.List;

@Controller
@RequestMapping("/sicoes/reportes/contratados")
@Slf4j
@RequiredArgsConstructor
public class ReporteContratadosController {

    private final SicoesService sicoesService;

    private final PersonasServicios personasServicios;

    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("personas", personasServicios.listarPersonasComprobantes());
        model.addAttribute("operation", "form");
        return "uap/usic/siga/sicoes/reportes/contratados/index";
    }

    @PostMapping("/listar")
    public String listar(@RequestParam("idPersona") Long idPersona,
                        @RequestParam("gestion") Integer gestion, Model model) {
        List contrataciones;

        if (gestion != 0 && idPersona != -1) {
            contrataciones = sicoesService.listarContratacionesPorPersonaYGestion(idPersona, gestion);
        } else if (gestion == 0) {
            contrataciones = sicoesService.listarContratacionesPorPersona(idPersona);
        } else {
            contrataciones = sicoesService.listarContratacionesPorGestion(gestion);
        }

        model.addAttribute("contrataciones", contrataciones);
        model.addAttribute("gestion", gestion);
        model.addAttribute("idPersona", idPersona);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/contratados/index";
    }

    @PostMapping("/imprimir")
    public String imprimir(@RequestParam("idPersona") Long idPersona,
                          @RequestParam("gestion") Integer gestion, Model model) {
        model.addAttribute("contrataciones", sicoesService.listarContratacionesPorPersona(idPersona));
        model.addAttribute("persona", personasServicios.buscarPersonasPorIdPersona(idPersona));
        model.addAttribute("gestion", gestion);
        return "uap/usic/siga/sicoes/reportes/contratados/imprimir";
    }
}
