package uap.usic.siga.modelos.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidadesPg.Estudiantes;
import uap.usic.siga.entidadesPg.Matriculas;
import uap.usic.siga.entidadesPg.PgTipoEstudiante;
import uap.usic.siga.entidadesPg.TiposDescuentos;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.EstudiantesDao;

@Repository("estudiantesDao")
public class EstudiantesDaoImpl implements EstudiantesDao {

	@PersistenceContext
    private EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
	
	@Override
	public List<Estudiantes> listarEstudiantesPorFiltroGET(String palabraClave) {
    	String sql = " SELECT est "
                + " FROM Estudiantes est LEFT JOIN est.personas p "
                + " WHERE p.ci =:palabraClave "
				+ " AND est.estado = 'A' "
    			+ " AND p.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("palabraClave", palabraClave);
        return q.getResultList();
	}

	@Override
	public List<Estudiantes> listarEstudiantesGET() {
		  String sql = " SELECT est "
	                + " FROM Estudiantes est  "
	                + " WHERE est.estado = 'A'  ";
	        Query q = em.createQuery(sql);
	        return q.getResultList();
	}

	@Override
	public List<PgTipoEstudiante> listarTipoEstudianteGET() {
		String sql = " SELECT pte "
                + " FROM PgTipoEstudiante pte  "
                + " WHERE pte.estado = 'A'  ";
        Query q = em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public void registrarEstudianteSET(Estudiantes estudiantes) {
		 em.persist(estudiantes);

	}

	@Override
	public void modificarEstudianteSET(Estudiantes estudiantes) {
		em.merge(estudiantes);

	}

	@Override
	public void eliminarEstudianteSET(Estudiantes estudiantes) {
		em.merge(estudiantes);

	}

	@Override
	public Estudiantes buscarEstudiantePorIdEstudianteGET(Long idEstudiante) {
		String sql = " SELECT est "
                + " FROM Estudiantes est "
                + " WHERE est.idEstudiante = :idEstudiante "
                + " AND est.estado != 'X'";
        Query q = em.createQuery(sql);
        q.setParameter("idEstudiante", idEstudiante);
        try {
            return (Estudiantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public List<Matriculas> listarMatriculadoGET() {
		String sql = " SELECT mt "
                + " FROM Matriculas mt  "
                + " WHERE mt.estado = 'A'  ";
        Query q = em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public List<TiposDescuentos> listarTiposDescuentosGET() {
		String sql = " SELECT td "
                + " FROM TiposDescuentos td  "
                + " WHERE td.estado = 'A'  ";
        Query q = em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public void registrarMatriculaSET(Matriculas matriculas) {
		em.persist(matriculas);
		
	}

	@Override
	public void modificarMatriculaSET(Matriculas matriculas) {
		em.merge(matriculas);
		
	}

	@Override
	public void eliminarMatriculaSET(Matriculas matriculas) {
		em.merge(matriculas);
		
	}

	@Override
	public Matriculas buscarMatriculasPorIdMatriculasGET(Long idMatricula) {
		String sql = " SELECT mt "
                + " FROM Matriculas mt "
                + " WHERE mt.idMatricula = :idMatricula "
                + " AND mt.estado != 'X'";
        Query q = em.createQuery(sql);
        q.setParameter("idMatricula", idMatricula);
        try {
            return (Matriculas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

}
