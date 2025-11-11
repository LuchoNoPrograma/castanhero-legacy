package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacCompArchivosAdjuntos;

import java.util.List;

/**
 * Repositorio para gestión de archivos adjuntos de comprobantes
 * Proporciona operaciones de acceso a datos para documentos digitales
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacCompArchivosAdjuntosRepository extends JpaRepository<SacCompArchivosAdjuntos, Long> {

    /**
     * Busca archivos por comprobante
     */
    List<SacCompArchivosAdjuntos> findBySacComprobantes_IdSacComprobante(Long idSacComprobante);

    /**
     * Busca archivos por usuario
     */
    List<SacCompArchivosAdjuntos> findByUsuarios_IdUsuario(Long idUsuario);

    /**
     * Busca archivos por tipo
     */
    List<SacCompArchivosAdjuntos> findByTipoArchivo(String tipoArchivo);

    /**
     * Busca archivos por nombre (coincidencia parcial)
     */
    List<SacCompArchivosAdjuntos> findByNombreArchivoContainingIgnoreCase(String nombreArchivo);

    /**
     * Busca archivos activos por estado
     */
    List<SacCompArchivosAdjuntos> findByIdEstado(String idEstado);
}
