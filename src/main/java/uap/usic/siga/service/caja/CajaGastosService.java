package uap.usic.siga.service.caja;

import uap.usic.siga.domain.caja.CjaGastosEjecutados;
import uap.usic.siga.domain.caja.CjaTiposGastos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestión de gastos ejecutados
 * Define operaciones de negocio para registro y control de gastos
 *
 * @author Sistema de Caja - USIC
 */
public interface CajaGastosService {

    /**
     * Registra un nuevo gasto ejecutado
     */
    CjaGastosEjecutados registrarGasto(CjaGastosEjecutados gasto);

    /**
     * Actualiza un gasto existente
     */
    CjaGastosEjecutados actualizarGasto(CjaGastosEjecutados gasto);

    /**
     * Busca un gasto por ID
     */
    Optional<CjaGastosEjecutados> buscarGastoPorId(Long idGasto);

    /**
     * Lista gastos por ingreso de caja y usuario
     */
    List<CjaGastosEjecutados> listarGastosPorIngresoUsuario(Long idIngreso, Long idUsuario);

    /**
     * Lista gastos por periodo, gestión y usuario
     */
    List<CjaGastosEjecutados> listarGastosPorPeriodoGestionUsuario(
            Integer periodo, Integer gestion, Long idUsuario);

    /**
     * Lista gastos por ingreso y rango de fechas
     */
    List<CjaGastosEjecutados> listarGastosPorIngresoFechas(
            Long idIngreso, Date fecInicio, Date fecFin);

    /**
     * Lista gastos por persona, gestión, periodo y usuario
     */
    List<CjaGastosEjecutados> listarGastosPorPersonaGestionPeriodo(
            Long idPersona, Integer gestion, Integer periodo, Long idUsuario, Long idIngreso);

    /**
     * Cuenta gastos por ingreso y estado
     */
    Long contarGastosPorIngresoEstado(Long idIngreso, String idEstado);

    /**
     * Calcula el total de gastos de un ingreso
     */
    Double calcularTotalGastosPorIngreso(Long idIngreso, String idEstado);

    /**
     * Busca el tipo de gasto asociado a un gasto ejecutado
     */
    Optional<CjaTiposGastos> buscarTipoGastoPorGastoEjecutado(Long idGastoEjecutado);

    /**
     * Valida si un gasto puede ser registrado
     */
    boolean validarGasto(CjaGastosEjecutados gasto);
}
