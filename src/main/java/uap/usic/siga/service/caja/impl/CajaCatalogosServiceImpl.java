package uap.usic.siga.service.caja.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.caja.*;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.caja.*;
import uap.usic.siga.service.caja.CajaCatalogosService;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de catálogos de caja
 * Proporciona lógica de negocio para tipos, clasificaciones y proveedores
 *
 * @author Sistema de Caja - USIC
 */
@Slf4j
@Service("cajaCatalogosService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CajaCatalogosServiceImpl implements CajaCatalogosService {

    private final CjaTiposIngresosRepository tiposIngresosRepository;
    private final CjaTiposGastosRepository tiposGastosRepository;
    private final CjaTiposClasificacionesRepository tiposClasificacionesRepository;
    private final CjaGastosClasificacionesRepository gastosClasificacionesRepository;
    private final CjaProveedoresRepository proveedoresRepository;

    // ==================== TIPOS DE INGRESOS ====================

    @Override
    public List<CjaTiposIngresos> listarTiposIngresos() {
        log.debug("Listando todos los tipos de ingresos activos");
        return tiposIngresosRepository.findByIdEstado("A");
    }

    @Override
    @Transactional
    public CjaTiposIngresos registrarTipoIngreso(CjaTiposIngresos tipoIngreso) {
        log.info("Registrando nuevo tipo de ingreso: {}", tipoIngreso.getDescripcion());

        if (tipoIngreso == null) {
            throw new ValidacionException("El tipo de ingreso no puede ser nulo");
        }

        if (tipoIngreso.getDescripcion() == null || tipoIngreso.getDescripcion().trim().isEmpty()) {
            throw new ValidacionException("La descripción del tipo de ingreso es obligatoria");
        }

        // Establecer estado activo por defecto
        if (tipoIngreso.getIdEstado() == null) {
            tipoIngreso.setIdEstado("A");
        }

        CjaTiposIngresos tipoGuardado = tiposIngresosRepository.save(tipoIngreso);
        log.info("Tipo de ingreso registrado exitosamente con ID: {}", tipoGuardado.getIdTipoIngreso());

        return tipoGuardado;
    }

    // ==================== TIPOS DE GASTOS ====================

    @Override
    public List<CjaTiposGastos> listarTiposGastos() {
        log.debug("Listando todos los tipos de gastos activos");
        return tiposGastosRepository.findByIdEstado("A");
    }

    @Override
    @Transactional
    public CjaTiposGastos registrarTipoGasto(CjaTiposGastos tipoGasto) {
        log.info("Registrando nuevo tipo de gasto: {}", tipoGasto.getDescripcion());

        if (tipoGasto == null) {
            throw new ValidacionException("El tipo de gasto no puede ser nulo");
        }

        if (tipoGasto.getDescripcion() == null || tipoGasto.getDescripcion().trim().isEmpty()) {
            throw new ValidacionException("La descripción del tipo de gasto es obligatoria");
        }

        // Establecer estado activo por defecto
        if (tipoGasto.getIdEstado() == null) {
            tipoGasto.setIdEstado("A");
        }

        CjaTiposGastos tipoGuardado = tiposGastosRepository.save(tipoGasto);
        log.info("Tipo de gasto registrado exitosamente con ID: {}", tipoGuardado.getIdTipoGasto());

        return tipoGuardado;
    }

    @Override
    public List<CjaTiposGastos> listarTiposGastosPorClasificacion(Long idClasificacion) {
        log.debug("Listando tipos de gastos por clasificación ID: {}", idClasificacion);

        if (idClasificacion == null) {
            throw new ValidacionException("El ID de la clasificación es requerido");
        }

        return tiposGastosRepository
                .findByCjaGastosClasificaciones_IdClasificacionAndIdEstado(idClasificacion, "A");
    }

    // ==================== CLASIFICACIONES ====================

    @Override
    public List<CjaTiposClasificaciones> listarTiposClasificaciones() {
        log.debug("Listando todos los tipos de clasificaciones");
        return tiposClasificacionesRepository.findAll();
    }

    @Override
    public List<CjaGastosClasificaciones> listarGastosClasificaciones() {
        log.debug("Listando todas las clasificaciones de gastos activas");
        return gastosClasificacionesRepository.findByIdEstado("A");
    }

    @Override
    public List<CjaGastosClasificaciones> listarClasificacionesPorTipo(Long idTipoClasificacion) {
        log.debug("Listando clasificaciones por tipo ID: {}", idTipoClasificacion);

        if (idTipoClasificacion == null) {
            throw new ValidacionException("El ID del tipo de clasificación es requerido");
        }

        return gastosClasificacionesRepository
                .findByCjaTiposClasificaciones_IdTipoClasificacionAndIdEstado(
                        idTipoClasificacion, "A");
    }

    // ==================== PROVEEDORES ====================

    @Override
    public List<CjaProveedores> listarProveedores() {
        log.debug("Listando todos los proveedores activos");
        return proveedoresRepository.findByIdEstado("A");
    }

    @Override
    @Transactional
    public CjaProveedores registrarProveedor(CjaProveedores proveedor) {
        log.info("Registrando nuevo proveedor: {}", proveedor.getNombre());

        if (proveedor == null) {
            throw new ValidacionException("El proveedor no puede ser nulo");
        }

        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
            throw new ValidacionException("El nombre del proveedor es obligatorio");
        }

        // Establecer estado activo por defecto
        if (proveedor.getIdEstado() == null) {
            proveedor.setIdEstado("A");
        }

        CjaProveedores proveedorGuardado = proveedoresRepository.save(proveedor);
        log.info("Proveedor registrado exitosamente con ID: {}", proveedorGuardado.getIdProveedor());

        return proveedorGuardado;
    }

    @Override
    public Optional<CjaProveedores> buscarProveedorPorId(Long idProveedor) {
        log.debug("Buscando proveedor por ID: {}", idProveedor);

        if (idProveedor == null) {
            throw new ValidacionException("El ID del proveedor no puede ser nulo");
        }

        return proveedoresRepository.findById(idProveedor);
    }

    @Override
    public List<CjaProveedores> buscarProveedoresPorNombre(String nombre) {
        log.debug("Buscando proveedores por nombre: {}", nombre);

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ValidacionException("El nombre del proveedor no puede estar vacío");
        }

        return proveedoresRepository.findByNombreContainingIgnoreCaseAndIdEstado(nombre, "A");
    }
}
