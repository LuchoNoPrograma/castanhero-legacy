package uap.usic.siga.controladores.pg.administrarProgramas;

import java.io.IOException;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/reporteProgramas")
public class ReportesProgramas {
	
    @Autowired
	private ProgramasService servicio;

    @PostMapping("/certificado")
	    public String inicioEliminarPrograma(@ModelAttribute("programas") Programas programas,
	    		@RequestParam("idPrograma") Long idPrograma,
                @RequestParam("idPgPrgPlanes") Long idPgPrgPlanes, Model model, HttpSession session) throws IOException {

		 	model.addAttribute("programas",servicio.buscarProgramaPorIdPrograma(idPrograma));
            model.addAttribute("listaPla", servicio.ListaPorProgramas(idPrograma));
            model.addAttribute("listaP", servicio.ListaPorPlanes(idPgPrgPlanes));

	        return "uap/usic/siga/pg/administrarProgramas/imprimirCertificadoPrograma";
	    }
    
}
