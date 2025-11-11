package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaCntTiposGastos;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para contabilización de gastos por tipo
 * Proporciona operaciones para reportes de gastos por tipo
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaCntTiposGastosRepository extends JpaRepository<CjaCntTiposGastos, Long> {

    /**
     * Busca contabilización por tipo de gasto y gestión
     */
    Optional<CjaCntTiposGastos> findByCjaTiposGastos_IdCjaTipoGastoAndGestion(
            Long idCjaTipoGasto, Integer gestion);

    /**
     * Busca todas las contabilizaciones de una gestión
     */
    List<CjaCntTiposGastos> findByGestionOrderByTotalGastoDesc(Integer gestion);

    /**
     * Busca contabilizaciones por periodo y gestión
     */
    List<CjaCntTiposGastos> findByPeriodoAndGestion(Integer periodo, Integer gestion);
}
