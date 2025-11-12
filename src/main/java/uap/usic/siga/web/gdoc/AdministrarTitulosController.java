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
import uap.usic.siga.domain.gdoc.GdocTitulos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.service.gdoc.GdocArchivosAdjuntosService;
import uap.usic.siga.service.gdoc.GdocConsejosService;
import uap.usic.siga.service.gdoc.GdocTitulosService;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/titulos")
public class AdministrarTitulosController {

    private final GdocTitulosService titulosService;
    private final GdocArchivosAdjuntosService archivosService;
    private final GdocConsejosService consejosService;
    private final UsuariosServicios usuariosServicios;
    private final PersonasServicios personasServicios;

    public AdministrarTitulosController(GdocTitulosService titulosService,
                                        GdocArchivosAdjuntosService archivosService,
                                        GdocConsejosService consejosService,
                                        UsuariosServicios usuariosServicios,
                                        PersonasServicios personasServicios) {
        this.titulosService = titulosService;
        this.archivosService = archivosService;
        this.consejosService = consejosService;
        this.usuariosServicios = usuariosServicios;
        this.personasServicios = personasServicios;
    }

    @GetMapping("/inicioTitulos")
    public String inicioTitulos(Model model, HttpSession session) {
        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarTitulos/administrarTitulos";
    }

    @PostMapping("/inicioFormTitulos")
    public String inicioFormTitulos(@ModelAttribute("gdocTitulos") GdocTitulos gdocTitulos,
                                    Model model, HttpSession session) {
        desplegarListasComunes(model, session);
        model.addAttribute("operation", "reg");
        return "uap/usic/siga/gdoc/administrarTitulos/administrarTitulos";
    }

    @PostMapping("/registroTitulos")
    public String registrarTitulo(@Valid @ModelAttribute("gdocTitulos") GdocTitulos gdocTitulos,
                                  BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            desplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/gdoc/administrarTitulos/administrarTitulos";
        }

        MultipartFile multipartFile = gdocTitulos.getFile();
        Usuarios usuario = usuariosServicios.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("gDoc/titulos");
        adjuntarArchivo.adjuntarArchivoTitulo(gdocTitulos, rutaArchivo);

        GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
        gdocArchivoAdjunto.setNombreArchivo(gdocTitulos.getNombreArchivo());
        gdocArchivoAdjunto.setUsuarios(usuario);
        gdocArchivoAdjunto.setRutaArchivo("gDoc/titulos/");
        gdocArchivoAdjunto.setTipoArchivo(multipartFile.getContentType());

        GdocArchivosAdjuntos archivoGuardado = archivosService.guardar(gdocArchivoAdjunto);
        gdocTitulos.setGdocArchivosAdjuntos(archivoGuardado);
        titulosService.guardar(gdocTitulos);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarTitulos/administrarTitulos";
    }

    @GetMapping(value = "/openFile/{id}", produces = "application/pdf")
    @ResponseBody
    public Resource abrirArchivo(HttpServletResponse response, @PathVariable("id") Long idGdocTitulo) {
        GdocArchivosAdjuntos archivo = titulosService.buscarArchivoPorTitulo(idGdocTitulo);
        File file = new File("C:/" + archivo.getRutaArchivo() + archivo.getNombreArchivo());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));

        return new FileSystemResource(file);
    }

    @PostMapping("/inicioModificarTitulos")
    public String inicioModificarTitulos(@RequestParam("idGdocTitulo") Long idGdocTitulo,
                                         Model model, HttpSession session) {
        model.addAttribute("gdocTitulos", titulosService.buscarPorId(idGdocTitulo));
        model.addAttribute("operation", "mod");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarTitulos/administrarTitulos";
    }

    @PostMapping("/actualizarTitulos")
    public String modificarTitulo(@Valid @ModelAttribute("gdocTitulos") GdocTitulos gdocTitulos,
                                  BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("operation", "mod");
            desplegarListasComunes(model, session);
            return "uap/usic/siga/gdoc/administrarTitulos/administrarTitulos";
        }

        MultipartFile multipartFile = gdocTitulos.getFile();
        Usuarios usuario = usuariosServicios.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("gDoc/titulos");
        Integer resultado = adjuntarArchivo.adjuntarArchivoTitulo(gdocTitulos, rutaArchivo);

        if (resultado == 1) {
            GdocArchivosAdjuntos archivo = titulosService.buscarArchivoPorTitulo(gdocTitulos.getIdGdocTitulo());
            archivo.setNombreArchivo(gdocTitulos.getNombreArchivo());
            archivo.setUsuarios(usuario);
            archivosService.actualizar(archivo);
        }

        titulosService.actualizar(gdocTitulos);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarTitulos/administrarTitulos";
    }

    private void desplegarListasComunes(Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("currentUserId");
        Long idMnuTipoFuncion = 13L;

        model.addAttribute("gdocConsejos", consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion));
        model.addAttribute("listaTitulos", titulosService.listarPorConsejo(
                consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion).getIdGdocConsejo()));
        model.addAttribute("listaTiposTitulos", titulosService.listarTiposTitulos());
        model.addAttribute("listaTiposTitulosGrados", titulosService.listarTiposTitulosGrados());
    }
}
