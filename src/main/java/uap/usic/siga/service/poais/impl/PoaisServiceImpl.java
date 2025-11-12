package uap.usic.siga.service.poais.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.poais.*;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.poais.*;
import uap.usic.siga.service.poais.PoaisService;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de POAIS
 * Proporciona lógica de negocio para planes operativos anuales individuales
 *
 * @author Sistema POAIS - USIC
 */
@Slf4j
@Service("poaisService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PoaisServiceImpl implements PoaisService {

    private final PoaisRepository poaisRepository;
    private final PoaisObjetivosRepository objetivosRepository;
    private final PoaisResultadosRepository resultadosRepository;
    private final PoaisActividadesRepository actividadesRepository;
    private final PoaisDetallesActividadesRepository detallesActividadesRepository;
    private final PoaisMesesRepository mesesRepository;
    private final PoaisSemanasRepository semanasRepository;
    private final PoaisRequisitosRepository requisitosRepository;
    private final PoaisRequisitosCualidadesRepository cualidadesRepository;
    private final PoaisRequisitosExperienciasRepository experienciasRepository;
    private final PoaisRequisitosFormacionesRepository formacionesRepository;
    private final PoaisRequisitosCumplimientosRepository cumplimientosRepository;
    private final PoaisTiposCumplimientosRepository tiposCumplimientosRepository;
    private final PoaisSupervisoresRepository supervisoresRepository;
    private final PoaisIdentificacionesRepository identificacionesRepository;

    // ==================== POAIS ====================

    @Override
    public List<Poais> listarPoaisPorPersonalYGestion(Long idPersonal, Integer gestion) {
        log.debug("Listando POAIS por personal: {} y gestión: {}", idPersonal, gestion);

        if (idPersonal == null || gestion == null) {
            throw new ValidacionException("El ID del personal y la gestión son requeridos");
        }

        return poaisRepository.findByPersonal_IdPersonalAndGestion(idPersonal, gestion);
    }

    @Override
    public List<Poais> listarPoaisPorPersonal(Long idPersonal) {
        log.debug("Listando POAIS por personal: {}", idPersonal);

        if (idPersonal == null) {
            throw new ValidacionException("El ID del personal es requerido");
        }

        return poaisRepository.findByPersonal_IdPersonal(idPersonal);
    }

    @Override
    public Optional<Poais> buscarPoaisPorId(Long id) {
        log.debug("Buscando POAI por ID: {}", id);

        if (id == null) {
            throw new ValidacionException("El ID del POAI no puede ser nulo");
        }

        return poaisRepository.findById(id);
    }

    @Override
    @Transactional
    public Poais guardarPoais(Poais poais) {
        log.info("Guardando POAI para personal ID: {}",
                poais.getPersonal() != null ? poais.getPersonal().getIdPersonal() : null);

        if (poais == null) {
            throw new ValidacionException("El POAI no puede ser nulo");
        }

        if (poais.getPersonal() == null) {
            throw new ValidacionException("El personal asociado al POAI es obligatorio");
        }

        if (poais.getGestion() == null) {
            throw new ValidacionException("La gestión es obligatoria");
        }

        Poais poaisGuardado = poaisRepository.save(poais);
        log.info("POAI guardado exitosamente con ID: {}", poaisGuardado.getIdPoai());

        return poaisGuardado;
    }

    // ==================== OBJETIVOS ====================

    @Override
    public List<PoaisObjetivos> listarObjetivosPorPersonal(Long idPersonal) {
        log.debug("Listando objetivos por personal: {}", idPersonal);

        if (idPersonal == null) {
            throw new ValidacionException("El ID del personal es requerido");
        }

        return objetivosRepository.findByPoais_Personal_IdPersonal(idPersonal);
    }

    @Override
    public Optional<PoaisObjetivos> buscarObjetivoPorPoai(Long idPoai) {
        log.debug("Buscando objetivo por POAI: {}", idPoai);

        if (idPoai == null) {
            throw new ValidacionException("El ID del POAI es requerido");
        }

        return objetivosRepository.findByPoais_IdPoai(idPoai);
    }

    @Override
    @Transactional
    public PoaisObjetivos guardarObjetivo(PoaisObjetivos objetivo) {
        log.info("Guardando objetivo para POAI ID: {}",
                objetivo.getPoais() != null ? objetivo.getPoais().getIdPoai() : null);

        if (objetivo == null) {
            throw new ValidacionException("El objetivo no puede ser nulo");
        }

        if (objetivo.getPoais() == null) {
            throw new ValidacionException("El POAI asociado al objetivo es obligatorio");
        }

        PoaisObjetivos objetivoGuardado = objetivosRepository.save(objetivo);
        log.info("Objetivo guardado exitosamente con ID: {}", objetivoGuardado.getIdObjetivo());

        return objetivoGuardado;
    }

    // ==================== RESULTADOS ====================

    @Override
    public List<PoaisResultados> listarResultadosPorObjetivo(Long idObjetivo) {
        log.debug("Listando resultados por objetivo: {}", idObjetivo);

        if (idObjetivo == null) {
            throw new ValidacionException("El ID del objetivo es requerido");
        }

        return resultadosRepository.findByPoaisObjetivos_IdObjetivo(idObjetivo);
    }

    @Override
    @Transactional
    public PoaisResultados guardarResultado(PoaisResultados resultado) {
        log.info("Guardando resultado para objetivo ID: {}",
                resultado.getPoaisObjetivos() != null ? resultado.getPoaisObjetivos().getIdObjetivo() : null);

        if (resultado == null) {
            throw new ValidacionException("El resultado no puede ser nulo");
        }

        if (resultado.getPoaisObjetivos() == null) {
            throw new ValidacionException("El objetivo asociado al resultado es obligatorio");
        }

        PoaisResultados resultadoGuardado = resultadosRepository.save(resultado);
        log.info("Resultado guardado exitosamente con ID: {}", resultadoGuardado.getIdResultado());

        return resultadoGuardado;
    }

    // ==================== ACTIVIDADES ====================

    @Override
    public List<PoaisActividades> listarActividadesPorResultado(Long idResultado) {
        log.debug("Listando actividades por resultado: {}", idResultado);

        if (idResultado == null) {
            throw new ValidacionException("El ID del resultado es requerido");
        }

        return actividadesRepository.findByPoaisResultados_IdResultado(idResultado);
    }

    @Override
    @Transactional
    public PoaisActividades guardarActividad(PoaisActividades actividad) {
        log.info("Guardando actividad para resultado ID: {}",
                actividad.getPoaisResultados() != null ? actividad.getPoaisResultados().getIdResultado() : null);

        if (actividad == null) {
            throw new ValidacionException("La actividad no puede ser nula");
        }

        if (actividad.getPoaisResultados() == null) {
            throw new ValidacionException("El resultado asociado a la actividad es obligatorio");
        }

        PoaisActividades actividadGuardada = actividadesRepository.save(actividad);
        log.info("Actividad guardada exitosamente con ID: {}", actividadGuardada.getIdActividad());

        return actividadGuardada;
    }

    // ==================== CATALOGOS ====================

    @Override
    public List<PoaisMeses> listarMeses() {
        log.debug("Listando meses");
        return mesesRepository.findAll();
    }

    @Override
    public List<PoaisSemanas> listarSemanas() {
        log.debug("Listando semanas");
        return semanasRepository.findAll();
    }

    // ==================== DETALLES ACTIVIDADES ====================

    @Override
    @Transactional
    public PoaisDetallesActividades guardarDetalleActividad(PoaisDetallesActividades detalle) {
        log.info("Guardando detalle de actividad para actividad ID: {}",
                detalle.getPoaisActividades() != null ? detalle.getPoaisActividades().getIdActividad() : null);

        if (detalle == null) {
            throw new ValidacionException("El detalle de actividad no puede ser nulo");
        }

        if (detalle.getPoaisActividades() == null) {
            throw new ValidacionException("La actividad asociada es obligatoria");
        }

        PoaisDetallesActividades detalleGuardado = detallesActividadesRepository.save(detalle);
        log.info("Detalle de actividad guardado exitosamente con ID: {}",
                detalleGuardado.getIdDetalleActividad());

        return detalleGuardado;
    }

    // ==================== REQUISITOS ====================

    @Override
    public List<PoaisRequisitos> listarRequisitosPorPoai(Long idPoai) {
        log.debug("Listando requisitos por POAI: {}", idPoai);

        if (idPoai == null) {
            throw new ValidacionException("El ID del POAI es requerido");
        }

        return requisitosRepository.findByPoais_IdPoai(idPoai);
    }

    @Override
    @Transactional
    public PoaisRequisitos guardarRequisito(PoaisRequisitos requisito) {
        log.info("Guardando requisito para POAI ID: {}",
                requisito.getPoais() != null ? requisito.getPoais().getIdPoai() : null);

        if (requisito == null) {
            throw new ValidacionException("El requisito no puede ser nulo");
        }

        if (requisito.getPoais() == null) {
            throw new ValidacionException("El POAI asociado al requisito es obligatorio");
        }

        PoaisRequisitos requisitoGuardado = requisitosRepository.save(requisito);
        log.info("Requisito guardado exitosamente con ID: {}", requisitoGuardado.getIdRequisito());

        return requisitoGuardado;
    }

    // ==================== CUALIDADES ====================

    @Override
    public List<PoaisRequisitosCualidades> listarCualidadesPorRequisito(Long idRequisito) {
        log.debug("Listando cualidades por requisito: {}", idRequisito);

        if (idRequisito == null) {
            throw new ValidacionException("El ID del requisito es requerido");
        }

        return cualidadesRepository.findByPoaisRequisitos_IdRequisito(idRequisito);
    }

    @Override
    @Transactional
    public PoaisRequisitosCualidades guardarCualidad(PoaisRequisitosCualidades cualidad) {
        log.info("Guardando cualidad para requisito ID: {}",
                cualidad.getPoaisRequisitos() != null ? cualidad.getPoaisRequisitos().getIdRequisito() : null);

        if (cualidad == null) {
            throw new ValidacionException("La cualidad no puede ser nula");
        }

        if (cualidad.getPoaisRequisitos() == null) {
            throw new ValidacionException("El requisito asociado es obligatorio");
        }

        PoaisRequisitosCualidades cualidadGuardada = cualidadesRepository.save(cualidad);
        log.info("Cualidad guardada exitosamente con ID: {}", cualidadGuardada.getIdCualidad());

        return cualidadGuardada;
    }

    // ==================== EXPERIENCIAS ====================

    @Override
    public List<PoaisRequisitosExperiencias> listarExperienciasPorRequisito(Long idRequisito) {
        log.debug("Listando experiencias por requisito: {}", idRequisito);

        if (idRequisito == null) {
            throw new ValidacionException("El ID del requisito es requerido");
        }

        return experienciasRepository.findByPoaisRequisitos_IdRequisito(idRequisito);
    }

    @Override
    @Transactional
    public PoaisRequisitosExperiencias guardarExperiencia(PoaisRequisitosExperiencias experiencia) {
        log.info("Guardando experiencia para requisito ID: {}",
                experiencia.getPoaisRequisitos() != null ? experiencia.getPoaisRequisitos().getIdRequisito() : null);

        if (experiencia == null) {
            throw new ValidacionException("La experiencia no puede ser nula");
        }

        if (experiencia.getPoaisRequisitos() == null) {
            throw new ValidacionException("El requisito asociado es obligatorio");
        }

        PoaisRequisitosExperiencias experienciaGuardada = experienciasRepository.save(experiencia);
        log.info("Experiencia guardada exitosamente con ID: {}", experienciaGuardada.getIdExperiencia());

        return experienciaGuardada;
    }

    // ==================== FORMACIONES ====================

    @Override
    public List<PoaisRequisitosFormaciones> listarFormacionesPorRequisito(Long idRequisito) {
        log.debug("Listando formaciones por requisito: {}", idRequisito);

        if (idRequisito == null) {
            throw new ValidacionException("El ID del requisito es requerido");
        }

        return formacionesRepository.findByPoaisRequisitos_IdRequisito(idRequisito);
    }

    @Override
    @Transactional
    public PoaisRequisitosFormaciones guardarFormacion(PoaisRequisitosFormaciones formacion) {
        log.info("Guardando formación para requisito ID: {}",
                formacion.getPoaisRequisitos() != null ? formacion.getPoaisRequisitos().getIdRequisito() : null);

        if (formacion == null) {
            throw new ValidacionException("La formación no puede ser nula");
        }

        if (formacion.getPoaisRequisitos() == null) {
            throw new ValidacionException("El requisito asociado es obligatorio");
        }

        PoaisRequisitosFormaciones formacionGuardada = formacionesRepository.save(formacion);
        log.info("Formación guardada exitosamente con ID: {}", formacionGuardada.getIdFormacion());

        return formacionGuardada;
    }

    // ==================== CUMPLIMIENTOS ====================

    @Override
    public List<PoaisRequisitosCumplimientos> listarCumplimientosPorRequisito(Long idRequisito) {
        log.debug("Listando cumplimientos por requisito: {}", idRequisito);

        if (idRequisito == null) {
            throw new ValidacionException("El ID del requisito es requerido");
        }

        return cumplimientosRepository.findByPoaisRequisitos_IdRequisito(idRequisito);
    }

    @Override
    @Transactional
    public PoaisRequisitosCumplimientos guardarCumplimiento(PoaisRequisitosCumplimientos cumplimiento) {
        log.info("Guardando cumplimiento para requisito ID: {}",
                cumplimiento.getPoaisRequisitos() != null ? cumplimiento.getPoaisRequisitos().getIdRequisito() : null);

        if (cumplimiento == null) {
            throw new ValidacionException("El cumplimiento no puede ser nulo");
        }

        if (cumplimiento.getPoaisRequisitos() == null) {
            throw new ValidacionException("El requisito asociado es obligatorio");
        }

        PoaisRequisitosCumplimientos cumplimientoGuardado = cumplimientosRepository.save(cumplimiento);
        log.info("Cumplimiento guardado exitosamente con ID: {}", cumplimientoGuardado.getIdCumplimiento());

        return cumplimientoGuardado;
    }

    @Override
    public List<PoaisTiposCumplimientos> listarTiposCumplimientos() {
        log.debug("Listando tipos de cumplimientos");
        return tiposCumplimientosRepository.findAll();
    }

    // ==================== SUPERVISORES ====================

    @Override
    public List<PoaisSupervisores> listarSupervisores() {
        log.debug("Listando supervisores");
        return supervisoresRepository.findAll();
    }

    @Override
    @Transactional
    public PoaisSupervisores guardarSupervisor(PoaisSupervisores supervisor) {
        log.info("Guardando supervisor: {}",
                supervisor.getPersonal() != null ? supervisor.getPersonal().getIdPersonal() : null);

        if (supervisor == null) {
            throw new ValidacionException("El supervisor no puede ser nulo");
        }

        if (supervisor.getPersonal() == null) {
            throw new ValidacionException("El personal asociado al supervisor es obligatorio");
        }

        PoaisSupervisores supervisorGuardado = supervisoresRepository.save(supervisor);
        log.info("Supervisor guardado exitosamente con ID: {}", supervisorGuardado.getIdSupervisor());

        return supervisorGuardado;
    }

    // ==================== IDENTIFICACIONES ====================

    @Override
    @Transactional
    public PoaisIdentificaciones guardarIdentificacion(PoaisIdentificaciones identificacion) {
        log.info("Guardando identificación para POAI ID: {}",
                identificacion.getPoais() != null ? identificacion.getPoais().getIdPoai() : null);

        if (identificacion == null) {
            throw new ValidacionException("La identificación no puede ser nula");
        }

        if (identificacion.getPoais() == null) {
            throw new ValidacionException("El POAI asociado es obligatorio");
        }

        PoaisIdentificaciones identificacionGuardada = identificacionesRepository.save(identificacion);
        log.info("Identificación guardada exitosamente con ID: {}",
                identificacionGuardada.getIdIdentificacion());

        return identificacionGuardada;
    }
}
