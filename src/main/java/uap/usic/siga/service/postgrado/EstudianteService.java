package uap.usic.siga.service.postgrado;

import uap.usic.siga.domain.postgrado.PgEstudiante;

import java.util.List;

public interface EstudianteService {

    PgEstudiante crear(PgEstudiante estudiante);

    PgEstudiante actualizar(PgEstudiante estudiante);

    void eliminar(Long idEstudiante);

    PgEstudiante buscarPorId(Long idEstudiante);

    PgEstudiante buscarPorCi(String ci);

    List<PgEstudiante> listarTodos();

    List<PgEstudiante> listarPorPrograma(Long idPrograma);

    List<PgEstudiante> listarActivos();

    List<PgEstudiante> listarEgresados();

    List<PgEstudiante> listarInscritos();
}
