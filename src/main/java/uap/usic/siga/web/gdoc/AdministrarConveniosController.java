package uap.usic.siga.web.gdoc;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.domain.gdoc.GdocConvenios;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.service.gdoc.GdocArchivosAdjuntosService;
import uap.usic.siga.service.gdoc.GdocConsejosService;
import uap.usic.siga.service.gdoc.GdocConveniosService;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/convenios")
public class AdministrarConveniosController {

    private final GdocConveniosService conveniosService;
    private final GdocArchivosAdjuntosService archivosService;
    private final GdocConsejosService consejosService;
    private final UsuariosServicios usuariosServicios;
    private final InstitucionesServicios institucionesServicios;

    public AdministrarConveniosController(GdocConveniosService conveniosService,
                                          GdocArchivosAdjuntosService archivosService,
                                          GdocConsejosService consejosService,
                                          UsuariosServicios usuariosServicios,
                                          InstitucionesServicios institucionesServicios) {
        this.conveniosService = conveniosService;
        this.archivosService = archivosService;
        this.consejosService = consejosService;
        this.usuariosServicios = usuariosServicios;
        this.institucionesServicios = institucionesServicios;
    }

    @GetMapping("/inicioConvenios")
    public String inicioConvenios(Model model, HttpSession session) {
        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarConvenios/administrarConvenios";
    }

    @PostMapping("/inicioFormConvenios")
    public String inicioFormConvenios(@ModelAttribute("gdocConvenios") GdocConvenios gdocConvenios,
                                      Model model, HttpSession session) {
        desplegarListasComunes(model, session);
        model.addAttribute("operation", "reg");
        return "uap/usic/siga/gdoc/administrarConvenios/administrarConvenios";
    }

    @PostMapping("/registroConvenios")
    public String registrarConvenio(@Valid @ModelAttribute("gdocConvenios") GdocConvenios gdocConvenios,
                                    BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            desplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/gdoc/administrarConvenios/administrarConvenios";
        }

        MultipartFile multipartFile = gdocConvenios.getFile();
        Usuarios usuario = usuariosServicios.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Gdoc/convenios");
        adjuntarArchivo.adjuntarArchivoConvenios(gdocConvenios, rutaArchivo);

        GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
        gdocArchivoAdjunto.setNombreArchivo(gdocConvenios.getNombreArchivo());
        gdocArchivoAdjunto.setUsuarios(usuario);
        gdocArchivoAdjunto.setRutaArchivo("gDoc/convenios/");
        gdocArchivoAdjunto.setTipoArchivo(multipartFile.getContentType());

        GdocArchivosAdjuntos archivoGuardado = archivosService.guardar(gdocArchivoAdjunto);
        gdocConvenios.setGdocArchivosAdjuntos(archivoGuardado);
        conveniosService.guardar(gdocConvenios);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarConvenios/administrarConvenios";
    }

    @GetMapping(value = "/openFile/{id}", produces = "application/pdf")
    @ResponseBody
    public Resource abrirArchivo(HttpServletResponse response, @PathVariable("id") Long idGdocConvenio) {
        GdocArchivosAdjuntos archivo = conveniosService.buscarArchivoPorConvenio(idGdocConvenio);
        File file = new File("C:/" + archivo.getRutaArchivo() + archivo.getNombreArchivo());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));

        return new FileSystemResource(file);
    }

    @PostMapping("/inicioModificarConvenios")
    public String inicioModificarConvenios(@RequestParam("idGdocConvenio") Long idGdocConvenio,
                                           Model model, HttpSession session) {
        model.addAttribute("gdocConvenios", conveniosService.buscarPorId(idGdocConvenio));
        model.addAttribute("urlUp", "actualizarConvenio");
        model.addAttribute("operation", "mod");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarConvenios/administrarConvenios";
    }

    @PostMapping("/modificarConvenios")
    public String modificarConvenio(@Valid @ModelAttribute("gdocConvenios") GdocConvenios gdocConvenios,
                                    BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            desplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");
            return "uap/usic/siga/gdoc/administrarConvenios/administrarConvenios";
        }

        MultipartFile multipartFile = gdocConvenios.getFile();
        Usuarios usuario = usuariosServicios.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Gdoc/convenios");
        Integer resultado = adjuntarArchivo.adjuntarArchivoConvenios(gdocConvenios, rutaArchivo);

        if (resultado == 1) {
            GdocArchivosAdjuntos archivo = conveniosService.buscarArchivoPorConvenio(gdocConvenios.getIdGdocConvenio());
            archivo.setNombreArchivo(gdocConvenios.getNombreArchivo());
            archivo.setUsuarios(usuario);
            archivosService.actualizar(archivo);
        }

        conveniosService.actualizar(gdocConvenios);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarConvenios/administrarConvenios";
    }

    private void desplegarListasComunes(Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("currentUserId");
        Long idMnuTipoFuncion = 13L;

        model.addAttribute("gdocConsejos", consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion));
        model.addAttribute("listaConvenios", conveniosService.listarPorConsejo(
                consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion).getIdGdocConsejo()));
        model.addAttribute("listaRepresentantes", conveniosService.listarRepresentantes());
        model.addAttribute("listaTiposConvenios", conveniosService.listarTiposConvenios());
        model.addAttribute("listaInstituciones", institucionesServicios.listarInstituciones());
    }
}
