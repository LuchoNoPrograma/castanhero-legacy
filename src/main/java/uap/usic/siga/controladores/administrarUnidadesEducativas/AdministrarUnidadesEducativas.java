package uap.usic.siga.controladores.administrarUnidadesEducativas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import uap.usic.siga.entidades.OoEquiposParticipantes;
import uap.usic.siga.entidades.OoUnidadesEducativas;
import uap.usic.siga.servicios.OlimpiadasServicios;


@Controller
@RequestMapping("/unidadesedu")
public class AdministrarUnidadesEducativas {


	
	@Autowired
	private OlimpiadasServicios oServicios; 
	
	@GetMapping("/admUnidadesEducativas")
	public String inicioUnidadesEducativas( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		model.addAttribute("lUni", oServicios.listarUnidadesEducativas());
		return "uap/usic/siga/olimpiadas/administrarUnidadesEducativas/admUnidadesEducativas";
	}
	
	// ========= Muestra Formulario ===================
	@PostMapping(value = "/inicioForm")
	public String inicioFormUnidadesEducativas(@ModelAttribute("ooUnidadesEducativas") OoUnidadesEducativas ooUnidadesEducativas , Model model, HttpSession session) {
	      model.addAttribute("operation", "reg");
	      model.addAttribute("lComp", oServicios.listarCompeticiones());
	    
	      return "uap/usic/siga/olimpiadas/administrarUnidadesEducativas/admUnidadesEducativas";
	}

	// ===================== Guardar Datos en BD =========
	@PostMapping(value = "/registroUnidadEducativa")
	public String registrarUnidadesEducativas(@Valid @ModelAttribute("ooUnidadesEducativas")  OoUnidadesEducativas ooUnidadesEducativas, BindingResult result, HttpSession session, Model model, @RequestParam("file") MultipartFile imagen) throws IOException {
		if (result.hasErrors()) {
			  model.addAttribute("operation", "reg");
			  model.addAttribute("lComp", oServicios.listarCompeticiones());
			 
		      return "uap/usic/siga/olimpiadas/administrarUnidadesEducativas/admUnidadesEducativas";
		}
		
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static/images");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			byte[] bytesImg = imagen.getBytes();
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
			Files.write(rutaCompleta, bytesImg);
			
			ooUnidadesEducativas.setImagen(imagen.getOriginalFilename());
		}
		
		oServicios.registrarUnidadesEducativas(ooUnidadesEducativas);

		model.addAttribute("busqueda", "find");
		model.addAttribute("lUni", oServicios.listarUnidadesEducativas());
		return "uap/usic/siga/olimpiadas/administrarUnidadesEducativas/admUnidadesEducativas";
	}

	@GetMapping(value = "/inicioModUnidad/{id}")
	public String inicioFormModUnidadesEducativas(@ModelAttribute("ooUnidadesEducativas") OoUnidadesEducativas ooUnidadesEducativas , 
			@PathVariable("id") long idUnidadEducativa, Model model, HttpSession session) {
			
		  model.addAttribute("ooUnidadesEducativas", oServicios.buscarUnidadesEducativas(idUnidadEducativa));
	      model.addAttribute("operation", "modf");
	      model.addAttribute("lComp", oServicios.listarCompeticiones());
	      
	      return "uap/usic/siga/olimpiadas/administrarUnidadesEducativas/admUnidadesEducativas";
	}
	
	@PostMapping(value = "/modificarUnidadEducativa")
	public String modificarUnidadesEducativas(@Valid @ModelAttribute("ooUnidadesEducativas") OoUnidadesEducativas ooUnidadesEducativas, BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			  model.addAttribute("ooUnidadesEducativas", oServicios.buscarUnidadesEducativas(ooUnidadesEducativas.getIdUnidadEducativa()));
		      model.addAttribute("operation", "modf");
		      model.addAttribute("lComp", oServicios.listarCompeticiones());
		      
		      return "uap/usic/siga/olimpiadas/administrarUnidadesEducativas/admUnidadesEducativas";
		}
		
	   oServicios.modificarUnidadesEducativas(ooUnidadesEducativas); 

		model.addAttribute("busqueda", "find");
		model.addAttribute("lUni", oServicios.listarUnidadesEducativas());
		return "uap/usic/siga/olimpiadas/administrarEquiposParticipantes/admEquiposParticipantes";
	}

}
