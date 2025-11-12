package uap.usic.siga.controladores.sicoes.reportes.reportePorDirecciones;

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
@RequestMapping("/rDireccion")
public class ReportePorDirecciones {

	@Autowired
    private SicoesServicios scServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @Autowired
    private InstitucionesServicios inServicios;
    
    @Autowired
    private UsuariosServicios uServicios;
    
    @GetMapping("/inicioRepDireccion")
    public String formInicioReporteDirecciones(HttpSession session, Model model) {

        model.addAttribute("lDirecciones", inServicios.listarDireccionesFuncionales());
        model.addAttribute("urlList", "listarContratosModalidad");
        model.addAttribute("operation", "form");
        return "uap/usic/siga/sicoes/reportes/reportePorDirecciones/reportePorDirecciones";
    }

    @PostMapping("/listarContratosDireccion")
    public String listarContratosPorDirecciones(@RequestParam("idDireccionFuncional") Long idDireccionFuncional,
            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

    	if((gestion != 0) && (idDireccionFuncional != -1))  {
    		model.addAttribute("lContratosDirecciones", scServicios.listarContratacionesPorGestionIdDireccionesFuncionales(idDireccionFuncional, gestion));
    	} else {
			    	if(gestion == 0) {
			    		model.addAttribute("lContratosDirecciones", scServicios.listarContratacionesPorIdDireccionesFuncionales(idDireccionFuncional));
			    	} else {
			    		model.addAttribute("lContratosDirecciones", scServicios.listarContratacionesPorGestion(gestion));
			    	}
    	}
        model.addAttribute("gestion", gestion);
        model.addAttribute("idDireccionFuncional", idDireccionFuncional);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/reportePorDirecciones/reportePorDirecciones";
    }

    @PostMapping("/imprimirReporteContratosDireccion")
    public String imprimirContratosPorDirecciones(@RequestParam("idDireccionFuncional") Long idDireccionFuncional,
            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

    	if((gestion != 0) && (idDireccionFuncional != -1))  {
    		model.addAttribute("lContratosDirecciones", scServicios.listarContratacionesPorIdDireccionesFuncionales(idDireccionFuncional));
    	} else {
			    	if(gestion == 0) {
			    		model.addAttribute("lContratosDirecciones", scServicios.listarContratacionesPorIdDireccionesFuncionales(idDireccionFuncional));
			            model.addAttribute("direccionFuncioanl", inServicios.buscarDireccionFuncionalPorIdDireccionaFuncional(idDireccionFuncional));
			    	} else {
			    		model.addAttribute("lContratosDirecciones", scServicios.listarContratacionesPorGestion(gestion));
			    	}
    	}
    	model.addAttribute("direccionFuncioanl", inServicios.buscarDireccionFuncionalPorIdDireccionaFuncional(idDireccionFuncional));
    	model.addAttribute("gestionR", gestion);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/reportePorDirecciones/imprimirReporteContratosDirecciones";
    }


}
