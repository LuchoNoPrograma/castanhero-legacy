package uap.usic.siga.service.electoral.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.electoral.*;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.electoral.*;
import uap.usic.siga.service.electoral.ElectoralService;

import java.util.List;

/**
 * Implementación del servicio de gestión electoral
 * Proporciona lógica de negocio para elecciones, frentes y escrutinio
 *
 * @author Sistema Electoral - USIC
 */
@Slf4j
@Service("electoralService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ElectoralServiceImpl implements ElectoralService {

    private final EscEleccionesRepository eleccionesRepository;
    private final EscFrentesRepository frentesRepository;
    private final EscMesasHabilitadasRepository mesasHabilitadasRepository;
    private final EscrutinioActasRepository escrutinioActasRepository;
    private final EscDetallesRepository detallesRepository;
    private final EscResultadosGraficosRepository resultadosGraficosRepository;

    // ==================== ELECCIONES ====================

    @Override
    public List<EscElecciones> listarElecciones() {
        log.debug("Listando todas las elecciones");
        return eleccionesRepository.findAll();
    }

    @Override
    @Transactional
    public EscElecciones guardarEleccion(EscElecciones eleccion) {
        log.info("Guardando elección: {}", eleccion.getDescripcion());

        if (eleccion == null) {
            throw new ValidacionException("La elección no puede ser nula");
        }

        if (eleccion.getDescripcion() == null || eleccion.getDescripcion().trim().isEmpty()) {
            throw new ValidacionException("La descripción de la elección es obligatoria");
        }

        if (eleccion.getFecEleccion() == null) {
            throw new ValidacionException("La fecha de la elección es obligatoria");
        }

        if (eleccion.getGestion() == null) {
            throw new ValidacionException("La gestión es obligatoria");
        }

        EscElecciones eleccionGuardada = eleccionesRepository.save(eleccion);
        log.info("Elección guardada exitosamente con ID: {}", eleccionGuardada.getIdEleccion());

        return eleccionGuardada;
    }

    // ==================== FRENTES ====================

    @Override
    public List<EscFrentes> listarFrentes() {
        log.debug("Listando todos los frentes electorales");
        return frentesRepository.findAll();
    }

    @Override
    @Transactional
    public EscFrentes guardarFrente(EscFrentes frente) {
        log.info("Guardando frente electoral: {}", frente.getNombre());

        if (frente == null) {
            throw new ValidacionException("El frente no puede ser nulo");
        }

        if (frente.getNombre() == null || frente.getNombre().trim().isEmpty()) {
            throw new ValidacionException("El nombre del frente es obligatorio");
        }

        if (frente.getEscElecciones() == null) {
            throw new ValidacionException("La elección asociada al frente es obligatoria");
        }

        // Establecer estado activo por defecto
        if (frente.getIdEstado() == null) {
            frente.setIdEstado("A");
        }

        EscFrentes frenteGuardado = frentesRepository.save(frente);
        log.info("Frente electoral guardado exitosamente con ID: {}", frenteGuardado.getIdFrente());

        return frenteGuardado;
    }

    // ==================== MESAS HABILITADAS ====================

    @Override
    public List<EscMesasHabilitadas> listarMesasHabilitadas() {
        log.debug("Listando todas las mesas habilitadas");
        return mesasHabilitadasRepository.findAll();
    }

    @Override
    @Transactional
    public EscMesasHabilitadas guardarMesaHabilitada(EscMesasHabilitadas mesa) {
        log.info("Guardando mesa habilitada número: {}", mesa.getNumeroMesa());

        if (mesa == null) {
            throw new ValidacionException("La mesa habilitada no puede ser nula");
        }

        if (mesa.getNumeroMesa() == null) {
            throw new ValidacionException("El número de mesa es obligatorio");
        }

        if (mesa.getEscElecciones() == null) {
            throw new ValidacionException("La elección asociada a la mesa es obligatoria");
        }

        // Establecer estado activo por defecto
        if (mesa.getIdEstado() == null) {
            mesa.setIdEstado("A");
        }

        EscMesasHabilitadas mesaGuardada = mesasHabilitadasRepository.save(mesa);
        log.info("Mesa habilitada guardada exitosamente con ID: {}", mesaGuardada.getIdMesaHabilitada());

        return mesaGuardada;
    }

    // ==================== ESCRUTINIO ACTAS ====================

    @Override
    public List<EscrutinioActas> listarEscrutinioActas() {
        log.debug("Listando todas las actas de escrutinio");
        return escrutinioActasRepository.findAll();
    }

    @Override
    @Transactional
    public EscrutinioActas guardarEscrutinioActa(EscrutinioActas acta) {
        log.info("Guardando acta de escrutinio para mesa ID: {}",
                acta.getEscMesasHabilitadas() != null ? acta.getEscMesasHabilitadas().getIdMesaHabilitada() : null);

        if (acta == null) {
            throw new ValidacionException("El acta de escrutinio no puede ser nula");
        }

        if (acta.getEscMesasHabilitadas() == null) {
            throw new ValidacionException("La mesa habilitada asociada al acta es obligatoria");
        }

        if (acta.getNumeroActa() == null) {
            throw new ValidacionException("El número de acta es obligatorio");
        }

        // Validar totales si están disponibles
        if (acta.getTotalVotos() != null && acta.getTotalVotos() < 0) {
            throw new ValidacionException("El total de votos no puede ser negativo");
        }

        EscrutinioActas actaGuardada = escrutinioActasRepository.save(acta);
        log.info("Acta de escrutinio guardada exitosamente con ID: {}", actaGuardada.getIdActa());

        return actaGuardada;
    }

    // ==================== DETALLES ====================

    @Override
    public List<EscDetalles> listarDetalles() {
        log.debug("Listando todos los detalles de escrutinio");
        return detallesRepository.findAll();
    }

    @Override
    @Transactional
    public EscDetalles guardarDetalle(EscDetalles detalle) {
        log.info("Guardando detalle de escrutinio para acta ID: {}",
                detalle.getEscrutinioActas() != null ? detalle.getEscrutinioActas().getIdActa() : null);

        if (detalle == null) {
            throw new ValidacionException("El detalle de escrutinio no puede ser nulo");
        }

        if (detalle.getEscrutinioActas() == null) {
            throw new ValidacionException("El acta de escrutinio asociada es obligatoria");
        }

        if (detalle.getEscFrentes() == null) {
            throw new ValidacionException("El frente electoral asociado es obligatorio");
        }

        if (detalle.getCantidadVotos() == null || detalle.getCantidadVotos() < 0) {
            throw new ValidacionException("La cantidad de votos debe ser mayor o igual a cero");
        }

        EscDetalles detalleGuardado = detallesRepository.save(detalle);
        log.info("Detalle de escrutinio guardado exitosamente con ID: {}", detalleGuardado.getIdDetalle());

        return detalleGuardado;
    }

    // ==================== RESULTADOS GRAFICOS ====================

    @Override
    public List<EscResultadosGraficos> listarResultadosGraficos() {
        log.debug("Listando todos los resultados gráficos");
        return resultadosGraficosRepository.findAll();
    }

    @Override
    @Transactional
    public EscResultadosGraficos guardarResultadoGrafico(EscResultadosGraficos resultado) {
        log.info("Guardando resultado gráfico para frente ID: {}",
                resultado.getEscFrentes() != null ? resultado.getEscFrentes().getIdFrente() : null);

        if (resultado == null) {
            throw new ValidacionException("El resultado gráfico no puede ser nulo");
        }

        if (resultado.getEscFrentes() == null) {
            throw new ValidacionException("El frente electoral asociado es obligatorio");
        }

        if (resultado.getTotalVotos() == null || resultado.getTotalVotos() < 0) {
            throw new ValidacionException("El total de votos debe ser mayor o igual a cero");
        }

        // Calcular porcentaje si se proporciona el total general
        if (resultado.getTotalVotosGeneral() != null && resultado.getTotalVotosGeneral() > 0) {
            Double porcentaje = (resultado.getTotalVotos().doubleValue() /
                    resultado.getTotalVotosGeneral().doubleValue()) * 100;
            resultado.setPorcentaje(porcentaje);
        }

        EscResultadosGraficos resultadoGuardado = resultadosGraficosRepository.save(resultado);
        log.info("Resultado gráfico guardado exitosamente con ID: {}", resultadoGuardado.getIdResultado());

        return resultadoGuardado;
    }
}
