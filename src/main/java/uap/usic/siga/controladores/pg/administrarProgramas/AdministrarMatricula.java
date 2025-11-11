package uap.usic.siga.controladores.pg.administrarProgramas;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.entidadesPg.Depositos;
import uap.usic.siga.entidadesPg.PgArchivosAdjuntos;
import uap.usic.siga.entidadesPg.PgPrgArancel;
import uap.usic.siga.entidadesPg.PgPrgPlnGrupo;
import uap.usic.siga.entidadesPg.PgPrgPlnVersiones;
import uap.usic.siga.entidadesPg.PgPstMatriculas;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.entidadesPg.Postulantes;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.servicios.MatriculaService;
import uap.usic.siga.servicios.PostulantesServicios;
import uap.usic.siga.servicios.ProgramasService;
import uap.usic.siga.servicios.UserService;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/aMatriculas")
public class AdministrarMatricula {

    @Autowired
	private MatriculaService mMatricula;

    @Autowired
    private PostulantesServicios mPostulantes;

    @Autowired
    private ProgramasService mProgramas;

    @Autowired
    private UserService userService;

    private final String URL = "uap/usic/siga/pg/administrarProgramas/admMatriculas";

    private final String REDIRECT = "redirect:/aPostulantesAdmitidos/listarAdmitidosPorPrograma=";
    

    @GetMapping("formMatricula={idPstProgramas}")
	public String inicioFormMatricula(@PathVariable("idPstProgramas")Long idPstProgramas,
    @ModelAttribute("depositos")Depositos deposito, Model model, HttpSession session){

        PgPstProgramas postulante;
        postulante=mPostulantes.buscarPgPstProgramasPorIdGET(idPstProgramas);
        Postulantes estudiante;
        estudiante=postulante.getPostulantes();
        Long idPro;
        idPro=postulante.getProgramas().getIdPrograma();
        Programas programa;
        programa=mProgramas.buscarProgramaPorIdPrograma(idPro);
        PgPrgArancel arancel;
        arancel=mProgramas.buscarArancelPorIdPrograma(idPro);
        PgPrgPlnVersiones version;
        Long idVer;
        version=mProgramas.buscarVersionPorIdPrograma(idPro);
        idVer=version.getIdPgPrgPlnVersiones();

        model.addAttribute("postulantes", postulante);
        model.addAttribute("programas", programa);
        model.addAttribute("aranceles", arancel);
        model.addAttribute("estudiantes", estudiante);
        model.addAttribute("lGrupos", mProgramas.ListaGruposPorIdPgPrgPlnVersiones(idVer));

	    model.addAttribute("operation", "pago");
	    getDesplegarListasComunes(model, session);
	    return "uap/usic/siga/pg/administrarProgramas/admMatriculas";
	}

    @PostMapping(value = "/matricular")
	public String matriculacion(@Valid @ModelAttribute("depositos") Depositos deposito,
    @ModelAttribute("matriculas") PgPstMatriculas matricula,BindingResult result, HttpSession session, Model model) throws IOException {
		
        if (result.hasErrors()) {
			model.addAttribute("operation", "reg");
			getDesplegarListasComunes(model, session);
			return "uap/usic/siga/pg/administrarProgramas/admMatriculas";
		}

            String nombre=deposito.getCodDeposito();
            deposito.setNombreArchivo(nombre);

            Usuarios usuarios = userService.findByIdEagerly((Long) session.getAttribute("idUsr"));        
	        MultipartFile multipartFile = deposito.getFile();
	        PgArchivosAdjuntos pgArchivosAdjuntos = new PgArchivosAdjuntos();
	       
	        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
	    
	        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Pg/version");
	        model.addAttribute("di", rutaArchivo);
	        Integer ad = adjuntarArchivo.adjuntarArchivosDepositos(deposito, rutaArchivo);

	        pgArchivosAdjuntos.setNombreArchivo(deposito.getNombreArchivo());
	        pgArchivosAdjuntos.setTipoArchivo(multipartFile.getContentType());
	        pgArchivosAdjuntos.setUsuarios(usuarios);
	        pgArchivosAdjuntos.setRutaArchivo("Pg/depositos/");
	        PgArchivosAdjuntos pgArchivosAdjuntos1 = mProgramas.registrarPgArchivosAdjuntosSET(pgArchivosAdjuntos);

            deposito.setPgArchivosAdjuntos(pgArchivosAdjuntos1);
            deposito.setUsuarios(usuarios);
    
        Long idPst;
        idPst=deposito.getPgPstProgramas().getIdPstProgramas();
        PgPstProgramas postulante;
        postulante=mPostulantes.buscarPgPstProgramasPorIdGET(idPst);
        Programas programa;
        programa=postulante.getProgramas();
        Long idPro=programa.getIdPrograma();
        PgPrgArancel arancel;
        arancel=mProgramas.buscarArancelPorIdPrograma(idPro);
        Float total=arancel.getMatricula();
        deposito.setCantDeposito(total);
        mMatricula.registrarDepositos(deposito);

        Long idEst;
        idEst=postulante.getPostulantes().getIdPostulante();
        Postulantes estudiante;
        estudiante=mPostulantes.buscarPostulantesPorIdGET(idEst);
    
        
        PgPrgPlnVersiones version;
        version=mProgramas.buscarVersionPorIdPrograma(idPro);
        Long idVer;
        idVer=version.getIdPgPrgPlnVersiones();

        getDesplegarListasComunes(model, session);
        model.addAttribute("est", estudiante);
        model.addAttribute("postulantes", postulante);
        model.addAttribute("programas", programa);
        model.addAttribute("operation", "matricula");
        model.addAttribute("lGrupos", mProgramas.ListaGruposPorIdPgPrgPlnVersiones(idVer));
	    return "uap/usic/siga/pg/administrarProgramas/admMatriculas";

	}

    @PostMapping(value = "/matriculacion")
	public String matricular(@Valid @ModelAttribute("matriculas") PgPstMatriculas matricula, 
    BindingResult result, HttpSession session, Model model) throws IOException {
		
        if (result.hasErrors()) {
			model.addAttribute("operation", "reg");
			getDesplegarListasComunes(model, session);
			return "uap/usic/siga/pg/administrarProgramas/admMatriculas";
		}
        PgPstProgramas postulante;
        Long idPro;
        Long idPst;
        idPst=matricula.getPstProgramas().getIdPstProgramas();
        postulante=mPostulantes.buscarPgPstProgramasPorIdGET(idPst);
        idPro=postulante.getProgramas().getIdPrograma();
        Programas programa;
        programa=mProgramas.buscarProgramaPorIdPrograma(idPro);
        String sigla;
        sigla=programa.getSigla();


        Random rnd = new Random();
        sigla=sigla+"-"+rnd.nextInt(1000);

        matricula.setNumMatricula(sigla);
        mMatricula.registrarMatriculas(matricula);


        postulante.setEstado('M');
        mPostulantes.modificarPgPstProgramasSET(postulante);

        getDesplegarListasComunes(model, session);
        model.addAttribute("operation", "matricula");
	    return REDIRECT+idPro;

	}

    //===============================================================================================
    private void getDesplegarListasComunes(Model model, HttpSession session) {

        model.addAttribute("lMatriculas", mMatricula.listaMatriculas());
        model.addAttribute("lDepositos", mMatricula.listaDepositos());

    }
    
}
