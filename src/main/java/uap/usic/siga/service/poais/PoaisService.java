package uap.usic.siga.service.poais;

import uap.usic.siga.domain.poais.*;

import java.util.List;
import java.util.Optional;

public interface PoaisService {

    List<Poais> listarPoaisPorPersonalYGestion(Long idPersonal, Integer gestion);

    List<Poais> listarPoaisPorPersonal(Long idPersonal);

    Optional<Poais> buscarPoaisPorId(Long id);

    Poais guardarPoais(Poais poais);

    List<PoaisObjetivos> listarObjetivosPorPersonal(Long idPersonal);

    Optional<PoaisObjetivos> buscarObjetivoPorPoai(Long idPoai);

    PoaisObjetivos guardarObjetivo(PoaisObjetivos objetivo);

    List<PoaisResultados> listarResultadosPorObjetivo(Long idObjetivo);

    PoaisResultados guardarResultado(PoaisResultados resultado);

    List<PoaisActividades> listarActividadesPorResultado(Long idResultado);

    PoaisActividades guardarActividad(PoaisActividades actividad);

    List<PoaisMeses> listarMeses();

    List<PoaisSemanas> listarSemanas();

    PoaisDetallesActividades guardarDetalleActividad(PoaisDetallesActividades detalle);

    List<PoaisRequisitos> listarRequisitosPorPoai(Long idPoai);

    PoaisRequisitos guardarRequisito(PoaisRequisitos requisito);

    List<PoaisRequisitosCualidades> listarCualidadesPorRequisito(Long idRequisito);

    PoaisRequisitosCualidades guardarCualidad(PoaisRequisitosCualidades cualidad);

    List<PoaisRequisitosExperiencias> listarExperienciasPorRequisito(Long idRequisito);

    PoaisRequisitosExperiencias guardarExperiencia(PoaisRequisitosExperiencias experiencia);

    List<PoaisRequisitosFormaciones> listarFormacionesPorRequisito(Long idRequisito);

    PoaisRequisitosFormaciones guardarFormacion(PoaisRequisitosFormaciones formacion);

    List<PoaisRequisitosCumplimientos> listarCumplimientosPorRequisito(Long idRequisito);

    PoaisRequisitosCumplimientos guardarCumplimiento(PoaisRequisitosCumplimientos cumplimiento);

    List<PoaisTiposCumplimientos> listarTiposCumplimientos();

    List<PoaisSupervisores> listarSupervisores();

    PoaisSupervisores guardarSupervisor(PoaisSupervisores supervisor);

    PoaisIdentificaciones guardarIdentificacion(PoaisIdentificaciones identificacion);
}
