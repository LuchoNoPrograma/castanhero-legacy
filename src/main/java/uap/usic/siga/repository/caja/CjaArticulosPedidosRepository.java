package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaArticulosPedidos;

import java.util.List;

/**
 * Repositorio para gestión de artículos de pedidos
 * Proporciona operaciones de acceso a datos para artículos solicitados
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaArticulosPedidosRepository extends JpaRepository<CjaArticulosPedidos, Long> {

    /**
     * Busca artículos por nombre (coincidencia parcial)
     */
    List<CjaArticulosPedidos> findByArticuloContainingIgnoreCase(String articulo);

    /**
     * Busca artículos activos por estado
     */
    List<CjaArticulosPedidos> findByIdEstado(String idEstado);
}
