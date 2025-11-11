package uap.usic.siga.service.sicoes;

import uap.usic.siga.domain.sicoes.*;

import java.util.List;

public interface SicoesService {

    List<ScsBoletaRespaldatoria> listarBoletasRespaldatorias();

    List<ScsFormulario> listarFormularios();

    List<ScsProyecto> listarProyectos();

    List<ScsModalidad> listarModalidades();

    List<ScsTipoModalidad> listarTiposModalidades();

    List<ScsTipoContrato> listarTiposContratos();

    ScsArchivoAdjunto registrarArchivoAdjunto(ScsArchivoAdjunto archivoAdjunto);

    ScsContratacion registrarContratacion(ScsContratacion contratacion);

    List<ScsContratacion> listarContrataciones();

    ScsArchivoAdjunto buscarArchivoAdjuntoPorId(Long idArchivoAdjunto);

    ScsContratacion buscarContratacionPorId(Long idContratacion);

    void modificarContratacion(ScsContratacion contratacion);

    void modificarArchivoAdjunto(ScsArchivoAdjunto archivoAdjunto);

    Long extraerMaximoIdContratacion();

    ScsProyecto registrarProyecto(ScsProyecto proyecto);

    void actualizarProyecto(ScsProyecto proyecto);

    ScsProyecto buscarProyectoPorId(Long idProyecto);

    List<ScsContratacion> listarContratacionesPorModalidadYGestion(Long idModalidad, Integer gestion);

    ScsModalidad buscarModalidadPorId(Long idModalidad);

    ScsPrsContratado registrarPrsContratado(ScsPrsContratado prsContratado);

    List<ScsPrsContratado> listarPrsContratados();

    ScsPrsContratado buscarPrsContratadoPorId(Long idPrsContratado);

    void actualizarPrsContratado(ScsPrsContratado prsContratado);

    List<ScsContratacion> listarContratacionesPorProyectoYGestion(Long idProyecto, Integer gestion);

    List<ScsContratacion> listarContratacionesPorPersonaYGestion(Long idPersona, Integer gestion);

    List<ScsContratacion> listarContratacionesPorDireccionYGestion(Long idDireccion, Integer gestion);

    List<ScsContratacion> listarContratacionesPorDireccion(Long idDireccion);

    List<ScsContratacion> listarContratacionesPorPersona(Long idPersona);

    List<ScsContratacion> listarContratacionesPorGestion(Integer gestion);

    List<ScsContratacion> listarContratacionesPorUnidadYGestion(Long idUnidad, Integer gestion);

    List<ScsContratacion> listarContratacionesPorUnidad(Long idUnidad);

    List<ScsContratacion> listarContratacionesPorProyecto(Long idProyecto);

    List<ScsContratacion> listarContratacionesPorModalidad(Long idModalidad);
}
