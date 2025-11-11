package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacPrestamosComprobantesDetalles;

import java.util.List;

/**
 * Repositorio para gestión de detalles de préstamos de comprobantes
 * Proporciona operaciones de acceso a datos para detalles de préstamos
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacPrestamosComprobantesDetallesRepository extends JpaRepository<SacPrestamosComprobantesDetalles, Long> {

    /**
     * Busca detalles por préstamo
     */
    List<SacPrestamosComprobantesDetalles> findBySacPrestamosComprobantes_IdSacPrestamoComprobante(
            Long idSacPrestamoComprobante);

    /**
     * Busca detalles por comprobante
     */
    List<SacPrestamosComprobantesDetalles> findBySacComprobantes_IdSacComprobante(Long idSacComprobante);

    /**
     * Busca detalles por estado del comprobante
     */
    List<SacPrestamosComprobantesDetalles> findBySacEstadosComprobantes_IdSacEstadoComprobante(
            Long idSacEstadoComprobante);

    /**
     * Busca detalles activos (prestados)
     */
    List<SacPrestamosComprobantesDetalles> findByPrestamoTrue();

    /**
     * Busca detalles por usuario
     */
    List<SacPrestamosComprobantesDetalles> findByUsuarios_IdUsuario(Long idUsuario);
}
