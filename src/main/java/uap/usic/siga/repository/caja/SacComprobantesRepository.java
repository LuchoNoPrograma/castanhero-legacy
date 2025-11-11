package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacComprobantes;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para gestión de comprobantes contables
 * Proporciona operaciones de acceso a datos para comprobantes
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacComprobantesRepository extends JpaRepository<SacComprobantes, Long> {

    /**
     * Busca comprobantes por carpeta
     */
    List<SacComprobantes> findBySacCarpetas_IdSacCarpeta(Long idSacCarpeta);

    /**
     * Busca comprobantes por tipo de comprobante
     */
    List<SacComprobantes> findBySacTiposComprobantes_IdSacTipoComprobante(Long idSacTipoComprobante);

    /**
     * Busca comprobantes por tipo de pago
     */
    List<SacComprobantes> findBySacTiposPagos_IdSacTipoPago(Long idSacTipoPago);

    /**
     * Busca comprobantes por rango de fechas
     */
    List<SacComprobantes> findByFecElaboracionBetween(Date fecInicio, Date fecFin);

    /**
     * Busca comprobantes por gestión
     */
    List<SacComprobantes> findByGestionOrderByFecElaboracionDesc(Integer gestion);

    /**
     * Busca comprobantes por hoja de ruta
     */
    List<SacComprobantes> findByHojaRuta(Integer hojaRuta);

    /**
     * Busca comprobantes por usuario y gestión
     */
    List<SacComprobantes> findByUsuarios_IdUsuarioAndGestion(Long idUsuario, Integer gestion);

    /**
     * Calcula total de montos por gestión
     */
    @Query("SELECT SUM(c.sacMonto) FROM SacComprobantes c " +
           "WHERE c.gestion = :gestion AND c.idEstado = :idEstado")
    Double sumMontoByGestionAndEstado(
            @Param("gestion") Integer gestion,
            @Param("idEstado") String idEstado);
}
