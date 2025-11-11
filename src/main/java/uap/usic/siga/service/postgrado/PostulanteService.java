package uap.usic.siga.service.postgrado;

import uap.usic.siga.domain.postgrado.PgPostulante;

import java.util.List;

public interface PostulanteService {

    PgPostulante crear(PgPostulante postulante);

    PgPostulante actualizar(PgPostulante postulante);

    void eliminar(Long idPostulante);

    PgPostulante buscarPorId(Long idPostulante);

    PgPostulante buscarPorCi(String ci);

    PgPostulante buscarPorEmail(String email);

    List<PgPostulante> listarTodos();

    List<PgPostulante> listarActivos();

    List<PgPostulante> buscarPorNombre(String nombre);

    List<PgPostulante> listarPorGradoAcademico(Long idGrado);
}
