package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaGastosClasificaciones;

import java.util.List;

/**
 * Repositorio para gestión de clasificaciones de gastos
 * Proporciona operaciones de acceso a datos para clasificaciones de gastos
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaGastosClasificacionesRepository extends JpaRepository<CjaGastosClasificaciones, Long> {

    /**
     * Busca clasificaciones de gastos por tipo de clasificación
     */
    List<CjaGastosClasificaciones> findByCjaTiposClasificaciones_IdCjaTipoClasificacion(Long idCjaTipoClasificacion);

    /**
     * Busca clasificaciones activas por estado
     */
    List<CjaGastosClasificaciones> findByIdEstado(String idEstado);

    /**
     * Busca clasificaciones ordenadas por número
     */
    List<CjaGastosClasificaciones> findAllByOrderByNroGastoClasificacionAsc();
}
