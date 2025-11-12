package uap.usic.siga.controladores.gdoc.reportes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uap.usic.siga.entidades.GdocConsejos;
import uap.usic.siga.entidades.GdocConvenios;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.ScsArchivosAdjuntos;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PdfService;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;

@Controller
@RequestMapping("/repConvenios")
public class ReportesConvenios {

	@Autowired
	private GdocServicios gServicios;;
	
	@Autowired
	private PersonasServicios pServicios;;
	
	@Autowired
	private UsuariosServicios uServicio;
	
	@Autowired
	private InstitucionesServicios iServicios;
	
	@Autowired
    private PdfService pdfService;

	
	  @GetMapping("/inicioReportConvenios")
	   public String inicioReporteConvenios(Model model, HttpSession session){
		    model.addAttribute("busqueda", "find");
		    model.addAttribute("lTiposConvenios", gServicios.listarGdocTiposConvenios());
		    getDesplegarListasComunes(model, session);
	        return "reportConvenios";   
	    }
	  
   @PostMapping("/imprimirReporteConvenios")
	   public void imprimirReporteConvneios(HttpServletResponse response, @RequestParam("idGdocTipoConvenio") Long idGdocTipoConvenio, HttpServletRequest request, Model model, HttpSession session) throws IOException {

		   String gestionT = "gestion" + idGdocTipoConvenio;
	       gestionT = request.getParameter(gestionT);
	       String  templ = "";
	       String  name = "C:/gDoc/hola.pdf";
	       pdfService.exportPdfFile(templ, gestionT, name);
	  
	       /*
	    	ByteArrayInputStream byteArrayInputStream = pdfService.convertHtmlToPdf(contentToGenerate);
	        response.setContentType("application/octet-stream");
	    	//response.setContentType("application/pdf");
	        response.setPath("C:/gDoc");
	        
	        //response.setHeader("Content-Disposition", "attachment; filename=files.pdf");
	    	response.setHeader("Content-Disposition", "attachment; filename=C:/gDoc/files.pdf");
	        IOUtils.copy(byteArrayInputStream, response.getOutputStream());

		   String gestionT = "gestion" + idGdocTipoConvenio;
	       gestionT = request.getParameter(gestionT);
	       Integer gestion = Integer.parseInt(gestionT);
		   model.addAttribute("id", idGdocTipoConvenio);
	        model.addAttribute("gestion", gestion);
	        model.addAttribute("lConvneios", gServicios.listarGdocConveniosPorIdGdocTipoCovenioGestion(idGdocTipoConvenio, gestion));
	        
	        
	        model.addAttribute("busqueda", "find");
	          getDesplegarListasComunes(model, session);
	        return "imprimirReportesConvenios2";
	        */
	    }
	   
	   @PostMapping(value = "/imprimirConvenio")
	    public String imprimirResolucion(@RequestParam("idGdocTipoConvenio") Long idGdocTipoConvenio,
	    		@RequestParam("gestion") Integer gestion, Model model, HttpSession session)  {
		   model.addAttribute("bConvneios", gServicios.buscarGdocConveniosPorIdGdocTipoConvenioGestion(idGdocTipoConvenio, gestion));
	       return "imprimirReporteConvenios";
	    }
 
	   

	  public void getDesplegarListasComunes(Model model, HttpSession session) {
	        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
	        GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion((Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
	        String idEstado = "A";
	        model.addAttribute("lAutoridades", gServicios.listarAutoridades(gdocConsejos.getIdGdocConsejo()));
	        model.addAttribute("lConvenios", gServicios.listarGdocConveniosPorIdGdocConsejo(gdocConsejos.getIdGdocConsejo()));
	        model.addAttribute("bGdocConsejos", gdocConsejos);
	        model.addAttribute("lRepresentante", gServicios.listarGdocRepresentante());
	        model.addAttribute("lTiposConvenios", gServicios.listarGdocTiposConvenios());
	        model.addAttribute("lInstituciones", iServicios.listarInstituciones());
	        model.addAttribute("urlList", "imprimirReporteConvenios");
	        model.addAttribute("urlMod", "inicioModificarConvenios");
	        model.addAttribute("urlConfirmarMod", "confirmarModificacionIngresos");
	        model.addAttribute("urlEliminar", "eliminarIngresosFondos");
	        model.addAttribute("urlRIn", "inicioFormConvenios");
	        model.addAttribute("urlEI", "inicioEliminarConvenios");
	        model.addAttribute("urlCEI", "confirmarEliminarIngresos");
	        model.addAttribute("urlClose", "inicioCerrarCaja");
	        model.addAttribute("impRepCnv", "imprimirConvenio");
	     }

}
