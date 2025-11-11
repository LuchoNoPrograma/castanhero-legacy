package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacDevolucionesComprobantes;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para gestión de devoluciones de comprobantes
 * Proporciona operaciones de acceso a datos para control de devoluciones
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacDevolucionesComprobantesRepository extends JpaRepository<SacDevolucionesComprobantes, Long> {

    /**
     * Busca devoluciones por persona
     */
    List<SacDevolucionesComprobantes> findByPersonas_IdPersona(Long idPersona);

    /**
     * Busca devoluciones por usuario
     */
    List<SacDevolucionesComprobantes> findByUsuarios_IdUsuario(Long idUsuario);

    /**
     * Busca devoluciones por rango de fechas
     */
    List<SacDevolucionesComprobantes> findByFecDevolucionBetween(Date fecInicio, Date fecFin);

    /**
     * Busca devoluciones por gestión
     */
    List<SacDevolucionesComprobantes> findByGestionDevolucionOrderByFecDevolucionDesc(Integer gestionDevolucion);

    /**
     * Busca devoluciones por persona y gestión
     */
    List<SacDevolucionesComprobantes> findByPersonas_IdPersonaAndGestionDevolucion(
            Long idPersona, Integer gestionDevolucion);
}
