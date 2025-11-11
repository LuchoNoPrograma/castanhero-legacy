package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacTiposComprobantes;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de tipos de comprobantes
 * Proporciona operaciones de acceso a datos para categorías de comprobantes
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacTiposComprobantesRepository extends JpaRepository<SacTiposComprobantes, Long> {

    /**
     * Busca tipos de comprobantes activos por estado
     */
    List<SacTiposComprobantes> findByIdEstado(String idEstado);

    /**
     * Busca un tipo de comprobante por nombre
     */
    Optional<SacTiposComprobantes> findBySacTipoComprobante(String sacTipoComprobante);
}
