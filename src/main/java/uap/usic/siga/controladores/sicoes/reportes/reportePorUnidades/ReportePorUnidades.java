package uap.usic.siga.controladores.sicoes.reportes.reportePorUnidades;

import java.io.IOException;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SicoesServicios;
import uap.usic.siga.servicios.UsuariosServicios;

@Controller
@RequestMapping("/rUnidad")
public class ReportePorUnidades {

	@Autowired
    private SicoesServicios scServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @Autowired
    private InstitucionesServicios inServicios;
    
    @Autowired
    private UsuariosServicios uServicios;
    
    @GetMapping("/inicioRepUnidad")
    public String formInicioReporteUnidad(HttpSession session, Model model) {

        model.addAttribute("lUnidades", inServicios.listarUnidadesFuncionales());
        model.addAttribute("urlList", "listarContratosModalidad");
        model.addAttribute("operation", "form");
        return "uap/usic/siga/sicoes/reportes/reportePorUnidades/reportePorUnidades";
    }

    @PostMapping("/listarContratosUnidad")
    public String listarContratosPorDirecciones(@RequestParam("idUnidadFuncional") Long idUnidadFuncional,
            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

    	if((gestion != 0) && (idUnidadFuncional != -1))  {
    		model.addAttribute("lContratosUnidades", scServicios.listarContratacionesPorGestionIdScsUnidadesFuncionales(idUnidadFuncional, gestion));
    	} else {
			    	if(gestion == 0) {
			    		model.addAttribute("lContratosUnidades", scServicios.listarContratacionesPorIdUnidadesFuncionales(idUnidadFuncional));
			    	} else {
			    		model.addAttribute("lContratosUnidades", scServicios.listarContratacionesPorGestionUnidadesFuncionales(gestion));
			    	}
    	}
        model.addAttribute("gestion", gestion);
        model.addAttribute("idUnidadFuncional", idUnidadFuncional);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/reportePorUnidades/reportePorUnidades";
    }

    @PostMapping("/imprimirReporteContratosUnidad")
    public String imprimirContratosPorDirecciones(@RequestParam("idUnidadFuncional") Long idUnidadFuncional,
            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

    	if((gestion != 0) && (idUnidadFuncional != -1))  {
    		
    		model.addAttribute("lContratosUnidades", scServicios.listarContratacionesPorIdUnidadesFuncionales(idUnidadFuncional));
    	} else {
			    	if(gestion == 0) {
			    		model.addAttribute("unidadesFuncional", inServicios.buscarInsUnidadesFuncionalesPorIdUnidadFuncional(idUnidadFuncional));
			    		model.addAttribute("lContratosUnidades", scServicios.listarContratacionesPorIdUnidadesFuncionales(idUnidadFuncional));
			            
			    	} else {
			    		
			    		model.addAttribute("lContratosUnidades", scServicios.listarContratacionesPorIdUnidadesFuncionales(idUnidadFuncional));
			    		
			    	}
    	}
    	
    	model.addAttribute("unidadesFuncional", inServicios.buscarInsUnidadesFuncionalesPorIdUnidadFuncional(idUnidadFuncional));
    	model.addAttribute("gestionR", gestion);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/reportePorUnidades/imprimirReporteContratosUnidades";
    }


}
