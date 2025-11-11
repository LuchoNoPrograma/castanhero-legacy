package uap.usic.siga.service.postgrado.impl;

import uap.usic.siga.domain.postgrado.PgMatricula;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.postgrado.PgMatriculaRepository;
import uap.usic.siga.service.postgrado.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private PgMatriculaRepository matriculaRepository;

    @Override
    public PgMatricula crear(PgMatricula matricula) {
        if (matricula.getMatricula() == null || matricula.getMatricula().isEmpty()) {
            throw new ValidacionException("El número de matrícula es obligatorio");
        }

        if (matriculaRepository.findByMatricula(matricula.getMatricula()).isPresent()) {
            throw new ValidacionException("Ya existe una matrícula con el número: " + matricula.getMatricula());
        }

        matricula.activar();
        return matriculaRepository.save(matricula);
    }

    @Override
    public PgMatricula actualizar(PgMatricula matricula) {
        buscarPorId(matricula.getIdMatricula());
        return matriculaRepository.save(matricula);
    }

    @Override
    public void eliminar(Long idMatricula) {
        PgMatricula matricula = buscarPorId(idMatricula);
        matricula.desactivar();
        matriculaRepository.save(matricula);
    }

    @Override
    public PgMatricula buscarPorId(Long idMatricula) {
        return matriculaRepository.findById(idMatricula)
                .orElseThrow(() -> new RecursoNoEncontradoException("Matrícula no encontrada con ID: " + idMatricula));
    }

    @Override
    public PgMatricula buscarPorNumeroMatricula(String numeroMatricula) {
        return matriculaRepository.findByMatricula(numeroMatricula)
                .orElseThrow(() -> new RecursoNoEncontradoException("Matrícula no encontrada con número: " + numeroMatricula));
    }

    @Override
    public List<PgMatricula> listarTodas() {
        return matriculaRepository.findAll();
    }

    @Override
    public List<PgMatricula> listarActivas() {
        return matriculaRepository.listarActivas();
    }
}
