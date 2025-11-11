package uap.usic.siga.modelos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import uap.usic.siga.entidadesPg.Docentes;
import uap.usic.siga.modelos.DocentesDao;

@Repository("docentesDao")
public class DocentesDaoImpl implements DocentesDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Docentes buscarDocentePorId(Long idDocente) {
        String sql = " SELECT d "
                + " FROM Docentes d "
                + " WHERE d.idDocente = :idDocente "
                + " AND d.estado = 'A'";

        Query q = em.createQuery(sql);
        q.setParameter("idDocente", idDocente);
        try {
            return (Docentes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Docentes buscarDocentesPorCi(String ciDocente) {
        String sql = " SELECT d "
                + " FROM Docentes d "
                + " WHERE d.ci = :ci "
                + " AND d.estado = 'A'";

        Query q = em.createQuery(sql);
        q.setParameter("ci", ciDocente);
        try {
            return (Docentes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void registrarDocentes(Docentes docentes) {
        em.persist(docentes);
    }

    @Override
    public void modificarDocentes(Docentes docentes) {
        em.merge(docentes);
    }

    @Override
    public void eliminarDocentes(Docentes docentes) {
        em.merge(docentes);
    }

    @Override
    public List<Docentes> listarDocentesPorPaterno() {
        String sql = " SELECT d "
                + " FROM Docentes d "
                + " WHERE d.estado = 'A' "
                + " ORDER BY d.paterno ASC";

        Query q = em.createQuery(sql);
        return q.getResultList();
    }

}
