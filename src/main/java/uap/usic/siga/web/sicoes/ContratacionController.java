package uap.usic.siga.web.sicoes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uap.usic.siga.domain.sicoes.ScsArchivoAdjunto;
import uap.usic.siga.domain.sicoes.ScsContratacion;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.service.sicoes.SicoesService;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/sicoes/contrataciones")
@Slf4j
@RequiredArgsConstructor
public class ContratacionController {

    private final SicoesService sicoesService;

    private final PersonasServicios personasServicios;

    private final CajitaServicios cajitaServicios;

    @GetMapping
    public String inicio(HttpSession session, Model model) {
        cargarListasComunes(model);
        model.addAttribute("scsContratacion", new ScsContratacion());
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/contrataciones/index";
    }

    @PostMapping("/nueva")
    public String mostrarFormularioNueva(@ModelAttribute("scsContratacion") ScsContratacion contratacion, Model model) {
        model.addAttribute("operation", "reg");
        cargarListasComunes(model);
        return "uap/usic/siga/sicoes/contrataciones/index";
    }

    @PostMapping
    public String registrar(@Valid @ModelAttribute("scsContratacion") ScsContratacion contratacion,
                           BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("operation", "reg");
            cargarListasComunes(model);
            return "uap/usic/siga/sicoes/contrataciones/index";
        }

        MultipartFile multipartFile = contratacion.getFile();
        Personas persona = personasServicios.buscarPersonasPorIdPersona((Long) session.getAttribute("idPersona"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();

        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/contratos");
        adjuntarArchivo.adjuntarArchivoSicoes(contratacion, rutaArchivo);

        ScsArchivoAdjunto archivoAdjunto = new ScsArchivoAdjunto();
        archivoAdjunto.setNombreArchivo(contratacion.getNombreArchivo());
        archivoAdjunto.setTipoArchivo(multipartFile.getContentType());
        archivoAdjunto.setPersona(persona);
        archivoAdjunto.setRutaArchivo("Sicoes/contratos/");
        ScsArchivoAdjunto archivoGuardado = sicoesService.registrarArchivoAdjunto(archivoAdjunto);

        contratacion.setArchivoAdjunto(archivoGuardado);
        contratacion.setPersona(persona);
        sicoesService.registrarContratacion(contratacion);

        cargarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/contrataciones/index";
    }

    @PostMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        ScsContratacion contratacion = sicoesService.buscarContratacionPorId(id);
        model.addAttribute("scsContratacion", contratacion);
        model.addAttribute("operation", "modif");
        cargarListasComunes(model);
        return "uap/usic/siga/sicoes/contrataciones/index";
    }

    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("scsContratacion") ScsContratacion contratacion,
                            BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("operation", "modif");
            cargarListasComunes(model);
            return "uap/usic/siga/sicoes/contrataciones/index";
        }

        MultipartFile multipartFile = contratacion.getFile();
        Personas persona = personasServicios.buscarPersonasPorIdPersona((Long) session.getAttribute("idPersona"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/contratos");
        Integer ad = adjuntarArchivo.adjuntarArchivoSicoes(contratacion, rutaArchivo);

        if (ad == 1) {
            ScsArchivoAdjunto archivoExistente = sicoesService.buscarArchivoAdjuntoPorId(
                contratacion.getArchivoAdjunto().getIdScsArchivoAdjunto());
            archivoExistente.setNombreArchivo(contratacion.getNombreArchivo());
            archivoExistente.setPersona(persona);
            sicoesService.modificarArchivoAdjunto(archivoExistente);
        }

        contratacion.setPersona(persona);
        sicoesService.modificarContratacion(contratacion);

        cargarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/contrataciones/index";
    }

    @PostMapping("/eliminar/{id}")
    public String mostrarFormularioEliminar(@PathVariable("id") Long id, Model model) {
        ScsContratacion contratacion = sicoesService.buscarContratacionPorId(id);
        model.addAttribute("scsContratacion", contratacion);
        model.addAttribute("operation", "delet");
        cargarListasComunes(model);
        return "uap/usic/siga/sicoes/contrataciones/index";
    }

    @PostMapping("/confirmar-eliminar")
    public String eliminar(@ModelAttribute("scsContratacion") ScsContratacion contratacion, Model model) {
        contratacion.setIdEstado("X");
        sicoesService.modificarContratacion(contratacion);

        cargarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/contrataciones/index";
    }

    private void cargarListasComunes(Model model) {
        model.addAttribute("contrataciones", sicoesService.listarContrataciones());
        model.addAttribute("proyectos", sicoesService.listarProyectos());
        model.addAttribute("tiposContratos", sicoesService.listarTiposContratos());
        model.addAttribute("modalidades", sicoesService.listarModalidades());
        model.addAttribute("tiposModalidades", sicoesService.listarTiposModalidades());
        model.addAttribute("personas", personasServicios.listarPersonasComprobantes());
        model.addAttribute("tiposGastos", cajitaServicios.listarTiposGastos());
        model.addAttribute("formularios", sicoesService.listarFormularios());
        model.addAttribute("boletasRespaldatorias", sicoesService.listarBoletasRespaldatorias());
        model.addAttribute("prsContratados", sicoesService.listarPrsContratados());
    }
}
