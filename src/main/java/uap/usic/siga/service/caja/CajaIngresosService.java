package uap.usic.siga.service.caja;

import uap.usic.siga.domain.caja.CjaIngresos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestión de ingresos de caja
 * Define operaciones de negocio para manejo de fondos ingresados
 *
 * @author Sistema de Caja - USIC
 */
public interface CajaIngresosService {

    /**
     * Registra un nuevo ingreso de fondos
     */
    CjaIngresos registrarIngreso(CjaIngresos ingreso);

    /**
     * Actualiza un ingreso existente
     */
    CjaIngresos actualizarIngreso(CjaIngresos ingreso);

    /**
     * Actualiza el estado de un ingreso
     */
    void actualizarEstadoIngreso(Long idIngreso, String nuevoEstado);

    /**
     * Actualiza el saldo de un ingreso
     */
    void actualizarSaldoIngreso(Long idIngreso, Double nuevoSaldo);

    /**
     * Elimina un ingreso (soft delete)
     */
    void eliminarIngreso(Long idIngreso);

    /**
     * Busca un ingreso por ID
     */
    Optional<CjaIngresos> buscarPorId(Long idIngreso);

    /**
     * Lista ingresos por persona
     */
    List<CjaIngresos> listarIngresosPorPersona(Long idPersona);

    /**
     * Busca ingreso por persona, gestión, periodo y estado
     */
    Optional<CjaIngresos> buscarIngresoPorPersonaGestionPeriodoEstado(
            Long idPersona, Integer gestion, Integer periodo, String idEstado);

    /**
     * Busca el saldo disponible de ingresos para una persona
     */
    Optional<CjaIngresos> buscarSaldoIngresosPorPersona(
            Long idPersona, Integer gestion, Integer periodo, String idEstado);

    /**
     * Busca el último ingreso activo de una persona
     */
    Optional<CjaIngresos> buscarUltimoIngresoPorPersona(Long idPersona, String idEstado);

    /**
     * Lista ingresos por unidad funcional y gestión
     */
    List<CjaIngresos> listarIngresosPorUnidadFuncionalGestion(
            Long idUnidadFuncional, Integer gestion);

    /**
     * Verifica si hay fondos suficientes en un ingreso
     */
    boolean verificarFondosDisponibles(Long idIngreso, Double montoRequerido);

    /**
     * Calcula el saldo disponible de un ingreso
     */
    Double calcularSaldoDisponible(Long idIngreso);
}
