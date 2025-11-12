package uap.usic.siga.modelos.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.hql.internal.ast.SqlGenerator;
import org.springframework.stereotype.Repository;

import uap.usic.siga.entidades.PrsCiExpedidos;
import uap.usic.siga.entidades.PrsPaises;
import uap.usic.siga.entidadesPg.PgAdmTiposAdmisiones;
import uap.usic.siga.entidadesPg.PgPrsTiposEmpresasTelefonicas;
import uap.usic.siga.entidadesPg.PgPrsTiposEstadosCiviles;
import uap.usic.siga.entidadesPg.PgPrsTiposSexos;
import uap.usic.siga.entidadesPg.PgPstDocumentos;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.entidadesPg.Postulantes;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.entidadesPg.TiposDocumentos;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.PostulantesDao;

@Repository("postulantesDao")
public class PostulantesDaoImpl implements PostulantesDao {
    @PersistenceContext
    private EntityManager em;

    // =======================MODULO POSTULANTES
    // PREINSCRITOS============================
    @Transactional(rollbackFor = { ServicioException.class })
    @Override
    public Postulantes buscarPostulantesPorCedulaIdentidadGET(String ci) {
        String sql = " Select p "
                + " from Postulantes p "
                + " Where p.ci = :ci "
                + " AND p.estado = 'A'";

        Query q = em.createQuery(sql);
        q.setParameter("ci", ci);
        try {
            return (Postulantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Postulantes buscarPostulantesPorIdGET(Long idPostulante) {
        String sql = " Select p "
                + " from Postulantes p "
                + " Where p.idPostulante = :idPostulante "
                + " AND p.estado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPostulante", idPostulante);
        try {
            return (Postulantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Postulantes buscarPostulantesAdmitidosPorIdGET(Long idAdmitido) {
        String sql = "SELECT p FROM Postulantes p"
                + " WHERE p.idPostulante =: idAdmitido "
                + " AND p.estado = 'E'";
        Query q = em.createQuery(sql);
        q.setParameter("idAdmitido", idAdmitido);
        try {
            return (Postulantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void eliminarPostulantesSET(Postulantes postulante) {
        em.merge(postulante);
    }

    @Override
    public List<Postulantes> listarPostulantesPorIdGET() {
        String sql = " SELECT p "
                + " FROM Postulantes p " // LEFT JOIN p.programa pro
                + " WHERE p.estado = 'A' ORDER BY p.idPostulante ";
        return em.createQuery(sql, Postulantes.class).getResultList();
    }

    @Override
    public List<Postulantes> listarPorPostulantesPorPaternoJPQL() {
        String sql = " SELECT p "
                + " FROM Postulantes p " // LEFT JOIN p.programa pro
                + " WHERE p.estado = 'A' ORDER BY p.paterno ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<Object[]> listarPostulantesPorCiJPQL() {
        String sql = " SELECT p "
                + " FROM Postulantes p LEFT JOIN p.programa pro "
                + " WHERE p.estado = 'A' ORDER BY p.ci ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void modificarPostulantesSET(Postulantes postulantes) {
        em.merge(postulantes);
    }

    @Override
    public void registrarPostulantesSET(Postulantes postulantes) {
        em.persist(postulantes);
    }

    // =====================MODULO POSTULANTES ADMITIDOS===========================

    @Override
    public void registrarPgPstProgramasSET(PgPstProgramas pgPstProgramas) {
        em.persist(pgPstProgramas);
    }

    @Override
    public void modificarPgPstProgramasSET(PgPstProgramas pgPstProgramas){
        em.merge(pgPstProgramas);
    }

    @Override
    public void eliminarPgPstProgramasSET(PgPstProgramas pgPstProgramas) {
        em.merge(pgPstProgramas);
    }

    @Override
    public List<PgAdmTiposAdmisiones> listarPgAdmTiposAdmisiones(){
        String sql = "SELECT adm FROM PgAdmTiposAdmisiones adm"+
                    " WHERE adm.estado = 'A'";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public PgAdmTiposAdmisiones buscarPgAdmTiposAdmisionesPorId(Long id){
        String sql = "SELECT adm FROM PgAdmTiposAdmisiones adm"+
                    " WHERE adm.estado = 'A' AND adm.idTipoAdmision =:id";
        Query q = em.createQuery(sql);
        q.setParameter("id", id);
        try{
            return (PgAdmTiposAdmisiones) q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public void registrarCedulaIdentidadExpedidoSET(PrsCiExpedidos prsCiExpedidos) {
        em.persist(prsCiExpedidos);
    }

    @Override
    public void registrarProgramaSET(Programas programas) {
        em.persist(programas);
    }

    @Override
    public void registrarTiposSexosSET(PgPrsTiposSexos tiposSexos) {
        em.persist(tiposSexos);
    }

    @Override
    public void registrarPaisesSET(PrsPaises prsPaises) {
        em.persist(prsPaises);
    }

    @Override
    public void registrarEmpresaTelefonicaSET(PgPrsTiposEmpresasTelefonicas tiposEmpresasTelefonicas) {
        em.persist(tiposEmpresasTelefonicas);
    }

    @Override
    public void registrarTipoEstadoCivilSET(PgPrsTiposEstadosCiviles tiposEstadosCiviles) {
        em.persist(tiposEstadosCiviles);
    }

    @Override
    public void registrarListaPgPstPersonasDocumentosSET(List<PgPstDocumentos> listaDocumentos) {
        for (PgPstDocumentos p : listaDocumentos) {
            em.persist(p);
        }
    }

    @Override
    public void registrarPstPersonasDocumentosSET(PgPstDocumentos documentos) {
        em.persist(documentos);
    }
    
    @Override
    public Postulantes buscarPostulantesAdmitidosPorCiGET(Long ciAdmitido) {
        String sql = "SELECT p FROM Postulantes p "+
                    "WHERE p.ci =:ci AND p.estado = 'E'";
        Query q = em.createQuery(sql);
        q.setParameter("ci", ciAdmitido);
        try{
            return (Postulantes) q.getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PgPstProgramas> buscarPgPstProgramasPorCiPostulante(String ci) {
        String sql = "SELECT pstprg FROM PgPstProgramas pstprg "+
                    "LEFT JOIN pstprg.postulantes pst "+
                    "LEFT JOIN pstprg.programas prg "+
                    "WHERE pst.estado = 'A' AND pst.ci =:ci";
        Query q = em.createQuery(sql);
        q.setParameter("ci", ci);
        return q.getResultList();
    }

    @Override
    public PgPstProgramas buscarPgPstProgramasPorIdGET(Long idPgPstProgramas) {
       String sql = "SELECT pstprg FROM PgPstProgramas pstprg "+
                    "LEFT JOIN pstprg.postulantes pst "+
                    "LEFT JOIN pstprg.programas prg "+
                    "WHERE pstprg.estado = 'A' AND pstprg.idPstProgramas =:idPgPstProgramas";
        Query q = em.createQuery(sql);
        q.setParameter("idPgPstProgramas", idPgPstProgramas);
        try{
            return (PgPstProgramas) q.getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PgPstProgramas> listarPostulantesAdmitidosPorPaternoJPQL() {
        String sql = "SELECT p FROM Postulantes p WHERE p.estado = 'E' ORDER BY p.paterno ASC";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<PgPstProgramas> listarPostulantesAdmitidosPorIdJPQL() {
        String sql = "SELECT p FROM Postulantes p WHERE p.estado = 'E' ORDER BY p.idPostulante ASC";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<PgPstProgramas> listarPgPstProgramasPorPaternoJPQL() {
        String sql = "SELECT pstprg FROM PgPstProgramas pstprg "+
                     "LEFT JOIN pstprg.postulantes pst "+
                     "LEFT JOIN pstprg.programas prg "+
                     "WHERE pstprg.estado = 'A' ORDER BY pst.paterno ASC";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<PgPstProgramas> listarPgPstProgramasPorIdPrograma(Long idPrograma){
        String sql = "SELECT pstprg FROM PgPstProgramas pstprg "+
                    "LEFT JOIN pstprg.postulantes pst "+
                    "LEFT JOIN pstprg.programas prg "+
                    "WHERE pstprg.estado = 'A' AND prg.idPrograma = :idPrograma";
        Query q = em.createQuery(sql);
        q.setParameter("idPrograma", idPrograma);
        return q.getResultList();
    }
    
    //===================MODULO DE TABLA INTERMEDIA ENTRE PGPSTPERSONAS Y PROGRAMAS==============
    @Override
    public List<Postulantes> listarPostulantesResponse() {
        String sql = "SELECT new uap.usic.siga.dto.PostulantesResponse(p) "+
        "FROM Postulantes p "+
        "LEFT JOIN p.prsTiposSexos pSexo "+
        "LEFT JOIN p.prsPaises pPais "+
        "LEFT JOIN p.prsCiExpedidos pExpedido "+
        // "LEFT JOIN p.programas pPrograma "+
        "WHERE p.estado = 'A' ORDER BY p.paterno";

        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    public List<Object[]> listarPostulantesSimpleResponse(){
        String sql = "SELECT p.nombres, p.paterno, p.materno, p.ci, p.email, p.celular FROM Postulantes p "+
                    "WHERE p.estado = 'A' ORDER BY p.paterno";
        return em.createQuery(sql, Object[].class).getResultList();
    }


    @Override
    public void registrarTiposDocumentos(TiposDocumentos documentos) {
        em.persist(documentos);
    }

    @Override
    public void registrarPgAdmTiposAdmisiones(PgAdmTiposAdmisiones admision) {
        em.persist(admision);
    }

}
