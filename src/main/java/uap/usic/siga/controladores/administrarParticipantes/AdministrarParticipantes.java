package uap.usic.siga.controladores.administrarParticipantes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
import uap.usic.siga.entidades.OoParticipantes;
import uap.usic.siga.entidades.OoUnidadesEducativas;
import uap.usic.siga.servicios.OlimpiadasServicios;
import uap.usic.siga.servicios.PersonasServicios;


@Controller
@RequestMapping("/participantes")
public class AdministrarParticipantes {


	
	@Autowired
	private OlimpiadasServicios oServicios;
	
	@Autowired
	private PersonasServicios pServicios; 
	
	@GetMapping("/admParticipantes")
	public String inicioParticipantes( Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		model.addAttribute("lParticipantes", oServicios.listarParticipantes());
		return "uap/usic/siga/olimpiadas/administrarParticipantes/admParticipantes";
	}
	
	// ========= Muestra Formulario ===================
	@PostMapping(value = "/inicioForm")
	public String inicioFormParticipantes(@ModelAttribute("ooParticipantes") OoParticipantes ooParticipantes , Model model, HttpSession session) {
	      model.addAttribute("operation", "reg");
	      model.addAttribute("lPersonas", pServicios.listarPersonas());
	      model.addAttribute("lEquipos", oServicios.listarEquiposParticipantes());
	      return "uap/usic/siga/olimpiadas/administrarParticipantes/admParticipantes";
	}

	// ===================== Guardar Datos en BD =========
	@PostMapping(value = "/registroParticipantes")
	public String registrarParticipantes(@Valid @ModelAttribute("ooParticipantes")  OoParticipantes ooParticipantes, BindingResult result, HttpSession session, Model model, @RequestParam("file") MultipartFile imagen) throws IOException {
		if (result.hasErrors()) {
			  model.addAttribute("operation", "reg");
			  model.addAttribute("lPersonas", pServicios.listarPersonas());
			  model.addAttribute("lEquipos", oServicios.listarEquiposParticipantes());
		      return "uap/usic/siga/olimpiadas/administrarParticipantes/admParticipantes";
		}
		
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static/images");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			byte[] bytesImg = imagen.getBytes();
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
			Files.write(rutaCompleta, bytesImg);
			
			ooParticipantes.setImagen(imagen.getOriginalFilename());
		}
		
		oServicios.registrarParticipantes(ooParticipantes);

		model.addAttribute("busqueda", "find");
		model.addAttribute("lParticipantes", oServicios.listarParticipantes());
		return "uap/usic/siga/olimpiadas/administrarParticipantes/admParticipantes";
	}

	@GetMapping(value = "/inicioModParticipante/{id}")
	public String inicioFormModParticipante(@ModelAttribute("ooParticipantes") OoParticipantes ooParticipantes , 
			@PathVariable("id") long idParticipante, Model model, HttpSession session) {
			
		  model.addAttribute("ooParticipantes", oServicios.buscarParticipantes(idParticipante));
	      model.addAttribute("operation", "modf");
	      model.addAttribute("lPersonas", pServicios.listarPersonas());
	      model.addAttribute("lEquipos", oServicios.listarEquiposParticipantes());
	      return "uap/usic/siga/olimpiadas/administrarParticipantes/admParticipantes";
	}
	
	@PostMapping(value = "/modificarParticipante")
	public String modificarParticipantes(@Valid @ModelAttribute("ooParticipantes") OoParticipantes ooParticipantes, BindingResult result, HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			  model.addAttribute("ooParticipantes", oServicios.buscarParticipantes(ooParticipantes.getIdParticipante()));
		      model.addAttribute("operation", "modf");
		      model.addAttribute("lPersonas", pServicios.listarPersonas());
		      model.addAttribute("lEquipos", oServicios.listarEquiposParticipantes());
		      return "uap/usic/siga/olimpiadas/administrarParticipantes/admParticipantes";
		}
		
	   oServicios.modificarParticipantes(ooParticipantes); 

		model.addAttribute("busqueda", "find");
		model.addAttribute("lParticipantes", oServicios.listarParticipantes());
		return "uap/usic/siga/olimpiadas/administrarParticipantes/admParticipantes";
	}

}
