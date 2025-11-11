package uap.usic.siga.service.congreso.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.congreso.*;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.congreso.*;
import uap.usic.siga.service.congreso.CongresoService;

import java.util.List;

/**
 * Implementación del servicio de gestión de congresos
 * Proporciona lógica de negocio para congresos, congresistas y comisiones
 *
 * @author Sistema de Congreso - USIC
 */
@Slf4j
@Service("congresoService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CongresoServiceImpl implements CongresoService {

    private final CongresoUapRepository congresoRepository;
    private final CngTiposCongresistasRepository tiposCongresistasRepository;
    private final CngTiposCargosRepository tiposCargosRepository;
    private final CngTiposComisionesRepository tiposComisionesRepository;
    private final CngCongresistasRepository congresistasRepository;
    private final CngMesaDirectivaRepository mesaDirectivaRepository;
    private final CngComisionesRepository comisionesRepository;

    // ==================== CONGRESOS ====================

    @Override
    public List<CongresoUap> listarCongresos() {
        log.debug("Listando todos los congresos");
        return congresoRepository.findAll();
    }

    @Override
    @Transactional
    public CongresoUap guardarCongreso(CongresoUap congreso) {
        log.info("Guardando congreso: {}", congreso.getNombre());

        if (congreso == null) {
            throw new ValidacionException("El congreso no puede ser nulo");
        }

        if (congreso.getNombre() == null || congreso.getNombre().trim().isEmpty()) {
            throw new ValidacionException("El nombre del congreso es obligatorio");
        }

        if (congreso.getGestion() == null) {
            throw new ValidacionException("La gestión es obligatoria");
        }

        // Establecer estado activo por defecto
        if (congreso.getIdEstado() == null) {
            congreso.setIdEstado("A");
        }

        CongresoUap congresoGuardado = congresoRepository.save(congreso);
        log.info("Congreso guardado exitosamente con ID: {}", congresoGuardado.getIdCongreso());

        return congresoGuardado;
    }

    // ==================== TIPOS DE CONGRESISTAS ====================

    @Override
    public List<CngTiposCongresistas> listarTiposCongresistas() {
        log.debug("Listando todos los tipos de congresistas");
        return tiposCongresistasRepository.findAll();
    }

    @Override
    @Transactional
    public CngTiposCongresistas guardarTipoCongresista(CngTiposCongresistas tipo) {
        log.info("Guardando tipo de congresista: {}", tipo.getDescripcion());

        if (tipo == null) {
            throw new ValidacionException("El tipo de congresista no puede ser nulo");
        }

        if (tipo.getDescripcion() == null || tipo.getDescripcion().trim().isEmpty()) {
            throw new ValidacionException("La descripción del tipo de congresista es obligatoria");
        }

        // Establecer estado activo por defecto
        if (tipo.getIdEstado() == null) {
            tipo.setIdEstado("A");
        }

        CngTiposCongresistas tipoGuardado = tiposCongresistasRepository.save(tipo);
        log.info("Tipo de congresista guardado exitosamente con ID: {}", tipoGuardado.getIdTipoCongresista());

        return tipoGuardado;
    }

    // ==================== TIPOS DE CARGOS ====================

    @Override
    public List<CngTiposCargos> listarTiposCargos() {
        log.debug("Listando todos los tipos de cargos");
        return tiposCargosRepository.findAll();
    }

    @Override
    @Transactional
    public CngTiposCargos guardarTipoCargo(CngTiposCargos tipo) {
        log.info("Guardando tipo de cargo: {}", tipo.getDescripcion());

        if (tipo == null) {
            throw new ValidacionException("El tipo de cargo no puede ser nulo");
        }

        if (tipo.getDescripcion() == null || tipo.getDescripcion().trim().isEmpty()) {
            throw new ValidacionException("La descripción del tipo de cargo es obligatoria");
        }

        // Establecer estado activo por defecto
        if (tipo.getIdEstado() == null) {
            tipo.setIdEstado("A");
        }

        CngTiposCargos tipoGuardado = tiposCargosRepository.save(tipo);
        log.info("Tipo de cargo guardado exitosamente con ID: {}", tipoGuardado.getIdTipoCargo());

        return tipoGuardado;
    }

    // ==================== TIPOS DE COMISIONES ====================

    @Override
    public List<CngTiposComisiones> listarTiposComisiones() {
        log.debug("Listando todos los tipos de comisiones");
        return tiposComisionesRepository.findAll();
    }

    @Override
    @Transactional
    public CngTiposComisiones guardarTipoComision(CngTiposComisiones tipo) {
        log.info("Guardando tipo de comisión: {}", tipo.getDescripcion());

        if (tipo == null) {
            throw new ValidacionException("El tipo de comisión no puede ser nulo");
        }

        if (tipo.getDescripcion() == null || tipo.getDescripcion().trim().isEmpty()) {
            throw new ValidacionException("La descripción del tipo de comisión es obligatoria");
        }

        // Establecer estado activo por defecto
        if (tipo.getIdEstado() == null) {
            tipo.setIdEstado("A");
        }

        CngTiposComisiones tipoGuardado = tiposComisionesRepository.save(tipo);
        log.info("Tipo de comisión guardado exitosamente con ID: {}", tipoGuardado.getIdTipoComision());

        return tipoGuardado;
    }

    // ==================== CONGRESISTAS ====================

    @Override
    public List<CngCongresistas> listarCongresistas() {
        log.debug("Listando todos los congresistas");
        return congresistasRepository.findAll();
    }

    @Override
    @Transactional
    public CngCongresistas guardarCongresista(CngCongresistas congresista) {
        log.info("Guardando congresista para persona ID: {}",
                congresista.getPersonas() != null ? congresista.getPersonas().getIdPersona() : null);

        if (congresista == null) {
            throw new ValidacionException("El congresista no puede ser nulo");
        }

        if (congresista.getPersonas() == null) {
            throw new ValidacionException("La persona asociada al congresista es obligatoria");
        }

        if (congresista.getCongresoUap() == null) {
            throw new ValidacionException("El congreso asociado es obligatorio");
        }

        if (congresista.getCngTiposCongresistas() == null) {
            throw new ValidacionException("El tipo de congresista es obligatorio");
        }

        // Establecer estado activo por defecto
        if (congresista.getIdEstado() == null) {
            congresista.setIdEstado("A");
        }

        CngCongresistas congresistaGuardado = congresistasRepository.save(congresista);
        log.info("Congresista guardado exitosamente con ID: {}", congresistaGuardado.getIdCongresista());

        return congresistaGuardado;
    }

    // ==================== MESA DIRECTIVA ====================

    @Override
    public List<CngMesaDirectiva> listarMesasDirectivas() {
        log.debug("Listando todas las mesas directivas");
        return mesaDirectivaRepository.findAll();
    }

    @Override
    @Transactional
    public CngMesaDirectiva guardarMesaDirectiva(CngMesaDirectiva mesa) {
        log.info("Guardando mesa directiva para congresista ID: {}",
                mesa.getCngCongresistas() != null ? mesa.getCngCongresistas().getIdCongresista() : null);

        if (mesa == null) {
            throw new ValidacionException("La mesa directiva no puede ser nula");
        }

        if (mesa.getCngCongresistas() == null) {
            throw new ValidacionException("El congresista asociado a la mesa directiva es obligatorio");
        }

        if (mesa.getCongresoUap() == null) {
            throw new ValidacionException("El congreso asociado es obligatorio");
        }

        if (mesa.getCngTiposCargos() == null) {
            throw new ValidacionException("El tipo de cargo es obligatorio");
        }

        // Establecer estado activo por defecto
        if (mesa.getIdEstado() == null) {
            mesa.setIdEstado("A");
        }

        CngMesaDirectiva mesaGuardada = mesaDirectivaRepository.save(mesa);
        log.info("Mesa directiva guardada exitosamente con ID: {}", mesaGuardada.getIdMesaDirectiva());

        return mesaGuardada;
    }

    // ==================== COMISIONES ====================

    @Override
    public List<CngComisiones> listarComisiones() {
        log.debug("Listando todas las comisiones");
        return comisionesRepository.findAll();
    }

    @Override
    @Transactional
    public CngComisiones guardarComision(CngComisiones comision) {
        log.info("Guardando comisión para congresista ID: {}",
                comision.getCngCongresistas() != null ? comision.getCngCongresistas().getIdCongresista() : null);

        if (comision == null) {
            throw new ValidacionException("La comisión no puede ser nula");
        }

        if (comision.getCngCongresistas() == null) {
            throw new ValidacionException("El congresista asociado a la comisión es obligatorio");
        }

        if (comision.getCongresoUap() == null) {
            throw new ValidacionException("El congreso asociado es obligatorio");
        }

        if (comision.getCngTiposComisiones() == null) {
            throw new ValidacionException("El tipo de comisión es obligatorio");
        }

        // Establecer estado activo por defecto
        if (comision.getIdEstado() == null) {
            comision.setIdEstado("A");
        }

        CngComisiones comisionGuardada = comisionesRepository.save(comision);
        log.info("Comisión guardada exitosamente con ID: {}", comisionGuardada.getIdComision());

        return comisionGuardada;
    }
}
