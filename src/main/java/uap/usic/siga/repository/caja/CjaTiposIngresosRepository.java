package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaTiposIngresos;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de tipos de ingresos
 * Proporciona operaciones de acceso a datos para tipos de ingresos
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaTiposIngresosRepository extends JpaRepository<CjaTiposIngresos, Long> {

    /**
     * Busca tipos de ingresos activos por estado
     */
    List<CjaTiposIngresos> findByIdEstado(String idEstado);

    /**
     * Busca un tipo de ingreso por nombre
     */
    Optional<CjaTiposIngresos> findByTipoIngreso(String tipoIngreso);
}
