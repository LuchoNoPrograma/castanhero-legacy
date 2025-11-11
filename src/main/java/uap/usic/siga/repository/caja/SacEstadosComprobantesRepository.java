package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacEstadosComprobantes;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de estados de comprobantes
 * Proporciona operaciones de acceso a datos para estados del ciclo de vida
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacEstadosComprobantesRepository extends JpaRepository<SacEstadosComprobantes, Long> {

    /**
     * Busca estados activos por estado
     */
    List<SacEstadosComprobantes> findByIdEstado(String idEstado);

    /**
     * Busca un estado por nombre
     */
    Optional<SacEstadosComprobantes> findBySacNombreEstado(String sacNombreEstado);
}
