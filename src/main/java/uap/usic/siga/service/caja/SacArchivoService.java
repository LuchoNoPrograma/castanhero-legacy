package uap.usic.siga.service.caja;

import uap.usic.siga.domain.caja.*;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestión del sistema de archivo contable
 * Define operaciones para carpetas, estantes y organización física
 *
 * @author Sistema de Archivo Contable - USIC
 */
public interface SacArchivoService {

    // ==================== ESTANTES ====================

    /**
     * Lista todos los estantes
     */
    List<SacEstantes> listarEstantes();

    /**
     * Registra un nuevo estante
     */
    SacEstantes registrarEstante(SacEstantes estante);

    /**
     * Busca un estante por código
     */
    Optional<SacEstantes> buscarEstantePorCodigo(String codigo);

    // ==================== CARPETAS ====================

    /**
     * Lista carpetas por estante
     */
    List<SacCarpetas> listarCarpetasPorEstante(Long idEstante);

    /**
     * Lista carpetas por gestión
     */
    List<SacCarpetas> listarCarpetasPorGestion(Integer gestion);

    /**
     * Registra una nueva carpeta
     */
    SacCarpetas registrarCarpeta(SacCarpetas carpeta);

    /**
     * Busca una carpeta por código
     */
    Optional<SacCarpetas> buscarCarpetaPorCodigo(String codigo);

    // ==================== CATÁLOGOS ====================

    /**
     * Lista todos los tipos de carpetas
     */
    List<SacTiposCarpetas> listarTiposCarpetas();

    /**
     * Lista todos los tipos de pagos
     */
    List<SacTiposPagos> listarTiposPagos();

    /**
     * Lista todos los tipos de comprobantes
     */
    List<SacTiposComprobantes> listarTiposComprobantes();

    /**
     * Lista todos los estados de comprobantes
     */
    List<SacEstadosComprobantes> listarEstadosComprobantes();
}
