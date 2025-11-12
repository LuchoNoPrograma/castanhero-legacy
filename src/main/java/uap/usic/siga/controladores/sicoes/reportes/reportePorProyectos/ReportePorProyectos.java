package uap.usic.siga.controladores.sicoes.reportes.reportePorProyectos;

import java.io.IOException;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SicoesServicios;
import uap.usic.siga.servicios.UsuariosServicios;

@Controller
@RequestMapping("/rProyectos")
public class ReportePorProyectos {

	 @Autowired
	    private SicoesServicios scServicios;
	    
	    @Autowired
	    private PersonasServicios pServicios;
	    
	    @Autowired
	    private CajitaServicios cServicios;
	    
	    @Autowired
	    private UsuariosServicios uServicio;
	    
	    @GetMapping("/inicioRepProyectos")
	    public String formInicioReporteProyectos(HttpSession session, Model model) {

	        model.addAttribute("lScsProyectos", scServicios.listarScsProyectos());
	        model.addAttribute("urlList", "listarContratosModalidad");
	        model.addAttribute("operation", "form");
	        return "uap/usic/siga/sicoes/reportes/reportePorProyectos/reportePorProyectos";
	    }

	    @PostMapping("/listarContratosProyectos")
	    public String listarContratosPorModalidad(@RequestParam("idScsProyecto") Long idScsProyecto,
	            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

	    	if((gestion != 0) && (idScsProyecto != -1))  {
	    		model.addAttribute("lContratosProyectos", scServicios.listarContratacionesPorGestionIdScsProyectos(idScsProyecto, gestion));
	    	} else {
				    	if(gestion == 0) {
				    		model.addAttribute("lContratosProyectos", scServicios.listarContratacionesPorIdProyecto(idScsProyecto));
				    	} else {
				    		model.addAttribute("lContratosProyectos", scServicios.listarContratacionesPorGestionProyecto(gestion));
				    	}
	    	}
	        model.addAttribute("gestion", gestion);
	        model.addAttribute("idScsProyecto", idScsProyecto);
	        model.addAttribute("operation", "list");
	        return "uap/usic/siga/sicoes/reportes/reportePorProyectos/reportePorProyectos";
	    }

	    @PostMapping("/imprimirReporteContratosProyectos")
	    public String imprimirReporteContratosPorModalidad(@RequestParam("idScsProyecto") Long idScsProyecto,
	            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

	    	if((gestion != 0) && (idScsProyecto != -1))  {
	    		model.addAttribute("lContratosProyectos", scServicios.listarContratacionesPorGestionIdScsProyectos(idScsProyecto, gestion));
	    	} else {
				    	if(gestion == 0) {
				    		model.addAttribute("lContratosProyectos", scServicios.listarContratacionesPorIdProyecto(idScsProyecto));
				            model.addAttribute("proyectos", scServicios.buscarScsProyectosPorIdScsProyecto(idScsProyecto));
				    	} else {
				    		model.addAttribute("lContratosProyectos", scServicios.listarContratacionesPorGestionProyecto(gestion));
				    		
				    	}
	    	}
	    	model.addAttribute("proyectos", scServicios.buscarScsProyectosPorIdScsProyecto(idScsProyecto));
	    	model.addAttribute("gestionR", gestion);
	        model.addAttribute("operation", "list");
	        return "uap/usic/siga/sicoes/reportes/reportePorProyectos/imprimirReporteContratosProyectos";
	    }


}
