package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaTiposClasificaciones;

import java.util.List;

/**
 * Repositorio para gestión de tipos de clasificaciones
 * Proporciona operaciones de acceso a datos para tipos de clasificaciones de gastos
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaTiposClasificacionesRepository extends JpaRepository<CjaTiposClasificaciones, Long> {

    /**
     * Busca tipos de clasificaciones activos por estado
     */
    List<CjaTiposClasificaciones> findByIdEstado(String idEstado);

    /**
     * Busca tipos de clasificaciones ordenados por número
     */
    List<CjaTiposClasificaciones> findAllByOrderByNroTipoClasificacionAsc();
}
