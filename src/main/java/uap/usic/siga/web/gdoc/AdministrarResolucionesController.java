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
import uap.usic.siga.domain.gdoc.GdocResoluciones;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.service.gdoc.GdocArchivosAdjuntosService;
import uap.usic.siga.service.gdoc.GdocConsejosService;
import uap.usic.siga.service.gdoc.GdocResolucionesService;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/resolucion")
public class AdministrarResolucionesController {

    private final GdocResolucionesService resolucionesService;
    private final GdocArchivosAdjuntosService archivosService;
    private final GdocConsejosService consejosService;
    private final UsuariosServicios usuariosServicios;

    public AdministrarResolucionesController(GdocResolucionesService resolucionesService,
                                              GdocArchivosAdjuntosService archivosService,
                                              GdocConsejosService consejosService,
                                              UsuariosServicios usuariosServicios) {
        this.resolucionesService = resolucionesService;
        this.archivosService = archivosService;
        this.consejosService = consejosService;
        this.usuariosServicios = usuariosServicios;
    }

    @GetMapping("/inicioResol")
    public String inicioResolucion(Model model, HttpSession session) {
        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarResoluciones/administrarResoluciones";
    }

    @PostMapping("/inicioFormResolucion")
    public String inicioFormResoluciones(@ModelAttribute("gdocResoluciones") GdocResoluciones gdocResoluciones,
                                         Model model, HttpSession session) {
        desplegarListasComunes(model, session);
        model.addAttribute("operation", "reg");
        return "uap/usic/siga/gdoc/administrarResoluciones/administrarResoluciones";
    }

    @PostMapping("/registroResolucion")
    public String registrarResoluciones(@Valid @ModelAttribute("gdocResoluciones") GdocResoluciones gdocResoluciones,
                                        BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            desplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/gdoc/administrarResoluciones/administrarResoluciones";
        }

        MultipartFile multipartFile = gdocResoluciones.getFile();
        Usuarios usuario = usuariosServicios.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("gDoc/resolucion");
        adjuntarArchivo.adjuntarArchivoResolucion(gdocResoluciones, rutaArchivo);

        GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
        gdocArchivoAdjunto.setNombreArchivo(gdocResoluciones.getNombreArchivo());
        gdocArchivoAdjunto.setUsuarios(usuario);
        gdocArchivoAdjunto.setRutaArchivo("gDoc/resolucion/");
        gdocArchivoAdjunto.setTipoArchivo(multipartFile.getContentType());

        GdocArchivosAdjuntos archivoGuardado = archivosService.guardar(gdocArchivoAdjunto);
        gdocResoluciones.setGdocArchivosAdjuntos(archivoGuardado);
        resolucionesService.guardar(gdocResoluciones);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarResoluciones/administrarResoluciones";
    }

    @GetMapping(value = "/openFile/{id}", produces = "application/pdf")
    @ResponseBody
    public Resource abrirArchivo(HttpServletResponse response, @PathVariable("id") Long idGdocResolucion) {
        GdocArchivosAdjuntos archivo = resolucionesService.buscarArchivoPorResolucion(idGdocResolucion);
        File file = new File("C:/" + archivo.getRutaArchivo() + archivo.getNombreArchivo());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));

        return new FileSystemResource(file);
    }

    @PostMapping("/inicioModificarResolucion")
    public String inicioModificarResolucion(@RequestParam("idGdocResolucion") Long idGdocResolucion,
                                             Model model, HttpSession session) {
        model.addAttribute("gdocResoluciones", resolucionesService.buscarPorId(idGdocResolucion));
        model.addAttribute("modRes", "actualizarResolucion");
        model.addAttribute("operation", "mod");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarResoluciones/administrarResoluciones";
    }

    @PostMapping("/actualizarResolucion")
    public String modificarResoluciones(@Valid @ModelAttribute("gdocResoluciones") GdocResoluciones gdocResoluciones,
                                        BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("modRes", "actualizarResolucion");
            model.addAttribute("operation", "mod");
            desplegarListasComunes(model, session);
            return "uap/usic/siga/gdoc/administrarResoluciones/administrarResoluciones";
        }

        MultipartFile multipartFile = gdocResoluciones.getFile();
        Usuarios usuario = usuariosServicios.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("gDoc/resolucion");
        Integer resultado = adjuntarArchivo.adjuntarArchivoResolucion(gdocResoluciones, rutaArchivo);

        if (resultado == 1) {
            GdocArchivosAdjuntos archivo = resolucionesService.buscarArchivoPorResolucion(gdocResoluciones.getIdGdocResolucion());
            archivo.setNombreArchivo(gdocResoluciones.getNombreArchivo());
            archivo.setUsuarios(usuario);
            archivosService.actualizar(archivo);
        }

        resolucionesService.actualizar(gdocResoluciones);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarResoluciones/administrarResoluciones";
    }

    private void desplegarListasComunes(Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("currentUserId");
        Long idMnuTipoFuncion = 13L;

        model.addAttribute("gdocConsejos", consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion));
        model.addAttribute("listaResoluciones", resolucionesService.listarPorConsejo(
                consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion).getIdGdocConsejo()));
        model.addAttribute("listaAutoridades", resolucionesService.listarAutoridades(
                consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion).getIdGdocConsejo()));
    }
}
