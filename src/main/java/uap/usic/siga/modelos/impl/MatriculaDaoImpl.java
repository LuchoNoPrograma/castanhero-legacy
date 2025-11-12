package uap.usic.siga.modelos.impl;

import java.util.List;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.entidadesPg.Depositos;
import uap.usic.siga.entidadesPg.PgArchivosAdjuntos;
import uap.usic.siga.entidadesPg.PgPstMatriculas;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.MatriculaDao;

@Repository("MatriculaDao")
public class MatriculaDaoImpl implements MatriculaDao{


    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
    @Override
    public List<PgPstMatriculas> listaMatriculas() {
        String sql = "SELECT ma "
                + "FROM PgPstMatriculas ma"
                + " WHERE ma.estado = 'A' ";   
        return em.createQuery(sql, PgPstMatriculas.class).getResultList();
    }

    @Override
    public PgPstMatriculas buscarMatriculasPorIdPstPrograma(Long idPstPrograma){
        String sql = "SELECT ma FROM PgPstMatriculas ma "+
                    "LEFT JOIN ma.pstProgramas pstprg "+
                    "LEFT JOIN ma.grupo gr "+
                    "WHERE pstprg.idPstProgramas = :id AND pstprg.estado = 'A'";
        try{
            return em.createQuery(sql, PgPstMatriculas.class).getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<PgPstMatriculas> listarMatriculasPorIdGrupo(Long idGrupo){
        String sql = "SELECT ma FROM PgPstMatriculas ma "+
                    "LEFT JOIN ma.pstProgramas pstprg "+
                    "WHERE ma.estado = 'A' AND ma.grupo.idPgPrgPlnGrupos = :idGrupo";
        return em.createQuery(sql, PgPstMatriculas.class).
            setParameter("idGrupo", idGrupo).getResultList();
        
    }

    @Override
    public PgPstMatriculas registrarMatriculas(PgPstMatriculas matricula) {
        em.persist(matricula);
        return matricula;
    }

    @Override
    public PgPstMatriculas modificarPgPstMatriculas(PgPstMatriculas matricula){
        return em.merge(matricula);
    }

    @Override
    public void registrarfkPgPstProgramas(PgPstProgramas programa) {
        em.persist(programa);
    }

    @Override
    public List<Depositos> listaDepositos() {
        String sql = "SELECT d "
                + "FROM Depositos d "
                + "WHERE d.estado = 'A' ";   
        Query q = (Query) em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public Depositos registrarDepositos(Depositos deposito) {
        em.persist(deposito);
        return deposito;
    }

    @Override
    public PgArchivosAdjuntos registrarReciboAbjuntoSET(PgArchivosAdjuntos pgArchivosAdjuntos) {
        em.persist(pgArchivosAdjuntos);
	    return pgArchivosAdjuntos;
    }
   
    
}
