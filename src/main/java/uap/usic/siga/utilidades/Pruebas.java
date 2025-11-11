package uap.usic.siga.utilidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import uap.usic.siga.entidades.Menues;
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuFunciones;
import uap.usic.siga.entidades.MnuTiposEnlaces;
import uap.usic.siga.entidades.MnuTiposFunciones;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PrsCiExpedidos;
import uap.usic.siga.entidades.PrsEstadoCivil;
import uap.usic.siga.entidades.PrsGradosAcademicos;
import uap.usic.siga.entidades.PrsPaises;
import uap.usic.siga.entidades.PrsTiposSexos;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.entidades.SisNivelesAccesos;
import uap.usic.siga.entidadesPg.Docentes;
import uap.usic.siga.entidadesPg.EjecucionModuloEstudiante;
import uap.usic.siga.entidadesPg.EjecucionesModulos;
import uap.usic.siga.entidadesPg.GradoAcademico;
import uap.usic.siga.entidadesPg.PgAdmTiposAdmisiones;
import uap.usic.siga.entidadesPg.PgPrgModalidad;
import uap.usic.siga.entidadesPg.PgPrgModulos;
import uap.usic.siga.entidadesPg.PgPstMatriculas;
import uap.usic.siga.entidadesPg.Postulantes;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.servicios.DocentesServicios;
import uap.usic.siga.servicios.MatriculaService;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.ModuloServicio;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PostulantesServicios;
import uap.usic.siga.servicios.ProgramasService;
import uap.usic.siga.servicios.UsuariosServicios;

@Component
public class Pruebas {
    @Autowired
	private PersonasServicios prsServicios;

	@Autowired
	private PostulantesServicios pstServicios;

	@Autowired
	private DocentesServicios docServicios;

	@Autowired
	private ProgramasService prgServicios;

	@Autowired
	private MenuesServicios menuServicios;

	@Autowired
	private UsuariosServicios usuServicios;

    @Autowired
    private ModuloServicio modServicios;

	@Autowired
	private MatriculaService mtrServicios;
	/* */
    // @EventListener(ApplicationReadyEvent.class)
    public void pruebasDao(){
		// var ejecucion1 = modServicios.buscarEjecucionesModulosPorId(1L);
		// Set<PgPrgModulos> modDocente = modServicios.listarModulosPorDocente(docServicios.buscarDocentePorId(1L));
		// List<EjecucionModuloEstudiante> estMod1 = modServicios.listarEjecucionesModuloEstudiantePorIdModulo(j1L);
		// List<EjecucionModuloEstudiante> estEje1 = modServicios.listarEjecucionModuloEstudiantePorIdEjecucion(2L);

		// var doc1 = docServicios.buscarDocentePorId(2L);
		// EjecucionesModulos em = new EjecucionesModulos();
		// em.setDocente(doc1);
		// em.setModulo(prgServicios.buscarNodulosPorIdModulo(2L));
		// modServicios.registrarEjecucionesModulos(em);

		// ArrayList<EjecucionModuloEstudiante> estudiantes1 = new ArrayList<>();
		// List<PgPstMatriculas> matriculas = mtrServicios.listarMatriculasPorIdGrupo(2L);
		// matriculas.forEach(e -> {
		// 	int notaFinal = (int)Math.floor(Math.random()*100);
		// 	EjecucionModuloEstudiante est = new EjecucionModuloEstudiante();
		// 	est.setEjecucionModulo(modServicios.buscarEjecucionesModulosPorId(2L));
		// 	est.setMatriculas(e);
		// 	est.setNotaFinal((short)notaFinal);

		// 	modServicios.registrarEjecucionModuloEstudiante(est);
		// 	estudiantes1.add(est);
		// });
		
		System.out.println(modServicios.buscarEjecucionesModulosPorId(1L));
		System.out.println(modServicios.buscarEjecucionesModulosPorId(2L));


		// var eme1 = modServicios.buscarEjecucionModuloEstudiantePorId(1L);
		// eme1.setNotaFinal((short) 54);
		// modServicios.modificarEjecucionModuloEstudiante(eme1);

		// var eme2 = modServicios.buscarEjecucionModuloEstudiantePorId(2L);
		// eme2.setNotaFinal((short) 89);
		// modServicios.modificarEjecucionModuloEstudiante(eme2);

		// var eme3 = modServicios.buscarEjecucionModuloEstudiantePorId(3L);
		// eme3.setNotaFinal((short)0);
		// modServicios.modificarEjecucionModuloEstudiante(eme3);

		//grupo -> version -> plan -> programa
    }

	
	// @EventListener(ApplicationReadyEvent.class)
	public void ingresoDatosIniciales(){
		//=============PERSONAS=================
		var sexo1 = new PrsTiposSexos("Masculino");
		var sexo2 = new PrsTiposSexos("Femenino");
		prsServicios.registrarPrsTiposSexos(sexo1);
		prsServicios.registrarPrsTiposSexos(sexo2);

		var ci1 = new PrsCiExpedidos("Cochabamba", "CBBA");
		var ci2 = new PrsCiExpedidos("Pando", "PD");
		var ci3 = new PrsCiExpedidos("La paz", "LPZ");
		var ci4 = new PrsCiExpedidos("Santa cruz", "SCZ");
		var ci5 = new PrsCiExpedidos("Beni", "BN");
		var ci6 = new PrsCiExpedidos("Chuquisaca", "CH");
		var ci7 = new PrsCiExpedidos("Tarija", "TJ");
		var ci8 = new PrsCiExpedidos("Potosi", "PO");
		var ci9 = new PrsCiExpedidos("Oruro", "OR");
		List<PrsCiExpedidos> cis = List.of(ci1,ci2,ci3,ci4,ci5,ci6,ci7,ci8,ci9);
		cis.forEach(ci -> prsServicios.registrarPrsCedulaIdentidadExpedidos(ci));

		var pais = new PrsPaises("Bolivia", "Boliviana");
		var pais2 = new PrsPaises("Peru", "Peruana");
		prsServicios.registrarPrsPaises(pais);
		prsServicios.registrarPrsPaises(pais2);
	
		List<PrsEstadoCivil> estadosCiviles = List.of(
			new PrsEstadoCivil("Casado/a"), new PrsEstadoCivil("Soltero/a"),
		 	new PrsEstadoCivil("Viudo/a"), new PrsEstadoCivil("Divorciado/a"));
		estadosCiviles.forEach(e -> prsServicios.registrarPrsEstadoCivil(e));

		var grado1 = new PrsGradosAcademicos("Licenciatura");
		var grado2 = new PrsGradosAcademicos("Tecnico");
		prsServicios.registrarPrsGradosAcademicos(grado1);
		prsServicios.registrarPrsGradosAcademicos(grado2);

		var virtual = new PgPrgModalidad("Virtual", (short) 2);
		var semi = new PgPrgModalidad("Semipresencial", (short) 3);
		prgServicios.registrarPgPrgModalidad(virtual);
		prgServicios.registrarPgPrgModalidad(semi);

		var diplomado = new GradoAcademico("Diplomado");
		var espec = new GradoAcademico("Especialidad");
		var maestria = new GradoAcademico("Maestria");
		var doc = new GradoAcademico("Doctorado");

		prgServicios.registrarGradoAcademicoSet(diplomado);
		prgServicios.registrarGradoAcademicoSet(espec);
		prgServicios.registrarGradoAcademicoSet(maestria);
		prgServicios.registrarGradoAcademicoSet(doc);
		
		var prs = new Personas(pais, sexo1, ci1, grado1, estadosCiviles.get(0),"Admin", "Paterno", "Materno", "2022", 
		new Date(), "Direccion generica", "70707070", "O RH+");
		prsServicios.registrarPersonas(prs);
		System.out.println("Admin Registrado");


		//==========ADMITIDOS(PGPSTPROGRAMAS)===========
		List<PgAdmTiposAdmisiones> admisiones = List.of(new PgAdmTiposAdmisiones("Regular", 0), new PgAdmTiposAdmisiones("Especial", 40),
		new PgAdmTiposAdmisiones("10%", 10), new PgAdmTiposAdmisiones("20%",20), new PgAdmTiposAdmisiones("30%", 30));
		admisiones.forEach(a -> pstServicios.registrarPgAdmTiposAdmisiones(a));


		//===============DOCENTES==================
		var doc1 = new Docentes(pais, sexo1, ci3, estadosCiviles.get(1), grado1, 
		"Samuel","Fuentes","Chambi","2022","admin@gmail.com","Cobija","S/N");
		var doc2 = new Docentes(pais, sexo1, ci1, estadosCiviles.get(1), grado1, 
		"Eduardo Alberto","Zubieta","Copeticon","2023","user1@gmail.com","Cobija","S/N");
		var doc3 = new Docentes(pais, sexo2, ci2, estadosCiviles.get(0), grado2,
		"Juanito","Perez","Mamani", "2024", "user2@gmail.com", "Cochabamba","Dalence");
		List<Docentes> docentes = List.of(doc1, doc2, doc3);
		docentes.forEach(e -> docServicios.registrarDocentes(e));
		System.out.println("Docentes Registrados");

		//===========ENLACES Y LOGIN=============
		var sisAdmin = new SisAdministrador("POSGRADO","descripcion","observacion",1D);
		var sisAcceso = new SisNivelesAccesos("1");
		var menuFAdmin = new MnuTiposFunciones("JEFE DE PROGRAMA", "JEFEPRG");
		var menuFuncion = new MnuFunciones(prs, menuFAdmin, sisAdmin, sisAcceso, new Date(), 1D);
		menuServicios.registrarSisAdministrador(sisAdmin);
		menuServicios.registrarSisNivelesAccesos(sisAcceso);
		menuServicios.registrarMnuTiposFunciones(menuFAdmin);
		menuServicios.registrarMnuFunciones(menuFuncion);
	
		var menuTipoEnlace = new MnuTiposEnlaces("ACADEMICO", "img.jpg", 1);
		menuServicios.registrarTipoEnlace(menuTipoEnlace);

		var enlacePostulante = new MnuEnlaces(menuTipoEnlace, "Preinscripciones", "/aProgramas/listarPostulantes", "img.jpg", 1, 1);
		var enlaceAdmision = new MnuEnlaces(menuTipoEnlace, "Admisiones", "/aProgramas/listarAdmitidos", "img.jpg", 1, 1);
		var enlaceProgramas= new MnuEnlaces(menuTipoEnlace, "Programas", "/aProgramas/inicioProgramas", "img.jpg", 1, 1);
		var enlaceLanzamiento = new MnuEnlaces(menuTipoEnlace, "Lanzamientos", "/aLanzamientos/inicioProgramas", "img.jpg", 1, 3);
		// var enlaceDesarrollo = new MnuEnlaces(menuTipoEnlace, "Desarrollo Modulos", "");
		
		List<MnuEnlaces> enlaces = List.of(enlacePostulante, enlaceProgramas, enlaceLanzamiento, enlaceAdmision);	
		enlaces.forEach(enlace -> menuServicios.registrarEnlace(enlace));

		List<Menues> menuesAdmin = List.of(new Menues(enlacePostulante,menuFAdmin), new Menues(enlaceProgramas,menuFAdmin),
		new Menues(enlaceLanzamiento,menuFAdmin), new Menues(enlaceAdmision,menuFAdmin));
		menuesAdmin.forEach(menu -> menuServicios.registrarMenues(menu));

		var admin = usuServicios.buscarUsuariosPorIdUsuario(1L);
		admin.setPersonas(prs);
		usuServicios.modificarUsuarios(admin);

		//ENLACES DOCENTE
		var prsDoc = new Personas(pais2, sexo1, ci2, grado2, estadosCiviles.get(1),"Samuel", "Fuentes", "Chambi", "2022", 
		new Date(), "Direccion generica", "70707070", "O RH+");
		prsServicios.registrarPersonas(prsDoc);
		System.out.println("Usuario Docente registrado");

		var sisDoc = new SisAdministrador("POSGRADO","descripcion","observacion",1D);
		var sisAccesoDoc = new SisNivelesAccesos("2");
		var menuFDoc = new MnuTiposFunciones("DOCENTE", "DOC");
		var menuFuncionDoc = new MnuFunciones(prsDoc, menuFDoc, sisDoc, sisAccesoDoc, new Date(), 1D);
		menuServicios.registrarSisAdministrador(sisDoc);
		menuServicios.registrarSisNivelesAccesos(sisAccesoDoc);
		menuServicios.registrarMnuTiposFunciones(menuFDoc);
		menuServicios.registrarMnuFunciones(menuFuncionDoc);
		

		var menuTipoEnlaceDoc = new MnuTiposEnlaces("ACADEMICO", "img.jpg", 2);
		menuServicios.registrarTipoEnlace(menuTipoEnlaceDoc);
		var enlaceDocente = new MnuEnlaces(menuTipoEnlaceDoc, "Notas", "/aDocentes/inicioNotas", "img.jpg", 1, 1);
		menuServicios.registrarEnlace(enlaceDocente);
		List<Menues> menuesDoc = List.of(new Menues(enlaceDocente, menuFDoc));
		menuesDoc.forEach(e -> menuServicios.registrarMenues(e));

		var userDoc = usuServicios.buscarUsuariosPorIdUsuario(2L);
		userDoc.setPersonas(prsDoc);
		usuServicios.modificarUsuarios(userDoc);

		System.out.println("Procesos de Inicio Finalizados");
	}
}
