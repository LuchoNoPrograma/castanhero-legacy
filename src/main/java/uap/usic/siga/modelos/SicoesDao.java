package uap.usic.siga.modelos;

import java.util.List;
import uap.usic.siga.entidades.SacComprobantes;
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

public interface SicoesDao {
    
     public List<ScsBoletasRespaldatorias> listarScsBoletasRespaldatoriasJPQL();
     public List<ScsFormularios> listarScsFormulariosJPQL();
     public List<ScsProyectos> listarScsProyectosJPQL();
     public List<ScsModalidades> listarScsModalidadesJPQL();
     public List<ScsTiposModalidades> listarScsTiposModalidadesJPQL();
     public List<ScsTiposContratos> listarScsTiposContratosJPQL();
       
     public ScsArchivosAdjuntos registrarScsArchivosAdjuntosSET(ScsArchivosAdjuntos scsArchivosAdjuntos);
     public ScsContrataciones registrarScsContratacionesSET(ScsContrataciones scsContrataciones);
     
     public List<ScsContrataciones> listarScsContratacionesJPQL();
    
     public ScsArchivosAdjuntos buscarScsArchivosAdjuntosPorIdScsArchivoAdjuntoGET(Long idScsArchivoAdjunto);
     public ScsContrataciones buscarScsContratacionesPorIdScsContratacionGET(Long idScsContratacion);
     
     public void modificarScsContratacionesSET(ScsContrataciones scsContrataciones);
     public void modificarScsArchivosAdjuntosSET(ScsArchivosAdjuntos scsArchivosAdjuntos);
     
     public Long extraerMaximoIdScsContratacionGET();
     
     public ScsProyectos registrarScsProyectosSET(ScsProyectos scsProyectos);
     public void actualizarScsProyectosSET(ScsProyectos scsProyectos);
     
     public ScsProyectos buscarScsProyectosPorIdScsProyectoGET(Long idScsProyecto);
     
     
     
     public ScsModalidades  buscarScsModalidadesPorIdScsModalidadGET(Long idScsModalidad);
     
     public ScsPrsContratados registrarScsPrsContratadosSET(ScsPrsContratados scsPrsContratados);
     public List<ScsPrsContratados> listarScsPrsContratadosesJQPL();
     public ScsPrsContratados buscarScsPrsContratadosPorIdScsPrsContratadosGET(Long idScsPrsContratado);
     public void actualizarScsPrsContratadosSET(ScsPrsContratados scsPrsContratados);
     
     // ===================================== Reportes 
     public List<ScsContrataciones> listarContratacionesPorGestionIdScsProyectosJQPL(Long idScsProyecto, Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorIdProyectoJPQL(Long idScsProyecto);
     public List<ScsContrataciones> listarContratacionesPorGestionProyectoJPQL(Integer gestion);
     
     public List<ScsContrataciones> listarContratacionesPorGestionIdScsModalidadJQPL(Long idScsModalidad, Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorIdModalidadJPQL(Long idScsModalidad);
     public List<ScsContrataciones> listarContratacionesPorGestionModalidadJPQL(Integer gestion);
   
     public List<ScsContrataciones> listarContratacionesPorGestionIdDireccionesFuncionalesJPQL(Long idDireccionFuncional, Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorIdDireccionesFuncionalesJPQL(Long idDireccionFuncional);
     public List<ScsContrataciones> listarContratacionesPorGestionJPQL(Integer gestion);
     
     
     public List<ScsContrataciones> listarContratacionesPorGestionIdScsContratadoJQPL(Long idPersona, Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorIdPersonaFuncionalesJPQL(Long idPersona);
     public List<ScsContrataciones> listarContratacionesPorGestionContratadosJPQL(Integer gestion);
     
     
     public List<ScsContrataciones> listarContratacionesPorGestionIdScsUnidadesFuncionalesJQPL(Long idUnidadFuncional, Integer gestion);
     public List<ScsContrataciones> listarContratacionesPorIdUnidadesFuncionalesJPQL(Long idUnidadFuncional);
     public List<ScsContrataciones> listarContratacionesPorGestionUnidadesFuncionalesJPQL(Integer gestion);
     
}
