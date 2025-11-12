package uap.usic.siga.controladores.sicoes.administrarContrataciones;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.ScsArchivosAdjuntos;
import uap.usic.siga.entidades.ScsContrataciones;
import uap.usic.siga.entidades.ScsPrsContratados;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SicoesServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

/**
 * Rectorado - USIC
 * Fecha: 2020-02-03
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/aContrataciones")
public class AdministrarContrataciones {
    
     @Autowired
    private SicoesServicios scServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @Autowired
    private CajitaServicios cServicios;
    
    @Autowired
    private UsuariosServicios uServicio;
    
    @GetMapping("/inicioContrataciones")
    public String formInicioContrataciones(HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");    	
        return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";
    }

    @PostMapping(value = "/foraNuevaContratacion")
    public String forrNuevaContratacion(@ModelAttribute("scsContrataciones") ScsContrataciones scsContrataciones, HttpSession session, Model model) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";     
    }
  
    
    @PostMapping(value = "/registrarContrataciones")
    public String registrarContrataciones(@Valid ScsContrataciones scsContrataciones, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";
        }
  
                
        MultipartFile multipartFile = scsContrataciones.getFile();
        ScsArchivosAdjuntos scsArchivosAdjuntos = new ScsArchivosAdjuntos();
        Personas bPersona = pServicios.buscarPersonasPorIdPersona((Long) session.getAttribute("idPersona"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
    
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/fotos");
        model.addAttribute("di", rutaArchivo);
        Integer ad = adjuntarArchivo.adjuntarArchivoSicoes(scsContrataciones, rutaArchivo);

        scsArchivosAdjuntos.setNombreArchivo(scsContrataciones.getNombreArchivo());
        scsArchivosAdjuntos.setTipoArchivo(multipartFile.getContentType());
        scsArchivosAdjuntos.setPersonas(bPersona);
        scsArchivosAdjuntos.setRutaArchivo("Sicoes/sico/");
        ScsArchivosAdjuntos scsArchivosAdjuntos1 = scServicios.registrarScsArchivosAdjuntos(scsArchivosAdjuntos);

        scsContrataciones.setScsArchivosAdjuntos(scsArchivosAdjuntos1);
       scsContrataciones.setPersonas(bPersona);
        scServicios.registrarScsContrataciones(scsContrataciones);
        
        //getDesplegarListasComunes(model, session);
        model.addAttribute("lScsContrataciones", scServicios.listarScsContrataciones());
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";
    }

    @PostMapping(value ="/inicioModificarContrataciones")
    public String inicioModificarContrataciones(@ModelAttribute("scsContrataciones") ScsContrataciones scsContrataciones,
           @RequestParam("idScsContratacion") Long idScsContratacion, Model model, HttpSession session) throws IOException {

        model.addAttribute("scsContrataciones", scServicios.buscarScsContratacionesPorIdScsContratacion(idScsContratacion));
    	model.addAttribute("saludo", "Hola a Todos");
    	model.addAttribute("operation", "saludo");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";
    }
    
    
    @PostMapping(value = "/actualizarContratacionesSicoes")
    public String modificarContratacionesSicoes(@Valid ScsContrataciones scsContrataciones, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");
            return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";

        }
     
                  
        MultipartFile multipartFile = scsContrataciones.getFile();
        ScsArchivosAdjuntos scsArchivosAdjuntos = new ScsArchivosAdjuntos();
        Personas bPersona = pServicios.buscarPersonasPorIdPersona((Long) session.getAttribute("idPersona"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/fotos");
        model.addAttribute("di", rutaArchivo);
        Integer ad = adjuntarArchivo.adjuntarArchivoSicoes(scsContrataciones, rutaArchivo);
        
        if(ad == 1){
            ScsArchivosAdjuntos bScsArchivosAdjuntos = scServicios.buscarScsArchivosAdjuntosPorIdScsArchivoAdjunto(scsContrataciones.getScsArchivosAdjuntos().getIdScsArchivoAdjunto());
            bScsArchivosAdjuntos.setNombreArchivo(scsContrataciones.getNombreArchivo());
            bScsArchivosAdjuntos.setPersonas(bPersona);
            scsContrataciones.setPersonas(bPersona);
            scServicios.modificarScsArchivosAdjuntos(bScsArchivosAdjuntos);
        }
      
        scServicios.modificarScsContrataciones(scsContrataciones);
        scsContrataciones.setPersonas(bPersona);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";

    }

    @PostMapping(value ="/inicioEliminarContratacionesSicoes")
    public String inicioEliminarContrataciones(@ModelAttribute("scsContrataciones") ScsContrataciones scsContrataciones,
           @RequestParam("idScsContratacion") Long idScsContratacion, Model model, HttpSession session) throws IOException {

    	model.addAttribute("scsContrataciones", scServicios.buscarScsContratacionesPorIdScsContratacion(idScsContratacion));
        model.addAttribute("urlDel", "eliminarContratadosSicoes");
        model.addAttribute("operation", "delet");
        getDesplegarListasComunes(model, session);
        return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";
    }

    
    @PostMapping(value = "/eliminarContratacionesSicoes")
    public String eliminarContratacionesSicoes(@Valid ScsContrataciones scsContrataciones, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");
            return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";
        }

        scsContrataciones.setIdEstado("X");
        scServicios.modificarScsContrataciones(scsContrataciones);

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "uap/usic/siga/sicoes/administrarContrataciones/administrarContrataciones";
    }

    // ====== Listas Comunes de Módulo
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        //Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("lScsProyectos", scServicios.listarScsProyectos());
        model.addAttribute("lScsTiposContratos", scServicios.listarScsTiposContratos());
        model.addAttribute("lScsModalidades", scServicios.listarScsModalidades());
        model.addAttribute("lScsTiposModalidades", scServicios.listarScsTiposModalidades());
        model.addAttribute("lPersonas", pServicios.listarPersonasComprobantes());
        model.addAttribute("lCjaTiposGastos", cServicios.listarTiposGastos());
        model.addAttribute("lScsFormularios", scServicios.listarScsFormularios());
        model.addAttribute("lScsBoletasRespaldatorioas", scServicios.listarScsBoletasRespaldatorias());
        model.addAttribute("lScsPrsContratados", scServicios.listarScsPrsContratadoses());  
        model.addAttribute("lScsContrataciones", scServicios.listarScsContrataciones());
      
    }

}
