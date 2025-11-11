package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaArticulosCertificados;

import java.util.List;

/**
 * Repositorio para gestión de artículos certificados
 * Proporciona operaciones de acceso a datos para artículos certificados
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaArticulosCertificadosRepository extends JpaRepository<CjaArticulosCertificados, Long> {

    /**
     * Busca artículos por nombre (coincidencia parcial)
     */
    List<CjaArticulosCertificados> findByArticuloContainingIgnoreCase(String articulo);

    /**
     * Busca artículos activos por estado
     */
    List<CjaArticulosCertificados> findByIdEstado(String idEstado);
}
