package uap.usic.siga.servicios;

import java.util.List;
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
import uap.usic.siga.entidadesPg.Postulantes;

/**
 *
 * @author fmbma
 */
public interface PersonasServicios {

    public List<Personas> listarPersonas();

    public List<PrsPaises> listarPaises();

    Personas registrarPersonas(Personas personas);

    public List<Object[]> listarPersonasPorCi();

    public Personas buscarPersonasPorCedulaIdentidad(String ci);

    public Personas buscarPersonaPorIdUsuario(Long id);

    public void registrarPrsPaises(PrsPaises prsPaises);

    public List<PrsTiposSexos> listarTiposSexos();

    public void registrarPrsTiposSexos(PrsTiposSexos prsTiposSexos);

    public void modificarPersonas(Personas personas);

    public void eliminarPersonas(Personas personas);

    public Personas buscarPersonasPorIdPersona(Long id);

    public List<PrsCiExpedidos> listarCedulaIdentidadexpedidos();

    public void registrarPrsCedulaIdentidadExpedidos(PrsCiExpedidos prsCiExpedidos);

    public List<PersonasResponse> listarPersonasResponsePersonalizado();

    public PnlPersonalAdministrativos buscarPnlPersonalAdministrativosPorIdPersonaIdEstado(Long idPersona, String idEstado);

    public CjaIngresos buscarCjaIngresosPoIdPersonaIdEstado(Long idPersona, String idEstado);

    public CjaGastosEjecutados buscarCjaGastosEjecutadosPorIdPersonaIdEstado(Long idPersona, String idEstado);

    public Usuarios buscarUsuariosPorIdPersonaIdEstado(Long idPersona, String idEstado);

    public Personas buscarPersonasPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado);

    public Personas buscarPersonasPorIdCjaIngresoGET(Long idCjaIngreso);

    public List<Personas> listarCjaGastosEjecutadosPersonasPorIdCjaIngresoIdUsuario(Long idCjaIngreso, Long id);

    public List<Personas> listarPersonasComprobantes();

    public Personas buscarPersonasComprobantesPorIdPersona(Long idPersona);

    public PersonasResponse buscarPersonaRazonSocialPorIdSacRazonSocialResponse(Long idSacRazonSocial);

    public PersonasResponse buscarPersonaRazonSocialPorIdPersonaResponse(Long idPersona);

    public List<PrsGradosAcademicos> listarGradosAcademicos();

    public void registrarPrsGradosAcademicos(PrsGradosAcademicos prsGradosAcademicos);

    public List<Personas> listarPersonalAdministrativoPorGestion(Integer gestion);
    
    public List<Personas> listarPersonalAdministrativoActivos();

	public List<Personas> listarPersonasPorFiltroGET(String palabraClave);

    public List<PrsEstadoCivil> listarEstadoCivil();

    public void registrarPrsEstadoCivil(PrsEstadoCivil estadoCivil);

    public PrsGradosAcademicos buscarGradoAcademicoPorId(Long id);
    
    public PrsEstadoCivil buscarEstadoCivilPorId(Long id);
    
    public PrsPaises buscarPaisPorId(Long id);

    public PrsTiposSexos buscarSexoPorId(Long id);

    public PrsCiExpedidos buscarCiExpedidosPorId(Long id);
}