package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaGastosEjecutados;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para gestión de gastos ejecutados
 * Proporciona operaciones de acceso a datos para gastos realizados
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaGastosEjecutadosRepository extends JpaRepository<CjaGastosEjecutados, Long> {

    /**
     * Busca gastos por ingreso de caja y usuario
     */
    List<CjaGastosEjecutados> findByCjaIngresos_IdCjaIngresoAndUsuarios_IdUsuario(
            Long idCjaIngreso, Long idUsuario);

    /**
     * Busca gastos por periodo, gestión y usuario
     */
    List<CjaGastosEjecutados> findByPeriodoAndGestionAndUsuarios_IdUsuario(
            Integer periodo, Integer gestion, Long idUsuario);

    /**
     * Busca gastos por ingreso de caja, rango de fechas
     */
    List<CjaGastosEjecutados> findByCjaIngresos_IdCjaIngresoAndFecGastoBetween(
            Long idCjaIngreso, Date fecInicio, Date fecFin);

    /**
     * Busca gastos por persona, gestión, periodo y usuario
     */
    List<CjaGastosEjecutados> findByPersonas_IdPersonaAndGestionAndPeriodoAndUsuarios_IdUsuarioAndCjaIngresos_IdCjaIngreso(
            Long idPersona, Integer gestion, Integer periodo, Long idUsuario, Long idCjaIngreso);

    /**
     * Cuenta gastos por ingreso de caja y estado
     */
    Long countByCjaIngresos_IdCjaIngresoAndIdEstado(Long idCjaIngreso, String idEstado);

    /**
     * Calcula total de gastos por ingreso
     */
    @Query("SELECT SUM(g.totalG) FROM CjaGastosEjecutados g " +
           "WHERE g.cjaIngresos.idCjaIngreso = :idCjaIngreso " +
           "AND g.idEstado = :idEstado")
    Double sumTotalGastoByCjaIngresoAndEstado(
            @Param("idCjaIngreso") Long idCjaIngreso,
            @Param("idEstado") String idEstado);

    /**
     * Busca gastos por usuario y rango de fechas
     */
    List<CjaGastosEjecutados> findByUsuarios_IdUsuarioAndFecGastoBetween(
            Long idUsuario, Date fecInicio, Date fecFin);
}
