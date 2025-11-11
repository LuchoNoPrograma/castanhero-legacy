package uap.usic.siga.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import uap.usic.siga.modelos.PostulantesDao;
import uap.usic.siga.servicios.PostulantesServicios;

@Service
@Transactional
public class PostulantesServiciosImpl implements PostulantesServicios{
    @Autowired
    private PostulantesDao dao;

    @Override
    public Postulantes buscarPostulantesPorCedulaIdentidadGET(String ci) {
        return dao.buscarPostulantesPorCedulaIdentidadGET(ci);
    }

    @Override
    public Postulantes buscarPostulantesPorIdGET(Long idPostulantes) {
        return dao.buscarPostulantesPorIdGET(idPostulantes);
    }

    @Override
    public Postulantes buscarPostulantesAdmitidosPorIdGET(Long idAdmitido){
        return dao.buscarPostulantesAdmitidosPorIdGET(idAdmitido);
    }

    @Override
    public void eliminarPostulantesSET(Postulantes postulantes) {
        dao.eliminarPostulantesSET(postulantes);
    }

    @Override
    public List<Postulantes> listarPostulantesPorIdGET() {
        return dao.listarPostulantesPorIdGET();
    }

    @Override
    public List<Object[]> listarPostulantesPorCiJPQL() {
        return dao.listarPostulantesPorCiJPQL();
    }

    @Override
    public void modificarPostulantesSET(Postulantes postulantes) {
        dao.modificarPostulantesSET(postulantes);
    }

    @Override
    public void registrarPstPersonasDocumentosSET(PgPstDocumentos documentos) {
        dao.registrarPstPersonasDocumentosSET(documentos);
    }

    @Override
    public void registrarEmpresaTelefonicaSET(PgPrsTiposEmpresasTelefonicas tiposEmpresasTelefonicas) {
        dao.registrarEmpresaTelefonicaSET(tiposEmpresasTelefonicas);
    }

    @Override
    public void registrarTipoEstadoCivilSET(PgPrsTiposEstadosCiviles tiposEstadosCiviles) {
        dao.registrarTipoEstadoCivilSET(tiposEstadosCiviles);
    }

    @Override
    public void registrarPostulantesSET(Postulantes postulantes) {
        dao.registrarPostulantesSET(postulantes);
    }

    @Override
    public void registrarPaisesSET(PrsPaises prsPaises) {
        dao.registrarPaisesSET(prsPaises);
    }

    @Override
    public void registrarCedulaIdentidadExpedidoSET(PrsCiExpedidos prsCiExpedidos) {
        dao.registrarCedulaIdentidadExpedidoSET(prsCiExpedidos);
    }

    @Override
    public void registrarProgramaSET(Programas programas) {
        dao.registrarProgramaSET(programas);        
    }

    @Override
    public void registrarTiposSexosSET(PgPrsTiposSexos tiposSexos) {
        dao.registrarTiposSexosSET(tiposSexos);
    }

    @Override
    public void registrarListaPgPstPersonasDocumentosSET(List<PgPstDocumentos> listaDocumentos) {
        dao.registrarListaPgPstPersonasDocumentosSET(listaDocumentos);
    }

    @Override
    public List<PgAdmTiposAdmisiones> listarPgAdmTiposAdmisiones(){
        return dao.listarPgAdmTiposAdmisiones();
    }

    @Override
    public PgAdmTiposAdmisiones buscarPgAdmTiposAdmisionesPorId(Long id){
        return dao.buscarPgAdmTiposAdmisionesPorId(id);
    }

    @Override
    public List<Postulantes> listarPostulantesResponse() {
        return dao.listarPostulantesResponse();
    }

    @Override
    public List<Postulantes> listarPostulantesSimpleResponse(){
        ArrayList<Postulantes> pst = new ArrayList<>();
        for(Object[] a : dao.listarPostulantesSimpleResponse()){
            Postulantes p = new Postulantes();
            p.setNombres((String) a[0]);
            p.setPaterno((String) a[1]);
            p.setMaterno((String)a[2]);
            p.setEmail((String) a[3]);
            p.setCelular((String) a[4]);
            pst.add(p);
        }
        return pst;
    }

    @Override
    public List<Postulantes> listarPorPostulantesPorPaternoJPQL() {
        return dao.listarPorPostulantesPorPaternoJPQL();
    }

    @Override
    public Postulantes buscarPostulantesAdmitidosPorCiGET(Long ciAdmitido) {
        return dao.buscarPostulantesAdmitidosPorCiGET(ciAdmitido);
    }

    @Override
    public List<PgPstProgramas> buscarPgPstProgramasPorCiPostulante(String ci) {
        return dao.buscarPgPstProgramasPorCiPostulante(ci);
    }

    @Override
    public PgPstProgramas buscarPgPstProgramasPorIdGET(Long idPgPstProgramas) {
        return dao.buscarPgPstProgramasPorIdGET(idPgPstProgramas);
    }

    @Override
    public List<PgPstProgramas> listarPostulantesAdmitidosPorPaternoJPQL() {
        return dao.listarPostulantesAdmitidosPorPaternoJPQL();
    }

    @Override
    public List<PgPstProgramas> listarPostulantesAdmitidosPorIdJPQL() {
        return dao.listarPostulantesAdmitidosPorIdJPQL();
    }

    @Override
    public List<PgPstProgramas> listarPgPstProgramasPorPaternoJPQL() {
        return dao.listarPgPstProgramasPorPaternoJPQL();
    }

    @Override
    public List<PgPstProgramas> listarPgPstProgramasPorIdPrograma(Long idPrograma){
        return dao.listarPgPstProgramasPorIdPrograma(idPrograma);
    }

    @Override
    public void modificarPgPstProgramasSET(PgPstProgramas pgPstProgramas) {
        dao.modificarPgPstProgramasSET(pgPstProgramas);
    }

    @Override
    public void eliminarPgPstProgramasSET(PgPstProgramas pgPstProgramas) {
        dao.eliminarPgPstProgramasSET(pgPstProgramas);
    }

    @Override
    public void registrarPgPstProgramasSET(PgPstProgramas pgPstProgramas) {
        dao.registrarPgPstProgramasSET(pgPstProgramas);
    }

    @Override
    public void registrarTiposDocumentos(TiposDocumentos documentos){
        dao.registrarTiposDocumentos(documentos);
    }

    @Override
    public void registrarPgAdmTiposAdmisiones(PgAdmTiposAdmisiones admision){
        dao.registrarPgAdmTiposAdmisiones(admision);
    }
}
