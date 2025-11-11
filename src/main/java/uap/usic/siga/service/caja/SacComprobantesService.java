package uap.usic.siga.service.caja;

import uap.usic.siga.domain.caja.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestión de comprobantes contables
 * Define operaciones de negocio para comprobantes, préstamos y devoluciones
 *
 * @author Sistema de Archivo Contable - USIC
 */
public interface SacComprobantesService {

    // ==================== COMPROBANTES ====================

    /**
     * Registra un nuevo comprobante
     */
    SacComprobantes registrarComprobante(SacComprobantes comprobante);

    /**
     * Actualiza un comprobante existente
     */
    SacComprobantes actualizarComprobante(SacComprobantes comprobante);

    /**
     * Busca un comprobante por ID
     */
    Optional<SacComprobantes> buscarComprobantePorId(Long idComprobante);

    /**
     * Lista comprobantes por carpeta
     */
    List<SacComprobantes> listarComprobantesPorCarpeta(Long idCarpeta);

    /**
     * Lista comprobantes por gestión
     */
    List<SacComprobantes> listarComprobantesPorGestion(Integer gestion);

    /**
     * Busca comprobantes por hoja de ruta
     */
    List<SacComprobantes> buscarComprobantesPorHojaRuta(Integer hojaRuta);

    // ==================== PRÉSTAMOS ====================

    /**
     * Registra un préstamo de comprobante
     */
    SacPrestamosComprobantes registrarPrestamo(SacPrestamosComprobantes prestamo);

    /**
     * Registra el detalle de un préstamo
     */
    SacPrestamosComprobantesDetalles registrarDetallePrestamo(
            SacPrestamosComprobantesDetalles detalle);

    /**
     * Lista préstamos por persona
     */
    List<SacPrestamosComprobantes> listarPrestamosPorPersona(Long idPersona);

    /**
     * Lista préstamos activos
     */
    List<SacPrestamosComprobantes> listarPrestamosActivos();

    // ==================== DEVOLUCIONES ====================

    /**
     * Registra una devolución de comprobante
     */
    SacDevolucionesComprobantes registrarDevolucion(SacDevolucionesComprobantes devolucion);

    /**
     * Registra el detalle de una devolución
     */
    SacDevolucionesComprobantesDetalles registrarDetalleDevolucion(
            SacDevolucionesComprobantesDetalles detalle);

    /**
     * Lista devoluciones por persona
     */
    List<SacDevolucionesComprobantes> listarDevolucionesPorPersona(Long idPersona);

    // ==================== ARCHIVOS ADJUNTOS ====================

    /**
     * Registra un archivo adjunto
     */
    SacCompArchivosAdjuntos registrarArchivoAdjunto(SacCompArchivosAdjuntos archivo);

    /**
     * Lista archivos adjuntos por comprobante
     */
    List<SacCompArchivosAdjuntos> listarArchivosAdjuntosPorComprobante(Long idComprobante);

    /**
     * Elimina un archivo adjunto
     */
    void eliminarArchivoAdjunto(Long idArchivo);
}
