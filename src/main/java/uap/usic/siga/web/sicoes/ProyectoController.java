package uap.usic.siga.web.sicoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uap.usic.siga.domain.sicoes.ScsProyecto;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.service.sicoes.SicoesService;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/sicoes/proyectos")
public class ProyectoController {

    @Autowired
    private SicoesService sicoesService;

    @Autowired
    private InstitucionesServicios institucionesServicios;

    @Autowired
    private UserService userService;

    @GetMapping
    public String inicio(HttpSession session, Model model) {
        cargarListasComunes(model);
        model.addAttribute("scsProyecto", new ScsProyecto());
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/proyectos/index";
    }

    @PostMapping("/nuevo")
    public String mostrarFormularioNuevo(@ModelAttribute("scsProyecto") ScsProyecto proyecto, Model model) {
        model.addAttribute("operation", "reg");
        cargarListasComunes(model);
        return "uap/usic/siga/sicoes/proyectos/index";
    }

    @PostMapping
    public String registrar(@Valid @ModelAttribute("scsProyecto") ScsProyecto proyecto,
                           BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("operation", "reg");
            cargarListasComunes(model);
            return "uap/usic/siga/sicoes/proyectos/index";
        }

        Usuarios usuario = userService.findByIdEagerly((Long) session.getAttribute("idUsr"));
        proyecto.setUsuario(usuario);
        sicoesService.registrarProyecto(proyecto);

        cargarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/proyectos/index";
    }

    @PostMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        ScsProyecto proyecto = sicoesService.buscarProyectoPorId(id);
        model.addAttribute("scsProyecto", proyecto);
        model.addAttribute("operation", "modif");
        cargarListasComunes(model);
        return "uap/usic/siga/sicoes/proyectos/index";
    }

    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("scsProyecto") ScsProyecto proyecto,
                            BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("operation", "modif");
            cargarListasComunes(model);
            return "uap/usic/siga/sicoes/proyectos/index";
        }

        Usuarios usuario = userService.findByIdEagerly((Long) session.getAttribute("idUsr"));
        proyecto.setUsuario(usuario);
        sicoesService.actualizarProyecto(proyecto);

        cargarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/proyectos/index";
    }

    @PostMapping("/eliminar/{id}")
    public String mostrarFormularioEliminar(@PathVariable("id") Long id, Model model) {
        ScsProyecto proyecto = sicoesService.buscarProyectoPorId(id);
        model.addAttribute("scsProyecto", proyecto);
        model.addAttribute("operation", "delet");
        cargarListasComunes(model);
        return "uap/usic/siga/sicoes/proyectos/index";
    }

    @PostMapping("/confirmar-eliminar")
    public String eliminar(@ModelAttribute("scsProyecto") ScsProyecto proyecto,
                          HttpSession session, Model model) {
        Usuarios usuario = userService.findByIdEagerly((Long) session.getAttribute("idUsr"));
        proyecto.setUsuario(usuario);
        proyecto.setIdEstado("X");
        sicoesService.actualizarProyecto(proyecto);

        cargarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "uap/usic/siga/sicoes/proyectos/index";
    }

    private void cargarListasComunes(Model model) {
        model.addAttribute("proyectos", sicoesService.listarProyectos());
        model.addAttribute("unidades", institucionesServicios.listarUnidadesFuncionales());
    }
}
