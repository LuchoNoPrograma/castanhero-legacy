package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaTiposGastos;

import java.util.List;

/**
 * Repositorio para gestión de tipos de gastos
 * Proporciona operaciones de acceso a datos para tipos de gastos
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaTiposGastosRepository extends JpaRepository<CjaTiposGastos, Long> {

    /**
     * Busca tipos de gastos por clasificación
     */
    List<CjaTiposGastos> findByCjaGastosClasificaciones_IdCjaGastoClasificacion(Long idCjaGastoClasificacion);

    /**
     * Busca tipos de gastos activos por estado
     */
    List<CjaTiposGastos> findByIdEstado(String idEstado);

    /**
     * Busca tipos de gastos ordenados por número
     */
    List<CjaTiposGastos> findAllByOrderByNroTipoGastoAsc();
}
