package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacCompNroComprobante;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de números de comprobantes
 * Proporciona operaciones de acceso a datos para numeración de comprobantes
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacCompNroComprobanteRepository extends JpaRepository<SacCompNroComprobante, Long> {

    /**
     * Busca números de comprobante por comprobante
     */
    List<SacCompNroComprobante> findBySacComprobantes_IdSacComprobante(Long idSacComprobante);

    /**
     * Busca un número de comprobante específico
     */
    Optional<SacCompNroComprobante> findBySacNroComprobante(Integer sacNroComprobante);

    /**
     * Busca el último número de comprobante
     */
    Optional<SacCompNroComprobante> findFirstByOrderBySacNroComprobanteDesc();
}
