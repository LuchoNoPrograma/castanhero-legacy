package uap.usic.siga.service.caja;

import uap.usic.siga.dto.CjaDireccionesFuncionalesResponse;
import uap.usic.siga.dto.CjaGastosClasificacionesResponse;
import uap.usic.siga.dto.CjaGastosEjecutadosResponse;
import uap.usic.siga.dto.CjaTiposClasificacionesResponse;
import uap.usic.siga.dto.CjaUnidadesFuncionalesResponse;

import java.util.Date;
import java.util.List;

/**
 * Servicio para generación de reportes de caja
 * Define operaciones para consultas complejas y reportes financieros
 *
 * @author Sistema de Caja - USIC
 */
public interface CajaReportesService {

    /**
     * Genera reporte de gastos ejecutados por ID
     */
    CjaGastosEjecutadosResponse generarReporteGastoEjecutado(Long idGastoEjecutado);

    /**
     * Lista tipos de clasificaciones con totales por usuario e ingreso
     */
    List<CjaTiposClasificacionesResponse> listarTiposClasificacionConTotales(
            Long idUsuario, Long idIngreso, Date fecIngreso, Date fecGasto);

    /**
     * Lista gastos por clasificación y caja de ingreso
     */
    List<CjaGastosClasificacionesResponse> listarGastosClasificacionPorIngreso(
            Long idUsuario, Long idIngreso);

    /**
     * Lista totales de gastos por unidades funcionales
     */
    List<CjaUnidadesFuncionalesResponse> listarTotalesGastosPorUnidadesFuncionales(
            Long idDireccionFuncional, Integer gestion);

    /**
     * Lista totales de gastos por direcciones funcionales
     */
    List<CjaDireccionesFuncionalesResponse> listarTotalesGastosPorDireccionesFuncionales(
            Long idSede, Integer gestion);

    /**
     * Lista tipos de clasificación con totales por ingreso
     */
    List<CjaTiposClasificacionesResponse> listarTiposClasificacionPorIngreso(Long idIngreso);

    /**
     * Lista totales de gastos por tipo de clasificación y unidad funcional
     */
    List<CjaTiposClasificacionesResponse> listarTotalesGastosPorTipoClasificacionUnidad(
            Long idUnidadFuncional, Integer gestion);

    /**
     * Lista totales de gastos por partidas y direcciones funcionales
     */
    List<CjaTiposClasificacionesResponse> listarTotalesGastosPorPartidasDirecciones(
            Long idDireccionFuncional, Integer gestion);

    /**
     * Lista totales generales de gastos por partida y sede
     */
    List<CjaTiposClasificacionesResponse> listarTotalesGeneralesGastosPorPartidaSede(
            Long idSede, Integer gestion);

    /**
     * Busca el monto máximo por sede y gestión
     */
    List<CjaTiposClasificacionesResponse> buscarMontoMaximoPorSede(Long idSede, Integer gestion);
}
