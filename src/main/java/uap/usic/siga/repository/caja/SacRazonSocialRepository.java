package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacRazonSocial;

import java.util.List;

/**
 * Repositorio para gestión de razones sociales
 * Proporciona operaciones de acceso a datos para entidades asociadas a comprobantes
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacRazonSocialRepository extends JpaRepository<SacRazonSocial, Long> {

    /**
     * Busca razones sociales por comprobante
     */
    List<SacRazonSocial> findBySacComprobantes_IdSacComprobante(Long idSacComprobante);

    /**
     * Busca razones sociales por persona
     */
    List<SacRazonSocial> findByPersonas_IdPersona(Long idPersona);

    /**
     * Busca razones sociales activas por estado
     */
    List<SacRazonSocial> findByIdEstado(String idEstado);
}
