package uap.usic.siga.web.sicoes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uap.usic.siga.domain.sicoes.ScsArchivoAdjunto;
import uap.usic.siga.domain.sicoes.ScsPrsContratado;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.service.sicoes.SicoesService;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/sicoes/contratados")
@Slf4j
@RequiredArgsConstructor
public class ContratadoController {

    private final SicoesService sicoesService;

    private final PersonasServicios personasServicios;

    @GetMapping
    public String inicio(HttpSession session, Model model) {
        cargarListasComunes(model);
        model.addAttribute("scsPrsContratado", new ScsPrsContratado());
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/contratados/index";
    }

    @PostMapping("/nuevo")
    public String mostrarFormularioNuevo(@ModelAttribute("scsPrsContratado") ScsPrsContratado contratado, Model model) {
        model.addAttribute("operation", "reg");
        cargarListasComunes(model);
        return "uap/usic/siga/sicoes/contratados/index";
    }

    @PostMapping
    public String registrar(@Valid @ModelAttribute("scsPrsContratado") ScsPrsContratado contratado,
                           BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("operation", "reg");
            cargarListasComunes(model);
            return "uap/usic/siga/sicoes/contratados/index";
        }

        MultipartFile multipartFile = contratado.getFile();
        Personas persona = personasServicios.buscarPersonasPorIdPersona((Long) session.getAttribute("idPersona"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();

        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/fotos");
        adjuntarArchivo.adjuntarFotosSicoes(contratado, rutaArchivo);

        ScsArchivoAdjunto archivoAdjunto = new ScsArchivoAdjunto();
        archivoAdjunto.setNombreArchivo(contratado.getNombreArchivo());
        archivoAdjunto.setTipoArchivo(multipartFile.getContentType());
        archivoAdjunto.setPersona(persona);
        archivoAdjunto.setRutaArchivo("Sicoes/fotos/");
        ScsArchivoAdjunto archivoGuardado = sicoesService.registrarArchivoAdjunto(archivoAdjunto);

        contratado.setArchivoAdjunto(archivoGuardado);
        sicoesService.registrarPrsContratado(contratado);

        cargarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/contratados/index";
    }

    @PostMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        ScsPrsContratado contratado = sicoesService.buscarPrsContratadoPorId(id);
        model.addAttribute("scsPrsContratado", contratado);
        model.addAttribute("operation", "modif");
        cargarListasComunes(model);
        return "uap/usic/siga/sicoes/contratados/index";
    }

    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("scsPrsContratado") ScsPrsContratado contratado,
                            BindingResult result, HttpSession session, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("operation", "modif");
            cargarListasComunes(model);
            return "uap/usic/siga/sicoes/contratados/index";
        }

        Personas persona = personasServicios.buscarPersonasPorIdPersona((Long) session.getAttribute("idPersona"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/fotos");
        Integer ad = adjuntarArchivo.adjuntarFotosSicoes(contratado, rutaArchivo);

        if (ad == 1) {
            ScsArchivoAdjunto archivoExistente = sicoesService.buscarArchivoAdjuntoPorId(
                contratado.getArchivoAdjunto().getIdScsArchivoAdjunto());
            archivoExistente.setNombreArchivo(contratado.getNombreArchivo());
            archivoExistente.setPersona(persona);
            sicoesService.modificarArchivoAdjunto(archivoExistente);
        }

        sicoesService.actualizarPrsContratado(contratado);

        cargarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/contratados/index";
    }

    @PostMapping("/eliminar/{id}")
    public String mostrarFormularioEliminar(@PathVariable("id") Long id, Model model) {
        ScsPrsContratado contratado = sicoesService.buscarPrsContratadoPorId(id);
        model.addAttribute("scsPrsContratado", contratado);
        model.addAttribute("operation", "delet");
        cargarListasComunes(model);
        return "uap/usic/siga/sicoes/contratados/index";
    }

    @PostMapping("/confirmar-eliminar")
    public String eliminar(@ModelAttribute("scsPrsContratado") ScsPrsContratado contratado, Model model) {
        contratado.setIdEstado("X");
        sicoesService.actualizarPrsContratado(contratado);

        cargarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/contratados/index";
    }

    private void cargarListasComunes(Model model) {
        model.addAttribute("contratados", sicoesService.listarPrsContratados());
        model.addAttribute("personas", personasServicios.listarPersonasComprobantes());
    }
}
