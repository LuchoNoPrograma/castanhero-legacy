package uap.usic.siga.servicios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.dto.PersonasResponse;
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.PrsCiExpedidos;
import uap.usic.siga.entidades.PrsEstadoCivil;
import uap.usic.siga.entidades.PrsGradosAcademicos;
import uap.usic.siga.entidades.PrsPaises;
import uap.usic.siga.entidades.PrsTiposSexos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.modelos.PersonasDao;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author fmbma
 */
@Service("personasServicios")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PersonasServiciosImpl implements PersonasServicios {

    private final PersonasDao dao;

    @Override
    public List<Personas> listarPersonas() {
        return dao.listarPersonasGET();
    }

    @Override
    public List<PrsPaises> listarPaises() {
        return dao.listarPaisesGET();
    }

    @Override
    public List<Object[]> listarPersonasPorCi() {
        return dao.listarPersonasPorCiJPQL();
    }

    @Override
    public Personas buscarPersonasPorCedulaIdentidad(String ci) {
        return dao.buscarPersonasPorCedulaIdentidadGET(ci);
    }

    @Override
    public Personas buscarPersonaPorIdUsuario(Long idUsuario) {
        return dao.buscarPersonaPorIdUsuarioGET(idUsuario);
    }

    @Override
    public void registrarPrsPaises(PrsPaises prsPaises) {
        dao.registrarPaisesSET(prsPaises);
    }

    @Override
    public List<PrsTiposSexos> listarTiposSexos() {
        return dao.listarTiposSexosJPQL();
    }

    @Override
    public void registrarPrsTiposSexos(PrsTiposSexos prsTiposSexos) {
        dao.registrarPrsTiposSexosSET(prsTiposSexos);
    }

    @Override
    public void modificarPersonas(Personas personas) {
        dao.modificarPersonasSET(personas);
    }

    @Override
    public void eliminarPersonas(Personas personas) {
        dao.eliminarPersonasSET(personas);
    }

    @Override
    public Personas buscarPersonasPorIdPersona(Long idPersona) {
        return dao.buscarPersonasPorIdPersonaGET(idPersona);
    }

    @Override
    public List<PrsCiExpedidos> listarCedulaIdentidadexpedidos() {
        return dao.listarCedulaIdentidadexpedidosJPQL();
    }

    @Override
    public void registrarPrsCedulaIdentidadExpedidos(PrsCiExpedidos prsCiExpedidos) {
        dao.registrarCedulaIdentidadExpedidoSET(prsCiExpedidos);
    }

    @Override
    public List<PersonasResponse> listarPersonasResponsePersonalizado() {
        return dao.listarPersonasResponsePersonalizadoJPQL();
    }

    @Override
    public PnlPersonalAdministrativos buscarPnlPersonalAdministrativosPorIdPersonaIdEstado(Long idPersona,
            String idEstado) {
        return dao.buscarPnlPersonalAdministrativosPorIdPersonaIdEstadoGET(idPersona, idEstado);
    }

    @Override
    public CjaIngresos buscarCjaIngresosPoIdPersonaIdEstado(Long idPersona, String idEstado) {
        return dao.buscarCjaIngresosPoIdPersonaIdEstadoGET(idPersona, idEstado);
    }

    @Override
    public CjaGastosEjecutados buscarCjaGastosEjecutadosPorIdPersonaIdEstado(Long idPersona, String idEstado) {
        return dao.buscarCjaGastosEjecutadosPorIdPersonaIdEstadoGET(idPersona, idEstado);
    }

    @Override
    public Usuarios buscarUsuariosPorIdPersonaIdEstado(Long idPersona, String idEstado) {
        return dao.buscarUsuariosPorIdPersonaIdEstadoGET(idPersona, idEstado);
    }

    @Override
    public Personas buscarPersonasPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado) {
        return dao.buscarPersonasPorIdCjaGastoEjecutadoGET(idCjaGastoEjecutado);
    }

    @Override
    public Personas buscarPersonasPorIdCjaIngresoGET(Long idCjaIngreso) {
        return dao.buscarPersonasPorIdCjaIngresoGET(idCjaIngreso);
    }

    @Override
    public List<Personas> listarCjaGastosEjecutadosPersonasPorIdCjaIngresoIdUsuario(Long idCjaIngreso, Long id) {
        return dao.listarCjaGastosEjecutadosPersonasPorIdCjaIngresoIdUsuarioJPQL(idCjaIngreso, id);
    }

    @Override
    public List<Personas> listarPersonasComprobantes() {
        return dao.listarPersonasComprobantesJPQL();
    }

    @Override
    public Personas buscarPersonasComprobantesPorIdPersona(Long idPersona) {
        return dao.buscarPersonasComprobantesPorIdPersonaGET(idPersona);
    }

    @Override
    public PersonasResponse buscarPersonaRazonSocialPorIdSacRazonSocialResponse(Long idSacRazonSocial) {
        return dao.buscarPersonaRazonSocialPorIdSacRazonSocialResponseGET(idSacRazonSocial);
    }

    @Override
    public PersonasResponse buscarPersonaRazonSocialPorIdPersonaResponse(Long idPersona) {
        return dao.buscarPersonaRazonSocialPorIdPersonaResponseGET(idPersona);
    }

    @Override
    public List<PrsGradosAcademicos> listarGradosAcademicos() {
        return dao.listarGradosAcademicosJPQL();
    }

    @Override
    public void registrarPrsGradosAcademicos(PrsGradosAcademicos prsGradosAcademicos) {
        dao.registrarPrsGradosAcademicosSET(prsGradosAcademicos);
    }

    @Override
    public List<Personas> listarPersonalAdministrativoPorGestion(Integer gestion) {
        return dao.listarPersonalAdministrativoPorGestionJPQL(gestion);
    }

    @Override
    public Personas registrarPersonas(Personas personas) {
        dao.registrarPersonasSET(personas);
        return null;
    }

    @Override
    public List<Personas> listarPersonalAdministrativoActivos() {
        return dao.listarPersonalAdministrativoActivosJPQL();
    }

    @Override
    public List<PrsEstadoCivil> listarEstadoCivil() {
        return dao.listarEstadoCivilJPQL();
    }

    @Override
    public List<Personas> listarPersonasPorFiltroGET(String palabraClave) {

        return dao.listarPersonasPorFiltroGET(palabraClave);
    }

    @Override
    public PrsGradosAcademicos buscarGradoAcademicoPorId(Long id) {
        return dao.buscarGradoAcademicoPorIdGET(id);
    }

    @Override
    public PrsEstadoCivil buscarEstadoCivilPorId(Long id) {        
        return dao.buscarEstadoCivilPorIdGET(id);
    }

    @Override
    public void registrarPrsEstadoCivil(PrsEstadoCivil estadoCivil) {
        dao.registrarPrsEstadoCivil(estadoCivil);
    }

    @Override
    public PrsPaises buscarPaisPorId(Long id) {
        return dao.buscarPaisPorId(id);
    }

    @Override
    public PrsTiposSexos buscarSexoPorId(Long id) {
        return dao.buscarSexoPorId(id);
    }

    @Override
    public PrsCiExpedidos buscarCiExpedidosPorId(Long id) {
        return dao.buscarCiExpedidosPorId(id);
    }
}
