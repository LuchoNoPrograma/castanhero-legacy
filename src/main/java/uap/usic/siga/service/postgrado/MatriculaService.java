package uap.usic.siga.service.postgrado;

import uap.usic.siga.domain.postgrado.PgMatricula;

import java.util.List;

public interface MatriculaService {

    PgMatricula crear(PgMatricula matricula);

    PgMatricula actualizar(PgMatricula matricula);

    void eliminar(Long idMatricula);

    PgMatricula buscarPorId(Long idMatricula);

    PgMatricula buscarPorNumeroMatricula(String numeroMatricula);

    List<PgMatricula> listarTodas();

    List<PgMatricula> listarActivas();
}
