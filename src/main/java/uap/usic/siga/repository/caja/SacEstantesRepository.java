package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacEstantes;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de estantes de archivo
 * Proporciona operaciones de acceso a datos para ubicaciones físicas
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacEstantesRepository extends JpaRepository<SacEstantes, Long> {

    /**
     * Busca estantes activos por estado
     */
    List<SacEstantes> findByIdEstado(String idEstado);

    /**
     * Busca un estante por código
     */
    Optional<SacEstantes> findBySacCodigoEstante(String sacCodigoEstante);

    /**
     * Busca estantes por número
     */
    List<SacEstantes> findBySacNumeroEstanteOrderBySacNombreEstanteAsc(Integer sacNumeroEstante);

    /**
     * Busca estantes ordenados por número
     */
    List<SacEstantes> findAllByOrderBySacNumeroEstanteAsc();
}
