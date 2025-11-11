package uap.usic.siga.servicios;

import java.util.List;
import uap.usic.siga.entidades.ScsArchivosAdjuntos;
import uap.usic.siga.entidades.ScsBoletasRespaldatorias;
import uap.usic.siga.entidades.ScsContrataciones;
import uap.usic.siga.entidades.ScsFormularios;
import uap.usic.siga.entidades.ScsModalidades;
import uap.usic.siga.entidades.ScsProyectos;
import uap.usic.siga.entidades.ScsPrsContratados;
import uap.usic.siga.entidades.ScsTiposContratos;
import uap.usic.siga.entidades.ScsTiposModalidades;

/**
 * Rectorado - USIC 
 * Fecha: 2019-12-27
 * @author Freddy Morales 
 */
public interface SicoesServicios {
    
     public List<ScsBoletasRespaldatorias> listarScsBoletasRespaldatorias();
     public List<ScsFormularios> listarScsFormularios();
     public List<ScsProyectos> listarScsProyectos();
     public List<ScsModalidades> listarScsModalidades();
     public List<ScsTiposModalidades> listarScsTiposModalidades();
     public List<ScsTiposContratos> listarScsTiposContratos();
     
     public ScsArchivosAdjuntos registrarScsArchivosAdjuntos(ScsArchivosAdjuntos scsArchivosAdjuntos);
     public ScsContrataciones registrarScsContrataciones(ScsContrataciones scsContrataciones);
     
     public List<ScsContrataciones> listarScsContrataciones();
     
     public ScsArchivosAdjuntos buscarScsArchivosAdjuntosPorIdScsArchivoAdjunto(Long idScsArchivoAdjunto);
     public ScsContrataciones buscarScsContratacionesPorIdScsContratacion(Long idScsContratacion);
     
     public void modificarScsContrataciones(ScsContrataciones scsContrataciones);
     public void modificarScsArchivosAdjuntos(ScsArchivosAdjuntos scsArchivosAdjuntos);
     
     public Long extraerMaximoIdScsContratacion();
     
     
     public ScsProyectos registrarScsProyectos(ScsProyectos scsProyectos);
     public void actualizarScsProyectos(ScsProyectos scsProyectos);
     
     public ScsProyectos buscarScsProyectosPorIdScsProyecto(Long idScsProyecto);
     
     public List<ScsContrataciones> listarContratacionesPorGestionIdScsModalidad(Long idScsModalidad, Integer gestion);
     
     public ScsModalidades  buscarScsModalidadesPorIdScsModalidad(Long idScsModalidad);
     
     public ScsPrsContratados registrarScsPrsContratados(ScsPrsContratados scsPrsContratados);
     public List<ScsPrsContratados> listarScsPrsContratadoses();
     public ScsPrsContratados buscarScsPrsContratadosPorIdScsPrsContratados(Long idScsPrsContratado);
     public void actualizarScsPrsContratados(ScsPrsContratados scsPrsContratados);
     
     // ========================
     public List<ScsContrataciones> listarContratacionesPorGestionIdScsProyectos(Long idScsProyecto, Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorGestionIdScsContratado(Long idPersona, Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorGestionIdDireccionesFuncionales(Long idDireccionFuncional, Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorIdDireccionesFuncionales(Long idDireccionFuncional);
     public List<ScsContrataciones> listarContratacionesPorIdPersonaFuncionales(Long idPersona);
     public List<ScsContrataciones> listarContratacionesPorGestion(Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorGestionContratados(Integer gestion);
     
     public List<ScsContrataciones> listarContratacionesPorGestionIdScsUnidadesFuncionales(Long idUnidadFuncional, Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorIdUnidadesFuncionales(Long idUnidadFuncional);
     public List<ScsContrataciones> listarContratacionesPorGestionUnidadesFuncionales(Integer gestion);
     
     public List<ScsContrataciones> listarContratacionesPorIdProyecto(Long idScsProyecto);
     public List<ScsContrataciones> listarContratacionesPorGestionProyecto(Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorIdModalidad(Long idScsModalidad);
     public List<ScsContrataciones> listarContratacionesPorGestionModalidad(Integer gestion);
     
}
