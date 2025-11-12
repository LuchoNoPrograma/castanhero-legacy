package uap.usic.siga.service.caja.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.caja.*;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.caja.*;
import uap.usic.siga.service.caja.SacComprobantesService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de comprobantes contables
 * Proporciona lógica de negocio para comprobantes, préstamos y devoluciones
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Slf4j
@Service("sacComprobantesService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SacComprobantesServiceImpl implements SacComprobantesService {

    private final SacComprobantesRepository comprobantesRepository;
    private final SacPrestamosComprobantesRepository prestamosRepository;
    private final SacPrestamosComprobantesDetallesRepository prestamosDetallesRepository;
    private final SacDevolucionesComprobantesRepository devolucionesRepository;
    private final SacDevolucionesComprobantesDetallesRepository devolucionesDetallesRepository;
    private final SacCompArchivosAdjuntosRepository archivosAdjuntosRepository;

    // ==================== COMPROBANTES ====================

    @Override
    @Transactional
    public SacComprobantes registrarComprobante(SacComprobantes comprobante) {
        log.info("Registrando nuevo comprobante: {}", comprobante.getNumeroComprobante());

        if (comprobante == null) {
            throw new ValidacionException("El comprobante no puede ser nulo");
        }

        if (comprobante.getNumeroComprobante() == null) {
            throw new ValidacionException("El número de comprobante es obligatorio");
        }

        if (comprobante.getSacCarpetas() == null) {
            throw new ValidacionException("La carpeta asociada al comprobante es obligatoria");
        }

        // Establecer estado por defecto
        if (comprobante.getSacEstadosComprobantes() == null) {
            throw new ValidacionException("El estado del comprobante es obligatorio");
        }

        // Establecer fecha de registro
        if (comprobante.getFecRegistro() == null) {
            comprobante.setFecRegistro(new Date());
        }

        SacComprobantes comprobanteGuardado = comprobantesRepository.save(comprobante);
        log.info("Comprobante registrado exitosamente con ID: {}", comprobanteGuardado.getIdComprobante());

        return comprobanteGuardado;
    }

    @Override
    @Transactional
    public SacComprobantes actualizarComprobante(SacComprobantes comprobante) {
        log.info("Actualizando comprobante ID: {}", comprobante.getIdComprobante());

        if (comprobante.getIdComprobante() == null) {
            throw new ValidacionException("El ID del comprobante es requerido para actualizar");
        }

        SacComprobantes comprobanteExistente = comprobantesRepository
                .findById(comprobante.getIdComprobante())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Comprobante no encontrado con ID: " + comprobante.getIdComprobante()));

        SacComprobantes comprobanteActualizado = comprobantesRepository.save(comprobante);
        log.info("Comprobante actualizado exitosamente ID: {}", comprobanteActualizado.getIdComprobante());

        return comprobanteActualizado;
    }

    @Override
    public Optional<SacComprobantes> buscarComprobantePorId(Long idComprobante) {
        log.debug("Buscando comprobante por ID: {}", idComprobante);

        if (idComprobante == null) {
            throw new ValidacionException("El ID del comprobante no puede ser nulo");
        }

        return comprobantesRepository.findById(idComprobante);
    }

    @Override
    public List<SacComprobantes> listarComprobantesPorCarpeta(Long idCarpeta) {
        log.debug("Listando comprobantes por carpeta ID: {}", idCarpeta);

        if (idCarpeta == null) {
            throw new ValidacionException("El ID de la carpeta es requerido");
        }

        return comprobantesRepository.findBySacCarpetas_IdCarpeta(idCarpeta);
    }

    @Override
    public List<SacComprobantes> listarComprobantesPorGestion(Integer gestion) {
        log.debug("Listando comprobantes por gestión: {}", gestion);

        if (gestion == null) {
            throw new ValidacionException("La gestión es requerida");
        }

        return comprobantesRepository.findByGestion(gestion);
    }

    @Override
    public List<SacComprobantes> buscarComprobantesPorHojaRuta(Integer hojaRuta) {
        log.debug("Buscando comprobantes por hoja de ruta: {}", hojaRuta);

        if (hojaRuta == null) {
            throw new ValidacionException("El número de hoja de ruta es requerido");
        }

        return comprobantesRepository.findByHojaRuta(hojaRuta);
    }

    // ==================== PRÉSTAMOS ====================

    @Override
    @Transactional
    public SacPrestamosComprobantes registrarPrestamo(SacPrestamosComprobantes prestamo) {
        log.info("Registrando préstamo de comprobante para persona ID: {}",
                prestamo.getPersonas() != null ? prestamo.getPersonas().getIdPersona() : null);

        if (prestamo == null) {
            throw new ValidacionException("El préstamo no puede ser nulo");
        }

        if (prestamo.getPersonas() == null) {
            throw new ValidacionException("La persona que solicita el préstamo es obligatoria");
        }

        if (prestamo.getFecPrestamo() == null) {
            prestamo.setFecPrestamo(new Date());
        }

        // Establecer estado activo
        if (prestamo.getIdEstado() == null) {
            prestamo.setIdEstado("A");
        }

        SacPrestamosComprobantes prestamoGuardado = prestamosRepository.save(prestamo);
        log.info("Préstamo registrado exitosamente con ID: {}", prestamoGuardado.getIdPrestamo());

        return prestamoGuardado;
    }

    @Override
    @Transactional
    public SacPrestamosComprobantesDetalles registrarDetallePrestamo(
            SacPrestamosComprobantesDetalles detalle) {
        log.info("Registrando detalle de préstamo para comprobante ID: {}",
                detalle.getSacComprobantes() != null ? detalle.getSacComprobantes().getIdComprobante() : null);

        if (detalle == null) {
            throw new ValidacionException("El detalle del préstamo no puede ser nulo");
        }

        if (detalle.getSacPrestamosComprobantes() == null) {
            throw new ValidacionException("El préstamo asociado es obligatorio");
        }

        if (detalle.getSacComprobantes() == null) {
            throw new ValidacionException("El comprobante es obligatorio");
        }

        // Establecer estado del comprobante como prestado
        SacComprobantes comprobante = detalle.getSacComprobantes();
        if (comprobante.getIdComprobante() != null) {
            Optional<SacComprobantes> comprobanteOpt = comprobantesRepository
                    .findById(comprobante.getIdComprobante());
            if (comprobanteOpt.isPresent()) {
                SacComprobantes comp = comprobanteOpt.get();
                // Actualizar estado del comprobante (P = Prestado)
                log.info("Comprobante ID {} marcado como prestado", comp.getIdComprobante());
            }
        }

        SacPrestamosComprobantesDetalles detalleGuardado = prestamosDetallesRepository.save(detalle);
        log.info("Detalle de préstamo registrado exitosamente con ID: {}",
                detalleGuardado.getIdPrestamoDetalle());

        return detalleGuardado;
    }

    @Override
    public List<SacPrestamosComprobantes> listarPrestamosPorPersona(Long idPersona) {
        log.debug("Listando préstamos por persona ID: {}", idPersona);

        if (idPersona == null) {
            throw new ValidacionException("El ID de la persona es requerido");
        }

        return prestamosRepository.findByPersonas_IdPersona(idPersona);
    }

    @Override
    public List<SacPrestamosComprobantes> listarPrestamosActivos() {
        log.debug("Listando préstamos activos");
        return prestamosRepository.findByIdEstado("A");
    }

    // ==================== DEVOLUCIONES ====================

    @Override
    @Transactional
    public SacDevolucionesComprobantes registrarDevolucion(SacDevolucionesComprobantes devolucion) {
        log.info("Registrando devolución de comprobante para persona ID: {}",
                devolucion.getPersonas() != null ? devolucion.getPersonas().getIdPersona() : null);

        if (devolucion == null) {
            throw new ValidacionException("La devolución no puede ser nula");
        }

        if (devolucion.getPersonas() == null) {
            throw new ValidacionException("La persona que realiza la devolución es obligatoria");
        }

        if (devolucion.getFecDevolucion() == null) {
            devolucion.setFecDevolucion(new Date());
        }

        // Establecer estado activo
        if (devolucion.getIdEstado() == null) {
            devolucion.setIdEstado("A");
        }

        SacDevolucionesComprobantes devolucionGuardada = devolucionesRepository.save(devolucion);
        log.info("Devolución registrada exitosamente con ID: {}", devolucionGuardada.getIdDevolucion());

        return devolucionGuardada;
    }

    @Override
    @Transactional
    public SacDevolucionesComprobantesDetalles registrarDetalleDevolucion(
            SacDevolucionesComprobantesDetalles detalle) {
        log.info("Registrando detalle de devolución para comprobante ID: {}",
                detalle.getSacComprobantes() != null ? detalle.getSacComprobantes().getIdComprobante() : null);

        if (detalle == null) {
            throw new ValidacionException("El detalle de la devolución no puede ser nulo");
        }

        if (detalle.getSacDevolucionesComprobantes() == null) {
            throw new ValidacionException("La devolución asociada es obligatoria");
        }

        if (detalle.getSacComprobantes() == null) {
            throw new ValidacionException("El comprobante es obligatorio");
        }

        // Restablecer estado del comprobante como disponible
        SacComprobantes comprobante = detalle.getSacComprobantes();
        if (comprobante.getIdComprobante() != null) {
            Optional<SacComprobantes> comprobanteOpt = comprobantesRepository
                    .findById(comprobante.getIdComprobante());
            if (comprobanteOpt.isPresent()) {
                SacComprobantes comp = comprobanteOpt.get();
                // Actualizar estado del comprobante (D = Disponible)
                log.info("Comprobante ID {} marcado como disponible", comp.getIdComprobante());
            }
        }

        SacDevolucionesComprobantesDetalles detalleGuardado = devolucionesDetallesRepository.save(detalle);
        log.info("Detalle de devolución registrado exitosamente con ID: {}",
                detalleGuardado.getIdDevolucionDetalle());

        return detalleGuardado;
    }

    @Override
    public List<SacDevolucionesComprobantes> listarDevolucionesPorPersona(Long idPersona) {
        log.debug("Listando devoluciones por persona ID: {}", idPersona);

        if (idPersona == null) {
            throw new ValidacionException("El ID de la persona es requerido");
        }

        return devolucionesRepository.findByPersonas_IdPersona(idPersona);
    }

    // ==================== ARCHIVOS ADJUNTOS ====================

    @Override
    @Transactional
    public SacCompArchivosAdjuntos registrarArchivoAdjunto(SacCompArchivosAdjuntos archivo) {
        log.info("Registrando archivo adjunto: {}", archivo.getNombreArchivo());

        if (archivo == null) {
            throw new ValidacionException("El archivo adjunto no puede ser nulo");
        }

        if (archivo.getSacComprobantes() == null) {
            throw new ValidacionException("El comprobante asociado al archivo es obligatorio");
        }

        if (archivo.getNombreArchivo() == null || archivo.getNombreArchivo().trim().isEmpty()) {
            throw new ValidacionException("El nombre del archivo es obligatorio");
        }

        if (archivo.getFecRegistro() == null) {
            archivo.setFecRegistro(new Date());
        }

        SacCompArchivosAdjuntos archivoGuardado = archivosAdjuntosRepository.save(archivo);
        log.info("Archivo adjunto registrado exitosamente con ID: {}", archivoGuardado.getIdArchivo());

        return archivoGuardado;
    }

    @Override
    public List<SacCompArchivosAdjuntos> listarArchivosAdjuntosPorComprobante(Long idComprobante) {
        log.debug("Listando archivos adjuntos por comprobante ID: {}", idComprobante);

        if (idComprobante == null) {
            throw new ValidacionException("El ID del comprobante es requerido");
        }

        return archivosAdjuntosRepository.findBySacComprobantes_IdComprobante(idComprobante);
    }

    @Override
    @Transactional
    public void eliminarArchivoAdjunto(Long idArchivo) {
        log.info("Eliminando archivo adjunto ID: {}", idArchivo);

        if (idArchivo == null) {
            throw new ValidacionException("El ID del archivo es requerido");
        }

        SacCompArchivosAdjuntos archivo = archivosAdjuntosRepository.findById(idArchivo)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Archivo adjunto no encontrado con ID: " + idArchivo));

        archivosAdjuntosRepository.delete(archivo);
        log.info("Archivo adjunto eliminado exitosamente ID: {}", idArchivo);
    }
}
