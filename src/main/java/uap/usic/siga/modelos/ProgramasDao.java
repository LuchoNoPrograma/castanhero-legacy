package uap.usic.siga.modelos;

import java.util.List;

import uap.usic.siga.entidades.PrsGradosAcademicos;
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

public interface ProgramasDao{
	
    //---------------------Modulo Programa----------------

	public List<Programas> listaProgramasJPLQ();
	public List<PgPrgModalidad> listaModalidadJPLQ();
	public List<PgPrgPlanes> listaPlanesJPLQ();
	public List<GradoAcademico> listaGradosAcademicosJPLQ();
    public List<PgMtrMaterias> listaMtrMateriasJPLQ();
    public List<PgMtrTiposMaterias> listaMtrTipoMateriasJPLQ();
    public List<PgPrgModulos> listaPrgModulosJPLQ();
    public List<PgMtrPlanes> listaMtrPlanesJPLQ();
    public List<Programas> listaMtrGeneralJPLQ();
    public List<PgPrgModulos> ListaPorPlanes(Long idPgPrgPlanes);
    public List<PgPrgPlanes> ListaPorProgramas(Long idPrograma);
    public List<PgPrgPlnVersiones> ListaVersionesPorIdPrograma(Long idPrograma);
    public List<PgPrgPlnGrupo> ListaGruposPorIdPgPrgPlnVersiones(Long idPgPrgPlnVersiones);
    public List<Programas> listaProgramasLanzados();
    public List<PgPrgArancel> listaAranceles();
    
	
	
    public Programas registrarProgramaSET(Programas programa);
    public PgPrgPlanes registPrgPlanesSet(PgPrgPlanes plan);
    public GradoAcademico registrarGradoAcademicoSet(GradoAcademico grado);
    public PgMtrMaterias registrarMtrMateriasSet(PgMtrMaterias materia);
    public PgPrgModulos registrarPrgModulosSet(PgPrgModulos modulo);
    public PgMtrPlanes registrarMtrPlanesSet(PgMtrPlanes planes);
    public PgPrgArancel registrararancelSET(PgPrgArancel arancel);
    

    
    public void registrarModalidadSet(PgPrgModalidad modalidad);
    public void registPrgGradoSet(PrsGradosAcademicos grado);
    public void registrarMtrTiposMaterias(PgMtrTiposMaterias tiposMaterias);
    public void registrarPrgPlanes(PgPrgPlanes prgPlanes);
    public void registrarMtrMaterias(PgMtrMaterias materias);


    public void modificarProgramaSET(Programas programa);
    public Programas buscarProgramaPorIdProgramaGET(Long id_programa);
    public PgPrgPlnVersiones buscarVersionPorIdGET(Long idPgPrgPlnVersiones);
    public PgPrgModulos buscarModulosPorPlanGet(Long plan);
    public void modificarPlanesSET(PgPrgPlanes pgPrgPlanes);
    public PgPrgPlanes buscarPlanesPorIdPlanesGET(Long idPgPrgPlanes);
    public void modificarMateriasSET(PgMtrMaterias materia);
    public PgMtrMaterias buscarMateriasPorIdMateria(Long idMateria);
    public PgPrgModulos buscarNodulosPorIdModulo(Long idModulo);
    public void modificarModulosSET(PgPrgModulos pgPrgModulos);
    public List<PgPrgModulos> listaModulosPorIdPlan(Long idPlan);
    public PgPrgArancel buscarArancelPorIdPrograma(Long idPrograma);
    public PgPrgPlnVersiones buscarVersionPorIdPrograma(Long idPrograma);
    public PgPrgPlnGrupo buscarGrupoPorIdPgPrgPlnVersiones(Long idPgPrgPlnVersiones);
    public PgPrgPlnGrupo buscarGrupoPorIdGrupo(Long idGrupo);

    //===================== Lanzamiento de Programa ========================
    public List<PgPrgPlnVersiones> listaPgPrgPlnVersionesJPLQ();
    public List<PgPrgPlnGrupo> listaPgPrgPlnGrupoJPLQ();

    public PgPrgPlnVersiones registrarVersionPlnSet(PgPrgPlnVersiones version);
    public PgPrgPlnGrupo registrarGrupoPlnSet(PgPrgPlnGrupo grupo);
    public PgPrgPlnGrupo registrarGrupo(PgPrgPlnGrupo grupo);

    public PgArchivosAdjuntos registrarPgArchivosAdjuntosSET(PgArchivosAdjuntos pgArchivosAdjuntos);
    public PgArchivosAdjuntos buscarPgArchivosAdjuntosPorIdPgPrgPlnVersionesGET(Long idPgPrgPlnVersiones);

}
