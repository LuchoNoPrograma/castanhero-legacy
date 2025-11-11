package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaPedidosGastos;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para gestión de pedidos de gastos
 * Proporciona operaciones de acceso a datos para solicitudes de gastos
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaPedidosGastosRepository extends JpaRepository<CjaPedidosGastos, Long> {

    /**
     * Busca pedidos por rango de fechas
     */
    List<CjaPedidosGastos> findByFecPedidoBetween(Date fecInicio, Date fecFin);

    /**
     * Busca pedidos por gestión
     */
    List<CjaPedidosGastos> findByGestionOrderByFecPedidoDesc(Integer gestion);

    /**
     * Busca pedidos por estado
     */
    List<CjaPedidosGastos> findByIdEstadoOrderByFecPedidoDesc(String idEstado);
}
