package uap.usic.siga.controladores.sicoes.reportes.reportePorContratados;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/rContratados")
public class ReportePorContratados {

	@Autowired
    private SicoesServicios scServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @Autowired
    private InstitucionesServicios inServicios;
    
    @Autowired
    private UsuariosServicios uServicios;
    
    @GetMapping("/inicioRepContratado")
    public String formInicioReporteContratados(HttpSession session, Model model) {

        model.addAttribute("lPersonas", pServicios.listarPersonas());
        model.addAttribute("urlList", "listarContratosModalidad");
        model.addAttribute("operation", "form");
        return "uap/usic/siga/sicoes/reportes/reportePorContratados/reportePorContratados";
    }

    @PostMapping("/listarContratosContratado")
    public String listarContratosPorContratados(@RequestParam("idPersona") Long idPersona,
            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

    	if((gestion != 0) && (idPersona != -1))  {
    		model.addAttribute("lContratosPersonas", scServicios.listarContratacionesPorGestionIdScsContratado(idPersona, gestion));
    	} else {
			    	if(gestion == 0) {
			    		model.addAttribute("lContratosPersonas", scServicios.listarContratacionesPorIdPersonaFuncionales(idPersona));
			    	} else {
			    		model.addAttribute("lContratosPersonas", scServicios.listarContratacionesPorGestionContratados(gestion));
			    	}
    	}
        model.addAttribute("gestion", gestion);
        model.addAttribute("idPersona", idPersona);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/reportePorContratados/reportePorContratados";
    }

    @PostMapping("/imprimirReporteContratosContratados")
    public String imprimirContratosPorContratados(@RequestParam("idPersona") Long idPersona,
            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

    	if((gestion != 0) && (idPersona != -1))  {
    		model.addAttribute("lContratosPersonas", scServicios.listarContratacionesPorIdPersonaFuncionales(idPersona));
    	} else {
			    	if(gestion == 0) {
			    		model.addAttribute("lContratosPersonas", scServicios.listarContratacionesPorIdPersonaFuncionales(idPersona));
			            model.addAttribute("personas", pServicios.buscarPersonasPorIdPersona(idPersona));
			    	} else {
			    		model.addAttribute("lContratosPersonas", scServicios.listarContratacionesPorGestionContratados(gestion));
			    	}
    	}
    	model.addAttribute("personas", pServicios.buscarPersonasPorIdPersona(idPersona));
    	model.addAttribute("gestionR", gestion);
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/reportePorContratados/imprimirReporteContratosContratados";
    }


}
