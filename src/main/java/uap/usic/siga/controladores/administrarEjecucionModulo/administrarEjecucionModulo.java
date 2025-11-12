package uap.usic.siga.controladores.administrarEjecucionModulo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.DestinationSetter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uap.usic.siga.dto.PostulantesResponse;
import uap.usic.siga.dto.pgDto.ProgramasResponse;
import uap.usic.siga.entidadesPg.Docentes;
import uap.usic.siga.entidadesPg.EjecucionModuloEstudiante;
import uap.usic.siga.entidadesPg.EjecucionesModulos;
import uap.usic.siga.entidadesPg.PgPrgModulos;
import uap.usic.siga.entidadesPg.PgPrgPlanes;
import uap.usic.siga.entidadesPg.PgPrgPlnGrupo;
import uap.usic.siga.entidadesPg.PgPrgPlnVersiones;
import uap.usic.siga.entidadesPg.PgPstMatriculas;
import uap.usic.siga.entidadesPg.Postulantes;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.servicios.DocentesServicios;
import uap.usic.siga.servicios.MatriculaService;
import uap.usic.siga.servicios.ModuloServicio;
import uap.usic.siga.servicios.PostulantesServicios;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/aEjecuciones")
public class AdministrarEjecucionModulo {
    @Autowired private ProgramasService prgServicios;
    @Autowired private DocentesServicios docServicios;
    @Autowired private PostulantesServicios pstServicios;
    @Autowired private ModuloServicio modServicios;
    @Autowired private MatriculaService matServicios;

    @Autowired private ModelMapper modelMapper;

    private String URL = "uap/usic/siga/pg/administrarModulos/admEjecucionesModulos";
    private String REDIRECT = "redirect:/aEjecuciones/administrarGrupo=";

    @GetMapping("/listarProgramasLanzados")
    public String listarProgramas(Model model, HttpSession session){
        model.addAttribute("operation", "tabla");
        model.addAttribute("lVersiones", prgServicios.listaPgPrgPlnVersionesJPLQ());
        getDesplegarListasComunes(model, session);
        return URL;
    }

    @GetMapping("/administrarGrupo={idGrupo}")
    public String administrarGrupo(@PathVariable Long idGrupo, Model model, HttpSession session){
        PgPrgPlnGrupo grupo = prgServicios.buscarGrupoPorIdGrupo(idGrupo);
        PgPrgPlnVersiones version = grupo.getVersion();
        PgPrgPlanes plan = version.getPlan();
        List<EjecucionesModulos> ejecuciones = modServicios.listarEjecucionesModulosPorIdGrupo(idGrupo);

        model.addAttribute("ejecucion", "tablaModulos");
        model.addAttribute("grupo",grupo);
        model.addAttribute("plan", plan);
        model.addAttribute("version", version);
        model.addAttribute("lEjecuciones", ejecuciones);
        getDesplegarListasComunes(model, session);
        return URL;
    }

    @PostMapping("/registrarEjecucionModulo")
    public String registrarEjecucionModulo(@RequestParam Long idEjecucion, @RequestParam Long idDocente, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaConclusion, Model model, HttpSession session){

        var ejecucionModulo = modServicios.buscarEjecucionesModulosPorId(idEjecucion);
        var docente = docServicios.buscarDocentePorId(idDocente);
        docente.setEstado('E');
        ejecucionModulo.setDocente(docente);
        ejecucionModulo.setFechaInicio(fechaInicio);
        ejecucionModulo.setFechaConclusion(fechaConclusion);
        ejecucionModulo.setEstado('E');

        for(var matricula : ejecucionModulo.getGrupo().getMatriculas()){
            var estudiante = new EjecucionModuloEstudiante(matricula, ejecucionModulo);
            modServicios.registrarEjecucionModuloEstudiante(estudiante);
        }

        modServicios.modificarEjecucionesModulos(ejecucionModulo);
        model.addAttribute("ejecucion", "tablaModulos");
        return REDIRECT+ejecucionModulo.getGrupo().getIdPgPrgPlnGrupos();
    }

    // @ResponseBody
    // @PostMapping("/registrarEjecucionModulo")
    // public ResponseEntity<EjecucionesModulos> registrarResponseEjecucionModulo(@RequestParam Long idEjecucion, @RequestParam Long idDocente,
    //     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio, 
    //     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaConclusion, HttpSession session){

    //     var em = modServicios.buscarEjecucionesModulosPorId(idEjecucion);
    //     var doc = docServicios.buscarDocentePorId(idDocente);
    //     doc.setEstado('E');
    //     em.setDocente(doc);

    //     em.setFechaInicio(fechaInicio);
    //     em.setFechaConclusion(fechaConclusion);
    //     em.setEstado('E');

    //     modServicios.modificarEjecucionesModulos(em);
    //     return ResponseEntity.status(HttpStatus.OK).body(em);
    // }

    @ResponseBody
    @GetMapping("/api/ejecucionModulo={idEjecucion}")
    public ResponseEntity<EjecucionesModulos> editarEjecucion(@PathVariable Long idEjecucion, Model model, HttpSession session){
        var em = modServicios.buscarEjecucionesModulosPorId(idEjecucion);
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }

    @GetMapping("/api/grupos-idVersion={idVersion}")
    @ResponseBody
    public ResponseEntity<List<PgPrgPlnGrupo>> listarGruposPorIdVersion(@PathVariable Long idVersion, HttpSession session){
        List<PgPrgPlnGrupo> dto = prgServicios.ListaGruposPorIdPgPrgPlnVersiones(idVersion);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/api/docentes")
    @ResponseBody
    public ResponseEntity<List<Docentes>> listarDocentesDisponibles(HttpSession session){
        List<Docentes> docentes = docServicios.listarDocentesPorPaterno();
        return ResponseEntity.status(HttpStatus.OK).body(docentes);
    }

    @GetMapping("/api/programa-id={idPrograma}")
    @ResponseBody
    public ProgramasResponse listarModulosPorIdPrograma(@PathVariable("idPrograma") Long idPrograma, Model model, HttpSession session){
        Programas prg = prgServicios.buscarProgramaPorIdPrograma(idPrograma);
        return modelMapper.typeMap(Programas.class,ProgramasResponse.class).addMappings(
            mapper -> {
                mapper.skip(ProgramasResponse::setPostulantes);
                mapper.skip(ProgramasResponse::setPostulantesAdmitidos);
            }).map(prg);   
    }

    //Prueba con DTO, si tarda mucho, no quiero pensar con 10000 registros...
    @GetMapping("api/postulantes=A")
    @ResponseBody
    public List<PostulantesResponse> listarPostulantesExistentes(Model model, HttpSession session){
        List<Postulantes> pst = pstServicios.listarPostulantesPorIdGET();
        List<PostulantesResponse> dto = pst.stream().map(e -> modelMapper.
            typeMap(Postulantes.class, PostulantesResponse.class).
            addMappings(mapper -> {mapper.skip(PostulantesResponse::setPgPstProgramas);}).map(e)
        ).collect(Collectors.toList());
        return dto;
    }

    //Prueba sin DTO
    @GetMapping("api/response2")
    @ResponseBody
    public List<Postulantes> buscarPostulantesPorId(Model model, HttpSession session){
        List<Postulantes> lPostulantes = pstServicios.listarPostulantesSimpleResponse();
        return lPostulantes;
    }

    private void getDesplegarListasComunes(Model model, HttpSession session) {

	}
}
