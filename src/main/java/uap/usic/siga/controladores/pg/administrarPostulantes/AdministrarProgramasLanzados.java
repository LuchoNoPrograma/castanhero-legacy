package uap.usic.siga.controladores.pg.administrarPostulantes;

import java.io.IOException;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uap.usic.siga.servicios.PostulantesServicios;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/aProgramas")
public class AdministrarProgramasLanzados {
    @Autowired
    private ProgramasService prgServicios;

    @Autowired
    private PostulantesServicios pstServicios;

    private String URL = "uap/usic/siga/pg/administrarPostulantes/admProgramasLanzados";
    
    //el boton Ver admitidos se encuentra en AdministrarAdmitidos
    //el boton Ver postulantes se encuentra en AdministrarPostulantes
    @GetMapping("/listarPostulantes")
    public String listarPostulantesPorPrograma(HttpSession session, Model model) throws IOException{
        model.addAttribute("lProgramas", prgServicios.listaProgramasLanzados());
        model.addAttribute("tabla", "postulantes");
        return URL;
    }
    @GetMapping("/listarAdmitidos")
    public String listarAdmitidosPorPrograma(HttpSession session, Model model) throws IOException{
        model.addAttribute("lProgramas", prgServicios.listaProgramasLanzados());
        model.addAttribute("tabla", "admitidos");
        return URL;
    }    
}
