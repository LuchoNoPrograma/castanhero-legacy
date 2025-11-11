package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacCompNroCheque;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de números de cheques
 * Proporciona operaciones de acceso a datos para numeración de cheques
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacCompNroChequeRepository extends JpaRepository<SacCompNroCheque, Long> {

    /**
     * Busca números de cheque por comprobante
     */
    List<SacCompNroCheque> findBySacComprobantes_IdSacComprobante(Long idSacComprobante);

    /**
     * Busca un número de cheque específico
     */
    Optional<SacCompNroCheque> findBySacNroCheque(Integer sacNroCheque);

    /**
     * Busca el último número de cheque
     */
    Optional<SacCompNroCheque> findFirstByOrderBySacNroChequeDesc();
}
