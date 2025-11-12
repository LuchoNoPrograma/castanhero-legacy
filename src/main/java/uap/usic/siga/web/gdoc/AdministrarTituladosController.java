package uap.usic.siga.web.gdoc;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
import uap.usic.siga.domain.gdoc.GdocTitulados;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.service.gdoc.GdocArchivosAdjuntosService;
import uap.usic.siga.service.gdoc.GdocConsejosService;
import uap.usic.siga.service.gdoc.GdocTituladosService;
import uap.usic.siga.service.gdoc.GdocTitulosService;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/titulados")
public class AdministrarTituladosController {

    private final GdocTituladosService tituladosService;
    private final GdocTitulosService titulosService;
    private final GdocArchivosAdjuntosService archivosService;
    private final GdocConsejosService consejosService;
    private final UsuariosServicios usuariosServicios;
    private final PersonasServicios personasServicios;

    public AdministrarTituladosController(GdocTituladosService tituladosService,
                                          GdocTitulosService titulosService,
                                          GdocArchivosAdjuntosService archivosService,
                                          GdocConsejosService consejosService,
                                          UsuariosServicios usuariosServicios,
                                          PersonasServicios personasServicios) {
        this.tituladosService = tituladosService;
        this.titulosService = titulosService;
        this.archivosService = archivosService;
        this.consejosService = consejosService;
        this.usuariosServicios = usuariosServicios;
        this.personasServicios = personasServicios;
    }

    @GetMapping("/inicioTitulados")
    public String inicioTitulados(Model model, HttpSession session) {
        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarTitulados/administrarTitulados";
    }

    @PostMapping("/inicioFormTitulados")
    public String inicioFormTitulados(@ModelAttribute("gdocTitulados") GdocTitulados gdocTitulados,
                                      Model model, HttpSession session) {
        desplegarListasComunes(model, session);
        model.addAttribute("operation", "reg");
        return "uap/usic/siga/gdoc/administrarTitulados/administrarTitulados";
    }

    @PostMapping("/registroTitulados")
    public String registrarTitulado(@Valid @ModelAttribute("gdocTitulados") GdocTitulados gdocTitulados,
                                    BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            desplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/gdoc/administrarTitulados/administrarTitulados";
        }

        MultipartFile multipartFile = gdocTitulados.getFile();
        Usuarios usuario = usuariosServicios.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("gDoc/titulados");
        adjuntarArchivo.adjuntarArchivoTitulado(gdocTitulados, rutaArchivo);

        GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
        gdocArchivoAdjunto.setNombreArchivo(gdocTitulados.getNombreArchivo());
        gdocArchivoAdjunto.setUsuarios(usuario);
        gdocArchivoAdjunto.setRutaArchivo("gDoc/titulados/");
        gdocArchivoAdjunto.setTipoArchivo(multipartFile.getContentType());

        GdocArchivosAdjuntos archivoGuardado = archivosService.guardar(gdocArchivoAdjunto);
        gdocTitulados.setGdocArchivosAdjuntos(archivoGuardado);
        tituladosService.guardar(gdocTitulados);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarTitulados/administrarTitulados";
    }

    @GetMapping(value = "/openFile/{id}", produces = "application/pdf")
    @ResponseBody
    public Resource abrirArchivo(HttpServletResponse response, @PathVariable("id") Long idGdocTitulado) {
        GdocArchivosAdjuntos archivo = tituladosService.buscarArchivoPorTitulado(idGdocTitulado);
        File file = new File("C:/" + archivo.getRutaArchivo() + archivo.getNombreArchivo());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));

        return new FileSystemResource(file);
    }

    @PostMapping("/inicioModificarTitulados")
    public String inicioModificarTitulados(@RequestParam("idGdocTitulado") Long idGdocTitulado,
                                           Model model, HttpSession session) {
        model.addAttribute("gdocTitulados", tituladosService.buscarPorId(idGdocTitulado));
        model.addAttribute("operation", "mod");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarTitulados/administrarTitulados";
    }

    @PostMapping("/actualizarTitulados")
    public String modificarTitulado(@Valid @ModelAttribute("gdocTitulados") GdocTitulados gdocTitulados,
                                    BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("operation", "mod");
            desplegarListasComunes(model, session);
            return "uap/usic/siga/gdoc/administrarTitulados/administrarTitulados";
        }

        MultipartFile multipartFile = gdocTitulados.getFile();
        Usuarios usuario = usuariosServicios.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("gDoc/titulados");
        Integer resultado = adjuntarArchivo.adjuntarArchivoTitulado(gdocTitulados, rutaArchivo);

        if (resultado == 1) {
            GdocArchivosAdjuntos archivo = tituladosService.buscarArchivoPorTitulado(gdocTitulados.getIdGdocTitulado());
            archivo.setNombreArchivo(gdocTitulados.getNombreArchivo());
            archivo.setUsuarios(usuario);
            archivosService.actualizar(archivo);
        }

        tituladosService.actualizar(gdocTitulados);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarTitulados/administrarTitulados";
    }

    private void desplegarListasComunes(Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("currentUserId");
        Long idMnuTipoFuncion = 13L;

        model.addAttribute("gdocConsejos", consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion));
        Long idConsejo = consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion).getIdGdocConsejo();

        model.addAttribute("listaTitulados", tituladosService.listarPorConsejo(idConsejo));
        model.addAttribute("listaTitulos", titulosService.listarPorConsejo(idConsejo));
        model.addAttribute("listaPersonas", personasServicios.listarPersonas());
    }
}
