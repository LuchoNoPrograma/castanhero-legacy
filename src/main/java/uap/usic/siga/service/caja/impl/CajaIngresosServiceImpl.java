package uap.usic.siga.service.caja.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.caja.CjaIngresos;
import uap.usic.siga.repository.caja.CjaIngresosRepository;
import uap.usic.siga.repository.caja.CjaGastosEjecutadosRepository;
import uap.usic.siga.service.caja.CajaIngresosService;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de ingresos de caja
 * Proporciona lógica de negocio para manejo de fondos
 *
 * @author Sistema de Caja - USIC
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CajaIngresosServiceImpl implements CajaIngresosService {

    private final CjaIngresosRepository ingresosRepository;
    private final CjaGastosEjecutadosRepository gastosRepository;

    @Override
    public CjaIngresos registrarIngreso(CjaIngresos ingreso) {
        log.info("Registrando nuevo ingreso para persona ID: {}", ingreso.getPersonas().getIdPersona());

        // Validar datos del ingreso
        validarIngreso(ingreso);

        // Inicializar saldo con el monto del ingreso
        if (ingreso.getSaldo() == null) {
            ingreso.setSaldo(ingreso.getMonto());
        }

        // Establecer estado activo por defecto
        if (ingreso.getIdEstado() == null) {
            ingreso.setIdEstado("I");
        }

        CjaIngresos ingresoGuardado = ingresosRepository.save(ingreso);
        log.info("Ingreso registrado exitosamente con ID: {}", ingresoGuardado.getIdCjaIngreso());

        return ingresoGuardado;
    }

    @Override
    public CjaIngresos actualizarIngreso(CjaIngresos ingreso) {
        log.info("Actualizando ingreso ID: {}", ingreso.getIdCjaIngreso());

        // Verificar que el ingreso existe
        CjaIngresos ingresoExistente = ingresosRepository.findById(ingreso.getIdCjaIngreso())
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + ingreso.getIdCjaIngreso()));

        return ingresosRepository.save(ingreso);
    }

    @Override
    public void actualizarEstadoIngreso(Long idIngreso, String nuevoEstado) {
        log.info("Actualizando estado del ingreso ID: {} a estado: {}", idIngreso, nuevoEstado);

        CjaIngresos ingreso = ingresosRepository.findById(idIngreso)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + idIngreso));

        ingreso.setIdEstado(nuevoEstado);
        ingresosRepository.save(ingreso);
    }

    @Override
    public void actualizarSaldoIngreso(Long idIngreso, Double nuevoSaldo) {
        log.info("Actualizando saldo del ingreso ID: {} a: {}", idIngreso, nuevoSaldo);

        CjaIngresos ingreso = ingresosRepository.findById(idIngreso)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + idIngreso));

        ingreso.setSaldo(nuevoSaldo);

        // Calcular porcentaje de gasto y saldo
        if (ingreso.getMonto() != null && ingreso.getMonto() > 0) {
            Double porcentajeGasto = ((ingreso.getMonto() - nuevoSaldo) / ingreso.getMonto()) * 100;
            Double porcentajeSaldo = (nuevoSaldo / ingreso.getMonto()) * 100;

            ingreso.setPorcentajeGasto(porcentajeGasto);
            ingreso.setPorcentajeSaldo(porcentajeSaldo);
        }

        ingresosRepository.save(ingreso);
    }

    @Override
    public void eliminarIngreso(Long idIngreso) {
        log.info("Eliminando ingreso ID: {}", idIngreso);

        CjaIngresos ingreso = ingresosRepository.findById(idIngreso)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + idIngreso));

        // Soft delete - cambiar estado a inactivo
        ingreso.setIdEstado("X");
        ingresosRepository.save(ingreso);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CjaIngresos> buscarPorId(Long idIngreso) {
        log.debug("Buscando ingreso por ID: {}", idIngreso);
        return ingresosRepository.findById(idIngreso);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CjaIngresos> listarIngresosPorPersona(Long idPersona) {
        log.debug("Listando ingresos para persona ID: {}", idPersona);
        return ingresosRepository.findByPersonas_IdPersona(idPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CjaIngresos> buscarIngresoPorPersonaGestionPeriodoEstado(
            Long idPersona, Integer gestion, Integer periodo, String idEstado) {
        log.debug("Buscando ingreso para persona: {}, gestión: {}, periodo: {}, estado: {}",
                idPersona, gestion, periodo, idEstado);

        return ingresosRepository.findByPersonas_IdPersonaAndGestionAndPeriodoAndIdEstado(
                idPersona, gestion, periodo, idEstado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CjaIngresos> buscarSaldoIngresosPorPersona(
            Long idPersona, Integer gestion, Integer periodo, String idEstado) {
        log.debug("Buscando saldo de ingresos para persona: {}, gestión: {}, periodo: {}",
                idPersona, gestion, periodo);

        return ingresosRepository.findByPersonas_IdPersonaAndGestionAndPeriodoAndIdEstado(
                idPersona, gestion, periodo, idEstado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CjaIngresos> buscarUltimoIngresoPorPersona(Long idPersona, String idEstado) {
        log.debug("Buscando último ingreso para persona: {}, estado: {}", idPersona, idEstado);
        return ingresosRepository.findFirstByPersonas_IdPersonaAndIdEstadoOrderByIdCjaIngresoDesc(
                idPersona, idEstado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CjaIngresos> listarIngresosPorUnidadFuncionalGestion(
            Long idUnidadFuncional, Integer gestion) {
        log.debug("Listando ingresos por unidad funcional: {}, gestión: {}",
                idUnidadFuncional, gestion);

        return ingresosRepository.findByUnidadFuncionalAndGestion(idUnidadFuncional, gestion);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean verificarFondosDisponibles(Long idIngreso, Double montoRequerido) {
        log.debug("Verificando fondos disponibles para ingreso: {}, monto requerido: {}",
                idIngreso, montoRequerido);

        Double saldoDisponible = calcularSaldoDisponible(idIngreso);
        return saldoDisponible != null && saldoDisponible >= montoRequerido;
    }

    @Override
    @Transactional(readOnly = true)
    public Double calcularSaldoDisponible(Long idIngreso) {
        log.debug("Calculando saldo disponible para ingreso: {}", idIngreso);

        CjaIngresos ingreso = ingresosRepository.findById(idIngreso)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado con ID: " + idIngreso));

        Double montoInicial = ingreso.getMonto();
        Double totalGastado = gastosRepository.sumTotalGastoByCjaIngresoAndEstado(idIngreso, "I");

        if (totalGastado == null) {
            totalGastado = 0.0;
        }

        return montoInicial - totalGastado;
    }

    /**
     * Valida los datos del ingreso antes de registrarlo
     */
    private void validarIngreso(CjaIngresos ingreso) {
        if (ingreso.getMonto() == null || ingreso.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto del ingreso debe ser mayor a cero");
        }

        if (ingreso.getFecIngreso() == null) {
            throw new IllegalArgumentException("La fecha de ingreso es obligatoria");
        }

        if (ingreso.getPersonas() == null) {
            throw new IllegalArgumentException("La persona asociada al ingreso es obligatoria");
        }

        if (ingreso.getCjaTiposIngresos() == null) {
            throw new IllegalArgumentException("El tipo de ingreso es obligatorio");
        }
    }
}
