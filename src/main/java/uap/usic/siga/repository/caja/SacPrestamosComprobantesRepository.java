package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacPrestamosComprobantes;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para gestión de préstamos de comprobantes
 * Proporciona operaciones de acceso a datos para control de préstamos
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacPrestamosComprobantesRepository extends JpaRepository<SacPrestamosComprobantes, Long> {

    /**
     * Busca préstamos por persona
     */
    List<SacPrestamosComprobantes> findByPersonas_IdPersona(Long idPersona);

    /**
     * Busca préstamos por usuario
     */
    List<SacPrestamosComprobantes> findByUsuarios_IdUsuario(Long idUsuario);

    /**
     * Busca préstamos por rango de fechas
     */
    List<SacPrestamosComprobantes> findByFecPrestamoBetween(Date fecInicio, Date fecFin);

    /**
     * Busca préstamos por gestión
     */
    List<SacPrestamosComprobantes> findByGestionPrestamoOrderByFecPrestamoDesc(Integer gestionPrestamo);

    /**
     * Busca préstamos activos (no devueltos)
     */
    List<SacPrestamosComprobantes> findByPrestamoTrue();

    /**
     * Busca préstamos por persona y estado activo
     */
    List<SacPrestamosComprobantes> findByPersonas_IdPersonaAndPrestamoTrue(Long idPersona);
}
