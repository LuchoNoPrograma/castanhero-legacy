package uap.usic.siga.service.postgrado;

import uap.usic.siga.domain.postgrado.PgDocente;

import java.util.List;

public interface DocenteService {

    PgDocente crear(PgDocente docente);

    PgDocente actualizar(PgDocente docente);

    void eliminar(Long idDocente);

    PgDocente buscarPorId(Long idDocente);

    PgDocente buscarPorCi(String ci);

    PgDocente buscarPorEmail(String email);

    List<PgDocente> listarTodos();

    List<PgDocente> listarActivos();

    List<PgDocente> buscarPorNombre(String nombre);

    List<PgDocente> listarPorGradoAcademico(Long idGrado);
}
