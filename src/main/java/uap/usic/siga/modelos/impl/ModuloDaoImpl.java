package uap.usic.siga.modelos.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;

import uap.usic.siga.entidadesPg.Docentes;
import uap.usic.siga.entidadesPg.EjecucionModuloEstudiante;
import uap.usic.siga.entidadesPg.EjecucionesModulos;
import uap.usic.siga.entidadesPg.PgPrgModulos;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.modelos.ModuloDao;

@Repository("moduloDao")
public class ModuloDaoImpl implements ModuloDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public EjecucionesModulos buscarEjecucionesModulosPorId(Long idEjecucionModulo) {
        String sql = "SELECT e FROM EjecucionesModulos e "+
                    "LEFT JOIN e.estudiantes est "+
                    "LEFT JOIN e.modulo mod "+
                    "LEFT JOIN e.docente doc "+ 
                    "WHERE e.idEjecucionModulo = :id";
        Query q = em.createQuery(sql);
        q.setParameter("id", idEjecucionModulo);

        try{
            return (EjecucionesModulos) q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public EjecucionModuloEstudiante buscarEjecucionModuloEstudiantePorId(Long idEjecucionModuloEstudiante){
        String sql = "SELECT eme FROM EjecucionModuloEstudiante eme "+
                    "LEFT JOIN eme.matriculas m "+
                    "WHERE eme.idEjecucionModuloEstudiante = :id";
        Query q = em.createQuery(sql);
        q.setParameter("id", idEjecucionModuloEstudiante);

        try{
            return (EjecucionModuloEstudiante) q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Set<PgPrgModulos> listarModulosPorDocente(Docentes docente) {
        String sql = "SELECT m FROM PgPrgModulos m "+
                    "LEFT JOIN FETCH m.ejecucionModulo em "+
                    "WHERE em.docente = :docente AND em.estado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("docente", docente);
        Set<PgPrgModulos> modulosFiltrados = new HashSet<PgPrgModulos>(q.getResultList());
        return modulosFiltrados;
    }

    @Override
    public List<EjecucionesModulos> listarEjecucionesModulosPorIdGrupo(Long idGrupo){
        String sql = "SELECT em FROM EjecucionesModulos em "+
                    "LEFT JOIN em.modulo mod "+
                    "LEFT JOIN em.docente doc "+
                    "WHERE em.grupo.idPgPrgPlnGrupos = :id AND em.estado != 'X'";
        return em.createQuery(sql, EjecucionesModulos.class)
            .setParameter("id", idGrupo).getResultList();
    }

    @Override
    public List<EjecucionModuloEstudiante> listarEjecucionesModuloEstudiantePorIdModulo(Long idModulo) {
        String sql = "SELECT eme FROM EjecucionModuloEstudiante eme "+
                    "LEFT JOIN eme.matriculas m "+
                    "WHERE eme.estado != 'X' AND eme.ejecucionModulo.modulo.idModulo = :idModulo";
        Query q = em.createQuery(sql);
        q.setParameter("idModulo", idModulo);
        return q.getResultList();
    }

    @Override
    public List<EjecucionModuloEstudiante> listarEjecucionModuloEstudiantePorIdEjecucion(Long idEjecucion){
        String sql = "SELECT eme FROM EjecucionModuloEstudiante eme "+
                    "LEFT JOIN eme.matriculas m "+
                    "WHERE eme.estado != 'X' AND eme.ejecucionModulo.idEjecucionModulo =: idEjecucion";
        Query q = em.createQuery(sql);
        q.setParameter("idEjecucion", idEjecucion);
        return q.getResultList();
    }

    @Override
    public void registrarEjecucionesModulos(EjecucionesModulos ejecucionesModulos) {
        em.persist(ejecucionesModulos);
    }

    @Override
    public void modificarEjecucionesModulos(EjecucionesModulos ejecucionesModulos) {
        em.merge(ejecucionesModulos);
    }

    @Override
    public void eliminarEjecucionesModulos(EjecucionesModulos ejecucionesModulos) {
        em.merge(ejecucionesModulos);
    }

    @Override
    public void registrarEjecucionModuloEstudiante(EjecucionModuloEstudiante ejecucionModuloEstudiante) {
        em.persist(ejecucionModuloEstudiante);
    }

    @Override
    public void modificarEjecucionModuloEstudiante(EjecucionModuloEstudiante ejecucionModuloEstudiante){
        em.merge(ejecucionModuloEstudiante);
    }
}
