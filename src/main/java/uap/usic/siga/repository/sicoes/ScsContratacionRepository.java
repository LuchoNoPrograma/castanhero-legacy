package uap.usic.siga.repository.sicoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.sicoes.ScsContratacion;

import java.util.List;

@Repository
public interface ScsContratacionRepository extends JpaRepository<ScsContratacion, Long> {

    List<ScsContratacion> findByIdEstadoNot(String idEstado);

    @Query("SELECT MAX(c.idScsContratacion) FROM ScsContratacion c")
    Long findMaxId();

    @Query("SELECT c FROM ScsContratacion c WHERE c.modalidad.idScsModalidad = :idModalidad AND c.gestion = :gestion AND c.idEstado <> 'X'")
    List<ScsContratacion> findByModalidadAndGestion(@Param("idModalidad") Long idModalidad, @Param("gestion") Integer gestion);

    @Query("SELECT c FROM ScsContratacion c WHERE c.proyecto.idScsProyecto = :idProyecto AND c.gestion = :gestion AND c.idEstado <> 'X'")
    List<ScsContratacion> findByProyectoAndGestion(@Param("idProyecto") Long idProyecto, @Param("gestion") Integer gestion);

    @Query("SELECT c FROM ScsContratacion c WHERE c.persona.idPersona = :idPersona AND c.gestion = :gestion AND c.idEstado <> 'X'")
    List<ScsContratacion> findByPersonaAndGestion(@Param("idPersona") Long idPersona, @Param("gestion") Integer gestion);

    @Query("SELECT c FROM ScsContratacion c JOIN c.proyecto p WHERE p.unidadFuncional.direccionFuncional.idDireccionFuncional = :idDireccion AND c.gestion = :gestion AND c.idEstado <> 'X'")
    List<ScsContratacion> findByDireccionFuncionalAndGestion(@Param("idDireccion") Long idDireccion, @Param("gestion") Integer gestion);

    @Query("SELECT c FROM ScsContratacion c JOIN c.proyecto p WHERE p.unidadFuncional.direccionFuncional.idDireccionFuncional = :idDireccion AND c.idEstado <> 'X'")
    List<ScsContratacion> findByDireccionFuncional(@Param("idDireccion") Long idDireccion);

    @Query("SELECT c FROM ScsContratacion c WHERE c.persona.idPersona = :idPersona AND c.idEstado <> 'X'")
    List<ScsContratacion> findByPersona(@Param("idPersona") Long idPersona);

    List<ScsContratacion> findByGestionAndIdEstadoNot(Integer gestion, String idEstado);

    @Query("SELECT c FROM ScsContratacion c JOIN c.proyecto p WHERE p.unidadFuncional.idUnidadFuncional = :idUnidad AND c.gestion = :gestion AND c.idEstado <> 'X'")
    List<ScsContratacion> findByUnidadFuncionalAndGestion(@Param("idUnidad") Long idUnidad, @Param("gestion") Integer gestion);

    @Query("SELECT c FROM ScsContratacion c JOIN c.proyecto p WHERE p.unidadFuncional.idUnidadFuncional = :idUnidad AND c.idEstado <> 'X'")
    List<ScsContratacion> findByUnidadFuncional(@Param("idUnidad") Long idUnidad);

    @Query("SELECT c FROM ScsContratacion c WHERE c.proyecto.idScsProyecto = :idProyecto AND c.idEstado <> 'X'")
    List<ScsContratacion> findByProyecto(@Param("idProyecto") Long idProyecto);

    @Query("SELECT c FROM ScsContratacion c WHERE c.modalidad.idScsModalidad = :idModalidad AND c.idEstado <> 'X'")
    List<ScsContratacion> findByModalidad(@Param("idModalidad") Long idModalidad);
}
