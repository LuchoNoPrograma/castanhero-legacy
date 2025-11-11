package uap.usic.siga.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.entidadesPg.GradoAcademico;
import uap.usic.siga.entidadesPg.PgArchivosAdjuntos;
import uap.usic.siga.entidadesPg.PgMtrMaterias;
import uap.usic.siga.entidadesPg.PgMtrPlanes;
import uap.usic.siga.entidadesPg.PgMtrTiposMaterias;
import uap.usic.siga.entidadesPg.PgPrgArancel;
import uap.usic.siga.entidadesPg.PgPrgModalidad;
import uap.usic.siga.entidadesPg.PgPrgModulos;
import uap.usic.siga.entidadesPg.PgPrgPlanes;
import uap.usic.siga.entidadesPg.PgPrgPlnGrupo;
import uap.usic.siga.entidadesPg.PgPrgPlnVersiones;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.modelos.ProgramasDao;
import uap.usic.siga.servicios.ProgramasService;

@Service("programaService")
@Transactional
public class ProgramasServiceImpl implements ProgramasService {
	
	@Autowired
	private ProgramasDao dao;

	@Override
	public List<Programas> listaProgramasJPLQ() {
		return dao.listaProgramasJPLQ();
	}

	@Override
	public List<PgPrgModalidad> listaModalidadJPLQ() {
		return dao.listaModalidadJPLQ();
	}

	@Override
	public List<PgPrgPlanes> listaPlanesJPLQ() {
		return dao.listaPlanesJPLQ();
	}

	@Override
	public List<GradoAcademico> listaGradosAcademicosJPLQ() {
		return dao.listaGradosAcademicosJPLQ();
	}

	@Override
	public List<PgMtrMaterias> listaMtrMateriasJPLQ() {
		return dao.listaMtrMateriasJPLQ();
	}

	@Override
	public List<PgMtrTiposMaterias> listaMtrTipoMateriasJPLQ() {
		return dao.listaMtrTipoMateriasJPLQ();
	}

	@Override
	public List<PgPrgModulos> listaPrgModulosJPLQ() {
		return dao.listaPrgModulosJPLQ();
	}

	@Override
	public List<PgPrgModulos> listaModulosPorIdPlan(Long idPlan){
		return dao.listaModulosPorIdPlan(idPlan);
	}

	@Override
	public Programas registrarProgramaSET(Programas programa) {
		return dao.registrarProgramaSET(programa);
	}

	@Override
	public PgPrgPlanes registPrgPlanesSet(PgPrgPlanes plan) {
		return dao.registPrgPlanesSet(plan);
	}

	@Override
	public GradoAcademico registrarGradoAcademicoSet(GradoAcademico grado) {
		return dao.registrarGradoAcademicoSet(grado);
	}

	@Override
	public PgMtrMaterias registrarMtrMateriasSet(PgMtrMaterias materia) {
		return dao.registrarMtrMateriasSet(materia);
	}

	@Override
	public PgPrgModulos registrarPrgModulosSet(PgPrgModulos modulo) {
		return dao.registrarPrgModulosSet(modulo);
	}

	@Override
	public void modificarPersonas(Programas programas) {
		dao.modificarProgramaSET(programas);	
	}

	@Override
	public Programas buscarProgramaPorIdPrograma(Long id_programa) {
		return dao.buscarProgramaPorIdProgramaGET(id_programa);
	}

	@Override
	public void modificarPlanes(PgPrgPlanes pgPrgPlanes) {
		dao.modificarPlanesSET(pgPrgPlanes);
		
	}

	@Override
	public PgPrgPlanes buscarPlanesPorIdPlanes(Long idPgPrgPlanes) {
		
		return dao.buscarPlanesPorIdPlanesGET(idPgPrgPlanes);
	}

	@Override
	public void modificarMateriasSET(PgMtrMaterias materia) {
		dao.modificarMateriasSET(materia);
		
	}

	@Override
	public PgMtrMaterias buscarMateriasPorIdMateria(Long idMateria) {
		return dao.buscarMateriasPorIdMateria(idMateria);
	}
	//================ Mtr Planes ======================

	@Override
	public List<PgMtrPlanes> listaMtrPlanesJPLQ() {
		return dao.listaMtrPlanesJPLQ();
	}

	@Override
	public PgMtrPlanes registrarMtrPlanesSet(PgMtrPlanes planes) {
		return dao.registrarMtrPlanesSet(planes);
	}

	@Override
	public PgPrgModulos buscarModulosPorPlanGet(Long plan) {
		return dao.buscarModulosPorPlanGet(plan);
	}

	@Override
	public List<PgPrgModulos> ListaPorPlanes(Long idPgPrgPlanes) {
		return dao.ListaPorPlanes(idPgPrgPlanes);
	}

	//===================== Lanzamiento ==========================

	@Override
	public List<PgPrgPlnVersiones> listaPgPrgPlnVersionesJPLQ() {
		return dao.listaPgPrgPlnVersionesJPLQ();
	}

	@Override
	public List<PgPrgPlnGrupo> listaPgPrgPlnGrupoJPLQ() {
		return dao.listaPgPrgPlnGrupoJPLQ();
	}

	@Override
	public PgPrgPlnVersiones registrarVersionPlnSet(PgPrgPlnVersiones version) {
		return dao.registrarVersionPlnSet(version);
	}

	@Override
	public PgPrgPlnGrupo registrarGrupoPlnSet(PgPrgPlnGrupo grupo) {
		return dao.registrarGrupoPlnSet(grupo);
	}

	@Override
	public PgPrgPlnVersiones buscarVersionPorIdGET(Long idPgPrgPlnVersiones) {
		return dao.buscarVersionPorIdGET(idPgPrgPlnVersiones);
	}

	@Override
	public List<Programas> listaMtrGeneralJPLQ() {
		return dao.listaMtrGeneralJPLQ();
	}

	@Override
	public PgPrgModulos buscarNodulosPorIdModulo(Long idModulo) {
		return dao.buscarNodulosPorIdModulo(idModulo);
	}

	@Override
	public void modificarModulosSET(PgPrgModulos pgPrgModulos) {
		dao.modificarModulosSET(pgPrgModulos);
	}

	@Override
	public List<PgPrgPlanes> ListaPorProgramas(Long idPrograma) {
		return dao.ListaPorProgramas(idPrograma);
	}

	@Override
	public List<PgPrgPlnVersiones> ListaVersionesPorIdPrograma(Long idPrograma) {
		return dao.ListaVersionesPorIdPrograma(idPrograma);
	}

	@Override
	public List<PgPrgPlnGrupo> ListaGruposPorIdPgPrgPlnVersiones(Long idPgPrgPlnVersiones) {
		return dao.ListaGruposPorIdPgPrgPlnVersiones(idPgPrgPlnVersiones);
	}

	@Override
	public List<Programas> listaProgramasLanzados() {
		return dao.listaProgramasLanzados();
	}

	@Override
	public PgArchivosAdjuntos buscarPgArchivosAdjuntosPorIdPgPrgPlnVersionesGET(Long idPgPrgPlnVersiones) {
		return dao.buscarPgArchivosAdjuntosPorIdPgPrgPlnVersionesGET(idPgPrgPlnVersiones);
	}

	@Override
	public PgArchivosAdjuntos registrarPgArchivosAdjuntosSET(PgArchivosAdjuntos pgArchivosAdjuntos) {
		return dao.registrarPgArchivosAdjuntosSET(pgArchivosAdjuntos);
	}

	@Override
	public PgPrgArancel registrararancelSET(PgPrgArancel arancel) {
		return dao.registrararancelSET(arancel);
	}

	@Override
	public List<PgPrgArancel> listaAranceles() {
		return dao.listaAranceles();
	}

	@Override
	public PgPrgArancel buscarArancelPorIdPrograma(Long idPrograma) {
		return dao.buscarArancelPorIdPrograma(idPrograma);
	}

	@Override
	public PgPrgPlnVersiones buscarVersionPorIdPrograma(Long idPrograma) {
		return dao.buscarVersionPorIdPrograma(idPrograma);
	}

	@Override
	public PgPrgPlnGrupo buscarGrupoPorIdPgPrgPlnVersiones(Long idPgPrgPlnVersiones) {
		return dao.buscarGrupoPorIdPgPrgPlnVersiones(idPgPrgPlnVersiones);
	}

	@Override
	public PgPrgPlnGrupo buscarGrupoPorIdGrupo(Long idGrupo){
		return dao.buscarGrupoPorIdGrupo(idGrupo);
	}

	@Override
	public PgPrgPlnGrupo registrarGrupo(PgPrgPlnGrupo grupo) {
		return dao.registrarGrupo(grupo);
	}

	@Override
	public void registrarPgPrgModalidad(PgPrgModalidad modalidad) {
		dao.registrarModalidadSet(modalidad);
	}
}
