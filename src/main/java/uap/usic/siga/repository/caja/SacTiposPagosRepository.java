package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacTiposPagos;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de tipos de pagos
 * Proporciona operaciones de acceso a datos para modalidades de pago
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacTiposPagosRepository extends JpaRepository<SacTiposPagos, Long> {

    /**
     * Busca tipos de pagos activos por estado
     */
    List<SacTiposPagos> findByIdEstado(String idEstado);

    /**
     * Busca un tipo de pago por nombre
     */
    Optional<SacTiposPagos> findBySacTipoPago(String sacTipoPago);
}
