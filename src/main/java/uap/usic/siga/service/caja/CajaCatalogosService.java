package uap.usic.siga.service.caja;

import uap.usic.siga.domain.caja.*;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestión de catálogos de caja
 * Define operaciones para tipos, clasificaciones y proveedores
 *
 * @author Sistema de Caja - USIC
 */
public interface CajaCatalogosService {

    // ==================== TIPOS DE INGRESOS ====================

    /**
     * Lista todos los tipos de ingresos activos
     */
    List<CjaTiposIngresos> listarTiposIngresos();

    /**
     * Registra un nuevo tipo de ingreso
     */
    CjaTiposIngresos registrarTipoIngreso(CjaTiposIngresos tipoIngreso);

    // ==================== TIPOS DE GASTOS ====================

    /**
     * Lista todos los tipos de gastos activos
     */
    List<CjaTiposGastos> listarTiposGastos();

    /**
     * Registra un nuevo tipo de gasto
     */
    CjaTiposGastos registrarTipoGasto(CjaTiposGastos tipoGasto);

    /**
     * Busca tipos de gastos por clasificación
     */
    List<CjaTiposGastos> listarTiposGastosPorClasificacion(Long idClasificacion);

    // ==================== CLASIFICACIONES ====================

    /**
     * Lista todos los tipos de clasificaciones
     */
    List<CjaTiposClasificaciones> listarTiposClasificaciones();

    /**
     * Lista todas las clasificaciones de gastos
     */
    List<CjaGastosClasificaciones> listarGastosClasificaciones();

    /**
     * Busca clasificaciones por tipo
     */
    List<CjaGastosClasificaciones> listarClasificacionesPorTipo(Long idTipoClasificacion);

    // ==================== PROVEEDORES ====================

    /**
     * Lista todos los proveedores activos
     */
    List<CjaProveedores> listarProveedores();

    /**
     * Registra un nuevo proveedor
     */
    CjaProveedores registrarProveedor(CjaProveedores proveedor);

    /**
     * Busca un proveedor por ID
     */
    Optional<CjaProveedores> buscarProveedorPorId(Long idProveedor);

    /**
     * Busca proveedores por nombre
     */
    List<CjaProveedores> buscarProveedoresPorNombre(String nombre);
}
