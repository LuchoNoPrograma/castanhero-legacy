package uap.usic.siga.servicios;


import java.util.List;

import javax.validation.Valid;

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

public interface PostulantesServicios {
    public Postulantes buscarPostulantesPorCedulaIdentidadGET(String ci);

    public Postulantes buscarPostulantesPorIdGET(Long idPostulantes);

    public void eliminarPostulantesSET(Postulantes postulantes);

    public List<Postulantes> listarPostulantesPorIdGET();

    public List<Object[]> listarPostulantesPorCiJPQL();

    public List<Postulantes> listarPorPostulantesPorPaternoJPQL();

    public void modificarPostulantesSET(Postulantes postulantes);

    public void registrarPostulantesSET(Postulantes postulantes);

    //================ MODULO POSTULANTES ADMITIDOS Postulante.estado = 'E' ========================   

    @Deprecated
    public Postulantes buscarPostulantesAdmitidosPorIdGET(Long idAdmitido);
    @Deprecated
    public Postulantes buscarPostulantesAdmitidosPorCiGET(Long ciAdmitido);    

    //===================== TABLA INTERMEDIA PgPstPersonas - Programas ===========================    
    public List<PgPstProgramas> buscarPgPstProgramasPorCiPostulante(String ci);

    public PgPstProgramas buscarPgPstProgramasPorIdGET(Long idPgPstProgramas);

    @Deprecated
    public List<PgPstProgramas> listarPostulantesAdmitidosPorPaternoJPQL();

    @Deprecated
    public List<PgPstProgramas> listarPostulantesAdmitidosPorIdJPQL();

    public List<PgPstProgramas> listarPgPstProgramasPorIdPrograma(Long idPrograma);

    public List<PgPstProgramas> listarPgPstProgramasPorPaternoJPQL();

    public void modificarPgPstProgramasSET(PgPstProgramas pgPstProgramas);

    public void eliminarPgPstProgramasSET(PgPstProgramas pgPstProgramas);

    public void registrarPgPstProgramasSET(PgPstProgramas pgPstProgramas);

    public PgAdmTiposAdmisiones buscarPgAdmTiposAdmisionesPorId(Long id);
    

    public List<PgAdmTiposAdmisiones> listarPgAdmTiposAdmisiones();

    //==================REGISTRAR ENTIDADES EXTERNAS======================

    public void registrarTiposDocumentos(TiposDocumentos documentos);

    public void registrarPstPersonasDocumentosSET(PgPstDocumentos documentos);

    public void registrarListaPgPstPersonasDocumentosSET(List<PgPstDocumentos> listaDocumentos);

    public void registrarEmpresaTelefonicaSET(PgPrsTiposEmpresasTelefonicas tiposEmpresasTelefonicas);

    public void registrarTipoEstadoCivilSET(PgPrsTiposEstadosCiviles tiposEstadosCiviles);

    public void registrarPaisesSET(PrsPaises prsPaises);

    public void registrarCedulaIdentidadExpedidoSET(PrsCiExpedidos prsCiExpedidos);

    public void registrarProgramaSET(Programas programas);
    
    public void registrarTiposSexosSET(PgPrsTiposSexos tiposSexos);
    
    public void registrarPgAdmTiposAdmisiones(PgAdmTiposAdmisiones admision);

    //===============RESPONSE PERSONALIZADO========================
    public List<Postulantes> listarPostulantesResponse();

    public List<Postulantes> listarPostulantesSimpleResponse();
}
