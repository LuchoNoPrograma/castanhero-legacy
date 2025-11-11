package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacDevolucionesComprobantesDetalles;

import java.util.List;

/**
 * Repositorio para gestión de detalles de devoluciones de comprobantes
 * Proporciona operaciones de acceso a datos para detalles de devoluciones
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacDevolucionesComprobantesDetallesRepository extends JpaRepository<SacDevolucionesComprobantesDetalles, Long> {

    /**
     * Busca detalles por devolución
     */
    List<SacDevolucionesComprobantesDetalles> findBySacDevolucionesComprobantes_IdSacDevolucionComprobante(
            Long idSacDevolucionComprobante);

    /**
     * Busca detalles por comprobante
     */
    List<SacDevolucionesComprobantesDetalles> findBySacComprobantes_IdSacComprobante(Long idSacComprobante);

    /**
     * Busca detalles por préstamo
     */
    List<SacDevolucionesComprobantesDetalles> findBySacPrestamosComprobantes_IdSacPrestamoComprobante(
            Long idSacPrestamoComprobante);

    /**
     * Busca detalles por estado del comprobante
     */
    List<SacDevolucionesComprobantesDetalles> findBySacEstadosComprobantes_IdSacEstadoComprobante(
            Long idSacEstadoComprobante);

    /**
     * Busca detalles por usuario
     */
    List<SacDevolucionesComprobantesDetalles> findByUsuarios_IdUsuario(Long idUsuario);
}
