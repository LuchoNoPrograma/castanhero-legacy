package uap.usic.siga.modelos.impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.ProgramasDao;

@Repository("programaDao")
public class ProgramasDaoImpl implements ProgramasDao {

	@PersistenceContext
    private javax.persistence.EntityManager em;
	
	@Transactional(rollbackFor = {ServicioException.class})
	@Override
	public List<Programas> listaProgramasJPLQ() {
		String sql = "SELECT pro "
                + "FROM Programas pro"
                + " WHERE pro.estado = 'A' ";
               
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public List<PgPrgModalidad> listaModalidadJPLQ() {
		String sql = "SELECT md "
                + "FROM PgPrgModalidad md";
                //+ " WHERE unv.idEstado = 'A' "
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public List<PgPrgPlanes> listaPlanesJPLQ() {
		String sql = "SELECT pl "
                + "FROM PgPrgPlanes pl"
                + " WHERE pl.estado = 'A' ";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}
	
	public List<GradoAcademico> listaGradosAcademicosJPLQ() {
		String sql = "SELECT g "
                + "FROM GradoAcademico g";
                //+ " WHERE unv.idEstado = 'A' "
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public List<PgMtrMaterias> listaMtrMateriasJPLQ() {
		String sql = "SELECT m "
                + "FROM PgMtrMaterias m"
                + " WHERE m.estado = 'A' ";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public List<PgMtrTiposMaterias> listaMtrTipoMateriasJPLQ() {
		String sql = "SELECT tm "
                + "FROM PgMtrTiposMaterias tm";
                //+ " WHERE tm.estado = 'A' ";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public List<PgPrgModulos> listaPrgModulosJPLQ() {
		String sql = "SELECT m "
                + "FROM PgPrgModulos m "
                + "WHERE m.estado = 'A' ";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public Programas registrarProgramaSET(Programas programa) {
		em.persist(programa);
		return programa;
	}

	@Override
	public PgPrgPlanes registPrgPlanesSet(PgPrgPlanes plan) {
		em.persist(plan);
		return plan;
	}

	@Override
	public GradoAcademico registrarGradoAcademicoSet(GradoAcademico grado) {
		em.persist(grado);
		return grado;
	}

	@Override
	public PgMtrMaterias registrarMtrMateriasSet(PgMtrMaterias materia) {
		em.persist(materia);
		return materia;
	}

	@Override
	public PgPrgModulos registrarPrgModulosSet(PgPrgModulos modulo) {
		em.persist(modulo);
		return modulo;
	}

	@Override
	public void registrarModalidadSet(PgPrgModalidad modalidad) {
		em.persist(modalidad);
	}

	@Override
	public void registPrgGradoSet(PrsGradosAcademicos grado) {
		em.persist(grado);
	}

	@Override
	public void registrarPrgPlanes(PgPrgPlanes prgPlanes) {
		em.persist(prgPlanes);
	}

	@Override
	public void modificarProgramaSET(Programas programa) {
		em.merge(programa);
		
	}

	@Override
	public Programas buscarProgramaPorIdProgramaGET(Long idPrograma) {
		String sql = " SELECT p "
                + " FROM Programas p "
                + "WHERE p.idPrograma = :idPrograma";
                //+ " AND p.estado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("idPrograma", idPrograma);
        try {
            return (Programas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public PgPrgModulos buscarNodulosPorIdModulo(Long idModulo) {
		String sql = "SELECT p "
                + "FROM PgPrgModulos p "
                + "WHERE p.idModulo = :idModulo";
                //+ " AND p.estado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("idModulo", idModulo);
        try {
            return (PgPrgModulos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}
	
	@Override
	public void modificarPlanesSET(PgPrgPlanes pgPrgPlanes) {
		em.merge(pgPrgPlanes);
		
	}

	@Override
	public void registrarMtrTiposMaterias(PgMtrTiposMaterias tiposMaterias) {
		em.persist(tiposMaterias);
		
	}

	@Override
	public PgPrgPlanes buscarPlanesPorIdPlanesGET(Long idPgPrgPlanes) {
		String sql = " SELECT p "
                + " FROM PgPrgPlanes p "
                + "WHERE p.idPgPrgPlanes = :idPgPrgPlanes";
               // + " AND p.estado = 'A'"
        Query q = em.createQuery(sql);
        q.setParameter("idPgPrgPlanes", idPgPrgPlanes);
        try {
            return (PgPrgPlanes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public List<PgPrgModulos> listaModulosPorIdPlan(Long idPlan){
		String sql = " SELECT p "
                + " FROM PgPrgModulos p "
                + "WHERE p.plan.idPgPrgPlanes = :id";
               // + " AND p.estado = 'A'"
        return em.createQuery(sql, PgPrgModulos.class)
			.setParameter("id", idPlan).getResultList();
	}

	@Override
	public void modificarMateriasSET(PgMtrMaterias materia) {
		em.persist(materia);
	}

	@Override
	public PgMtrMaterias buscarMateriasPorIdMateria(Long idMateria) {
		String sql = "SELECT ma "
					+"FROM PgMtrMaterias ma "
					+"WHERE ma.idMateria = :idMateria";
		Query q = em.createQuery(sql);
		q.setParameter("idMateria", idMateria);
		try{
			return (PgMtrMaterias) q.getSingleResult();
		}catch (Exception e){
			return null;
		}
	}

	//====================== Mod Materias ===========================
	@Override
	public List<PgMtrPlanes> listaMtrPlanesJPLQ() {
		String sql = "SELECT m "
                + "FROM PgMtrPlanes m"
                + " WHERE m.estado = 'A' ";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public PgMtrPlanes registrarMtrPlanesSet(PgMtrPlanes planes) {
		em.persist(planes);
		return planes;
	}

	@Override
	public void registrarMtrMaterias(PgMtrMaterias materias) {
		em.persist(materias);
	}

	@Override
	public PgPrgModulos buscarModulosPorPlanGet(Long plan) {
		String sql = " SELECT p "
                + " FROM PgPrgModulos p "
                + "WHERE p.plan = :plan";
                //+ " AND p.estado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("plan", plan);
        try {
            return (PgPrgModulos) q.getResultList();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public List<PgPrgModulos> ListaPorPlanes(Long idPgPrgPlanes) {
		String sql = "SELECT m "
                + "FROM PgPrgModulos m "
				+"left join m.plan u "
				+"where u.idPgPrgPlanes = :idPgPrgPlanes";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
		q.setParameter("idPgPrgPlanes", idPgPrgPlanes);
        return q.getResultList();
	}

	@Override
	public List<PgPrgPlanes> ListaPorProgramas(Long idPrograma) {
		String sql = "SELECT m "
                + "FROM PgPrgPlanes m "
				+"left join m.programa u "
				+"where u.idPrograma = :idPrograma "
				+"and m.estado = 'A'";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
		q.setParameter("idPrograma", idPrograma);
        return q.getResultList();
	}

	@Override
	public List<PgPrgPlnVersiones> ListaVersionesPorIdPrograma(Long idPrograma) {
		String sql = "SELECT m "
                + "FROM PgPrgPlnVersiones m "
				+"left join m.programa u "
				+"where u.idPrograma = :idPrograma";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
		q.setParameter("idPrograma", idPrograma);
        return q.getResultList();
	}

	//====================== Lanzamiento =============================
	@Override
	public List<PgPrgPlnVersiones> listaPgPrgPlnVersionesJPLQ() {
		String sql = "SELECT m "
                + "FROM PgPrgPlnVersiones m"
                + " WHERE m.estado = 'A' ";
                //+ " ORDER BY pro.universidad";
        return em.createQuery(sql, PgPrgPlnVersiones.class).getResultList();
	}

	@Override
	public List<PgPrgPlnGrupo> listaPgPrgPlnGrupoJPLQ() {
		String sql = "SELECT m "
                + "FROM PgPrgPlnGrupo m";
                //+ " WHERE m.estado = 'A' ";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public PgPrgPlnVersiones registrarVersionPlnSet(PgPrgPlnVersiones version) {
		em.persist(version);
		return version;
	}

	@Override
	public PgPrgPlnGrupo registrarGrupoPlnSet(PgPrgPlnGrupo grupo) {
		em.persist(grupo);
		return grupo;
	}

	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class})
	@Override	
	public PgPrgPlnGrupo buscarGrupoPorIdGrupo(Long idGrupo){
		String sql = "SELECT gru FROM PgPrgPlnGrupo gru "+
					"LEFT JOIN gru.version ver "+
					"WHERE gru.estado != 'X' AND gru.idPgPrgPlnGrupos = :id";
		Query q = em.createQuery(sql, PgPrgPlnGrupo.class);
		q.setParameter("id", idGrupo);
		try{
			return (PgPrgPlnGrupo) q.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public PgPrgPlnVersiones buscarVersionPorIdGET(Long idPgPrgPlnVersiones) {
		String sql = " SELECT p "
                + " FROM PgPrgPlnVersiones p "
                + "WHERE p.idPgPrgPlnVersiones = :idPgPrgPlnVersiones";
               // + " AND p.estado = 'A'"
        Query q = em.createQuery(sql);
        q.setParameter("idPgPrgPlnVersiones", idPgPrgPlnVersiones);
        try {
            return (PgPrgPlnVersiones) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public List<Programas> listaMtrGeneralJPLQ() {
		String sql = "SELECT pro, pla "
                + "FROM Programas pro, PgPrgPlanes pla"
                + " WHERE pro.idPrograma = :plan.programa";
               
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public void modificarModulosSET(PgPrgModulos pgPrgModulos) {
		em.merge(pgPrgModulos);
	}

	@Override
	public List<PgPrgPlnGrupo> ListaGruposPorIdPgPrgPlnVersiones(Long idPgPrgPlnVersiones) {
		String sql = "SELECT m "
                + "FROM PgPrgPlnGrupo m "
				+"left join m.version u "
				+"where u.idPgPrgPlnVersiones = :idPgPrgPlnVersiones";
                //+ " ORDER BY pro.universidad";
        Query q = (Query) em.createQuery(sql);
		q.setParameter("idPgPrgPlnVersiones", idPgPrgPlnVersiones);
        return q.getResultList();
	}

	@Override
	public List<Programas> listaProgramasLanzados() {
		String sql = "SELECT pro "
                + "FROM Programas pro"
                + " WHERE pro.estado = 'L' ";
               
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public PgArchivosAdjuntos registrarPgArchivosAdjuntosSET(PgArchivosAdjuntos pgArchivosAdjuntos) {
		em.persist(pgArchivosAdjuntos);
	    return pgArchivosAdjuntos;
	}

	@Override
	public PgArchivosAdjuntos buscarPgArchivosAdjuntosPorIdPgPrgPlnVersionesGET(Long idPgPrgPlnVersiones) {
		String sql = "SELECT gaa  "
	                + " FROM PgPrgPlnVersiones res LEFT JOIN  res.pgArchivosAdjuntos gaa"
	                + " WHERE res.idPgPrgPlnVersiones =:idPgPrgPlnVersiones "
	                + " AND gaa.idEstado = 'A' "
	                + " AND res.estado =  'A' ";
	        Query q = em.createQuery(sql);
	        q.setParameter("idPgPrgPlnVersiones", idPgPrgPlnVersiones);
	     try {
	            return (PgArchivosAdjuntos) q.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	}

	@Override
	public PgPrgArancel registrararancelSET(PgPrgArancel arancel) {
		em.persist(arancel);
		return arancel;
	}

	@Override
	public List<PgPrgArancel> listaAranceles() {
		String sql = "SELECT ara "
                + "FROM PgPrgArancel ara";
                //+ " WHERE pro.estado = 'A' ";
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public PgPrgArancel buscarArancelPorIdPrograma(Long idPrograma) {
		String sql = "SELECT ara "
                +"FROM PgPrgArancel ara "
				+"LEFT JOIN ara.programa p "
                +"WHERE p.idPrograma = :idPrograma";
                //+ " AND p.estado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("idPrograma", idPrograma);
		try {
			return (PgPrgArancel) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PgPrgPlnVersiones buscarVersionPorIdPrograma(Long idPrograma) {
		String sql = "SELECT ver "
                +"FROM PgPrgPlnVersiones ver "
				+"LEFT JOIN ver.programa p "
                +"WHERE p.idPrograma = :idPrograma";
                //+ " AND p.estado = 'A'";
        Query q = em.createQuery(sql);
		q.setParameter("idPrograma", idPrograma);
		return (PgPrgPlnVersiones) q.getSingleResult();
	}

	@Override
	public PgPrgPlnGrupo buscarGrupoPorIdPgPrgPlnVersiones(Long idPgPrgPlnVersiones) {
		String sql = "SELECT gru "
                +"FROM PgPrgPlnGrupo gru "
				+"LEFT JOIN gru.version v "
                +"WHERE v.idPgPrgPlnVersiones = :idPgPrgPlnVersiones";
                //+ " AND p.estado = 'A'";
        Query q = em.createQuery(sql);
		q.setParameter("idPgPrgPlnVersiones", idPgPrgPlnVersiones);
		return (PgPrgPlnGrupo) q.getSingleResult();
	}


	@Override
	public PgPrgPlnGrupo registrarGrupo(PgPrgPlnGrupo grupo) {
		em.persist(grupo);
		return grupo;
	}

}
