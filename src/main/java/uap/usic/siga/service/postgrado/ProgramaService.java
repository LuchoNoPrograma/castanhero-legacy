package uap.usic.siga.service.postgrado;

import uap.usic.siga.domain.postgrado.*;

import java.util.List;

public interface ProgramaService {

    PgPrograma crear(PgPrograma programa);

    PgPrograma actualizar(PgPrograma programa);

    void eliminar(Long idPrograma);

    PgPrograma buscarPorId(Long idPrograma);

    PgPrograma buscarPorSigla(String sigla);

    List<PgPrograma> listarTodos();

    List<PgPrograma> listarActivos();

    List<PgPrograma> listarDisponibles();

    List<PgPrograma> buscarPorNombre(String nombre);

    List<PgPrograma> listarPorModalidad(Long idModalidad);

    List<PgPrograma> listarPorGradoAcademico(Long idGrado);

    PgPrgPlan crearPlan(PgPrgPlan plan);

    PgPrgPlan buscarPlanPorId(Long idPlan);

    List<PgPrgPlan> listarPlanesPorPrograma(Long idPrograma);

    PgPrgModulo crearModulo(PgPrgModulo modulo);

    List<PgPrgModulo> listarModulosPorPlan(Long idPlan);

    PgPrgPlnVersion crearVersion(PgPrgPlnVersion version);

    List<PgPrgPlnVersion> listarVersionesPorPrograma(Long idPrograma);

    PgPrgPlnGrupo crearGrupo(PgPrgPlnGrupo grupo);

    List<PgPrgPlnGrupo> listarGruposPorVersion(Long idVersion);
}
