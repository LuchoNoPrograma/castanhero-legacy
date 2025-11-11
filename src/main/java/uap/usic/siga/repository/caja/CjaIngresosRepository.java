package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaIngresos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de ingresos de caja
 * Proporciona operaciones de acceso a datos para ingresos financieros
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaIngresosRepository extends JpaRepository<CjaIngresos, Long> {

    /**
     * Busca ingresos por persona
     */
    List<CjaIngresos> findByPersonas_IdPersona(Long idPersona);

    /**
     * Busca ingresos por persona, gestión, periodo y estado
     */
    Optional<CjaIngresos> findByPersonas_IdPersonaAndGestionAndPeriodoAndIdEstado(
            Long idPersona, Integer gestion, Integer periodo, String idEstado);

    /**
     * Busca ingresos por persona y estado
     */
    List<CjaIngresos> findByPersonas_IdPersonaAndIdEstado(Long idPersona, String idEstado);

    /**
     * Busca el ingreso más reciente por persona y estado
     */
    Optional<CjaIngresos> findFirstByPersonas_IdPersonaAndIdEstadoOrderByIdCjaIngresoDesc(
            Long idPersona, String idEstado);

    /**
     * Busca ingresos por tipo de ingreso, gestión, periodo, persona y estado
     */
    Optional<CjaIngresos> findByCjaTiposIngresos_IdCjaTipoIngresoAndGestionAndPeriodoAndPersonas_IdPersonaAndIdEstado(
            Long idCjaTipoIngreso, Integer gestion, Integer periodo, Long idPersona, String idEstado);

    /**
     * Busca ingresos por unidad funcional y gestión
     */
    @Query("SELECT i FROM CjaIngresos i " +
           "WHERE i.personas.idPersona IN " +
           "(SELECT pa.personas.idPersona FROM PnlPersonalAdministrativos pa " +
           "WHERE pa.insUnidadesFuncionales.idUnidadFuncional = :idUnidadFuncional) " +
           "AND i.gestion = :gestion")
    List<CjaIngresos> findByUnidadFuncionalAndGestion(
            @Param("idUnidadFuncional") Long idUnidadFuncional,
            @Param("gestion") Integer gestion);

    /**
     * Busca ingresos por gestión y periodo
     */
    List<CjaIngresos> findByGestionAndPeriodo(Integer gestion, Integer periodo);
}
