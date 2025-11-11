package uap.usic.siga.service.caja.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.caja.CjaGastosEjecutados;
import uap.usic.siga.domain.caja.CjaTiposGastos;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.caja.CjaGastosEjecutadosRepository;
import uap.usic.siga.repository.caja.CjaTiposGastosRepository;
import uap.usic.siga.service.caja.CajaGastosService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de gastos ejecutados
 * Proporciona lógica de negocio para registro y control de gastos
 *
 * @author Sistema de Caja - USIC
 */
@Slf4j
@Service("cajaGastosService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CajaGastosServiceImpl implements CajaGastosService {

    private final CjaGastosEjecutadosRepository gastosEjecutadosRepository;
    private final CjaTiposGastosRepository tiposGastosRepository;

    @Override
    @Transactional
    public CjaGastosEjecutados registrarGasto(CjaGastosEjecutados gasto) {
        log.info("Registrando nuevo gasto para ingreso ID: {}",
                gasto.getCjaIngresos() != null ? gasto.getCjaIngresos().getIdCjaIngreso() : null);

        validarGasto(gasto);

        // Establecer estado activo por defecto
        if (gasto.getIdEstado() == null) {
            gasto.setIdEstado("I");
        }

        // Establecer fecha de registro
        if (gasto.getFecRegistro() == null) {
            gasto.setFecRegistro(new Date());
        }

        CjaGastosEjecutados gastoGuardado = gastosEjecutadosRepository.save(gasto);
        log.info("Gasto registrado exitosamente con ID: {}", gastoGuardado.getIdGastoEjecutado());

        return gastoGuardado;
    }

    @Override
    @Transactional
    public CjaGastosEjecutados actualizarGasto(CjaGastosEjecutados gasto) {
        log.info("Actualizando gasto ID: {}", gasto.getIdGastoEjecutado());

        if (gasto.getIdGastoEjecutado() == null) {
            throw new ValidacionException("El ID del gasto es requerido para actualizar");
        }

        CjaGastosEjecutados gastoExistente = gastosEjecutadosRepository
                .findById(gasto.getIdGastoEjecutado())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Gasto no encontrado con ID: " + gasto.getIdGastoEjecutado()));

        validarGasto(gasto);

        CjaGastosEjecutados gastoActualizado = gastosEjecutadosRepository.save(gasto);
        log.info("Gasto actualizado exitosamente ID: {}", gastoActualizado.getIdGastoEjecutado());

        return gastoActualizado;
    }

    @Override
    public Optional<CjaGastosEjecutados> buscarGastoPorId(Long idGasto) {
        log.debug("Buscando gasto por ID: {}", idGasto);

        if (idGasto == null) {
            throw new ValidacionException("El ID del gasto no puede ser nulo");
        }

        return gastosEjecutadosRepository.findById(idGasto);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastosPorIngresoUsuario(Long idIngreso, Long idUsuario) {
        log.debug("Listando gastos por ingreso: {} y usuario: {}", idIngreso, idUsuario);

        if (idIngreso == null || idUsuario == null) {
            throw new ValidacionException("El ID del ingreso y usuario son requeridos");
        }

        return gastosEjecutadosRepository
                .findByCjaIngresos_IdCjaIngresoAndUsuarios_IdUsuario(idIngreso, idUsuario);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastosPorPeriodoGestionUsuario(
            Integer periodo, Integer gestion, Long idUsuario) {
        log.debug("Listando gastos por periodo: {}, gestión: {}, usuario: {}",
                periodo, gestion, idUsuario);

        if (periodo == null || gestion == null || idUsuario == null) {
            throw new ValidacionException("El periodo, gestión y usuario son requeridos");
        }

        return gastosEjecutadosRepository
                .findByPeriodoAndGestionAndUsuarios_IdUsuario(periodo, gestion, idUsuario);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastosPorIngresoFechas(
            Long idIngreso, Date fecInicio, Date fecFin) {
        log.debug("Listando gastos por ingreso: {} entre fechas: {} - {}",
                idIngreso, fecInicio, fecFin);

        if (idIngreso == null) {
            throw new ValidacionException("El ID del ingreso es requerido");
        }

        if (fecInicio == null || fecFin == null) {
            throw new ValidacionException("Las fechas de inicio y fin son requeridas");
        }

        return gastosEjecutadosRepository
                .findByCjaIngresos_IdCjaIngresoAndFecGastoBetween(idIngreso, fecInicio, fecFin);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastosPorPersonaGestionPeriodo(
            Long idPersona, Integer gestion, Integer periodo, Long idUsuario, Long idIngreso) {
        log.debug("Listando gastos por persona: {}, gestión: {}, periodo: {}, usuario: {}, ingreso: {}",
                idPersona, gestion, periodo, idUsuario, idIngreso);

        if (idPersona == null) {
            throw new ValidacionException("El ID de la persona es requerido");
        }

        return gastosEjecutadosRepository
                .findByPersonas_IdPersonaAndGestionAndPeriodoAndUsuarios_IdUsuarioAndCjaIngresos_IdCjaIngreso(
                        idPersona, gestion, periodo, idUsuario, idIngreso);
    }

    @Override
    public Long contarGastosPorIngresoEstado(Long idIngreso, String idEstado) {
        log.debug("Contando gastos por ingreso: {} y estado: {}", idIngreso, idEstado);

        if (idIngreso == null) {
            throw new ValidacionException("El ID del ingreso es requerido");
        }

        if (idEstado == null || idEstado.trim().isEmpty()) {
            throw new ValidacionException("El estado es requerido");
        }

        return gastosEjecutadosRepository
                .countByCjaIngresos_IdCjaIngresoAndIdEstado(idIngreso, idEstado);
    }

    @Override
    public Double calcularTotalGastosPorIngreso(Long idIngreso, String idEstado) {
        log.debug("Calculando total de gastos por ingreso: {} y estado: {}", idIngreso, idEstado);

        if (idIngreso == null) {
            throw new ValidacionException("El ID del ingreso es requerido");
        }

        Double total = gastosEjecutadosRepository
                .sumTotalGastoByCjaIngresoAndEstado(idIngreso, idEstado);

        return total != null ? total : 0.0;
    }

    @Override
    public Optional<CjaTiposGastos> buscarTipoGastoPorGastoEjecutado(Long idGastoEjecutado) {
        log.debug("Buscando tipo de gasto por gasto ejecutado ID: {}", idGastoEjecutado);

        if (idGastoEjecutado == null) {
            throw new ValidacionException("El ID del gasto ejecutado es requerido");
        }

        return gastosEjecutadosRepository.findById(idGastoEjecutado)
                .map(CjaGastosEjecutados::getCjaTiposGastos);
    }

    @Override
    public boolean validarGasto(CjaGastosEjecutados gasto) {
        if (gasto == null) {
            throw new ValidacionException("El gasto no puede ser nulo");
        }

        if (gasto.getMonto() == null || gasto.getMonto() <= 0) {
            throw new ValidacionException("El monto del gasto debe ser mayor a cero");
        }

        if (gasto.getFecGasto() == null) {
            throw new ValidacionException("La fecha del gasto es obligatoria");
        }

        if (gasto.getCjaIngresos() == null) {
            throw new ValidacionException("El ingreso asociado al gasto es obligatorio");
        }

        if (gasto.getCjaTiposGastos() == null) {
            throw new ValidacionException("El tipo de gasto es obligatorio");
        }

        if (gasto.getPersonas() == null) {
            throw new ValidacionException("La persona asociada al gasto es obligatoria");
        }

        return true;
    }
}
