package uap.usic.siga.servicios;

import java.util.List;

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

public interface ProgramasService {
	
	//---------------------Servicio Programa----------------

		public List<Programas> listaProgramasJPLQ();
		public List<PgPrgModalidad> listaModalidadJPLQ();
		public List<PgPrgPlanes> listaPlanesJPLQ();
		public List<GradoAcademico> listaGradosAcademicosJPLQ();
		public List<PgMtrMaterias> listaMtrMateriasJPLQ();
    	public List<PgMtrTiposMaterias> listaMtrTipoMateriasJPLQ();
		public List<PgPrgModulos> listaPrgModulosJPLQ();
		public List<PgPrgModulos> listaModulosPorIdPlan(Long idPlan);
		public List<PgMtrPlanes> listaMtrPlanesJPLQ();
		public List<PgPrgModulos> ListaPorPlanes(Long idPgPrgPlanes);
		public List<PgPrgPlanes> ListaPorProgramas(Long idPrograma);
		public List<PgPrgPlnVersiones> ListaVersionesPorIdPrograma(Long idPrograma);
		public List<PgPrgPlnGrupo> ListaGruposPorIdPgPrgPlnVersiones(Long idPgPrgPlnVersiones);
		public List<Programas> listaMtrGeneralJPLQ();
		public List<Programas> listaProgramasLanzados();
		public List<PgPrgArancel> listaAranceles();
		
	    public Programas registrarProgramaSET(Programas programa);
		public void registrarPgPrgModalidad(PgPrgModalidad modalidad);
	    public PgPrgPlanes registPrgPlanesSet(PgPrgPlanes plan);
		public GradoAcademico registrarGradoAcademicoSet(GradoAcademico grado);
		public PgMtrMaterias registrarMtrMateriasSet(PgMtrMaterias materia);
		public PgPrgModulos registrarPrgModulosSet(PgPrgModulos modulo);
		public PgMtrPlanes registrarMtrPlanesSet(PgMtrPlanes planes);
		public PgPrgArancel registrararancelSET(PgPrgArancel arancel);

		public void modificarPersonas(Programas programa);

		public Programas buscarProgramaPorIdPrograma(Long id_programa);
		public void modificarPlanes(PgPrgPlanes pgPrgPlanes);
	    public PgPrgPlanes buscarPlanesPorIdPlanes(Long idPgPrgPlanes);
		public void modificarMateriasSET(PgMtrMaterias materia);
    	public PgMtrMaterias buscarMateriasPorIdMateria(Long idMateria);
		public PgPrgModulos buscarModulosPorPlanGet(Long plan);
		public PgPrgModulos buscarNodulosPorIdModulo(Long idModulo);
		public void modificarModulosSET(PgPrgModulos pgPrgModulos);
		public PgPrgArancel buscarArancelPorIdPrograma(Long idPrograma);
		public PgPrgPlnVersiones buscarVersionPorIdPrograma(Long idPrograma);
		public PgPrgPlnGrupo buscarGrupoPorIdPgPrgPlnVersiones(Long idPgPrgPlnVersiones);
		public PgPrgPlnGrupo buscarGrupoPorIdGrupo(Long idGrupo);

		//======================== Lanzamiento ========================
		public List<PgPrgPlnVersiones> listaPgPrgPlnVersionesJPLQ();
    	public List<PgPrgPlnGrupo> listaPgPrgPlnGrupoJPLQ();

    	public PgPrgPlnVersiones registrarVersionPlnSet(PgPrgPlnVersiones version);
    	public PgPrgPlnGrupo registrarGrupoPlnSet(PgPrgPlnGrupo grupo);
		public PgPrgPlnGrupo registrarGrupo(PgPrgPlnGrupo grupo);

		public PgPrgPlnVersiones buscarVersionPorIdGET(Long idPgPrgPlnVersiones);

		public PgArchivosAdjuntos buscarPgArchivosAdjuntosPorIdPgPrgPlnVersionesGET(Long idPgPrgPlnVersiones);
    	public PgArchivosAdjuntos registrarPgArchivosAdjuntosSET(PgArchivosAdjuntos pgArchivosAdjuntos);
	    
}
