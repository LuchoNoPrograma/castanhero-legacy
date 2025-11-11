package uap.usic.siga.service.postgrado.impl;

import uap.usic.siga.domain.postgrado.PgEstudiante;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.postgrado.PgEstudianteRepository;
import uap.usic.siga.service.postgrado.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private PgEstudianteRepository estudianteRepository;

    @Override
    public PgEstudiante crear(PgEstudiante estudiante) {
        if (estudiante.getPersona() == null) {
            throw new ValidacionException("La persona del estudiante es obligatoria");
        }
        if (estudiante.getPrograma() == null) {
            throw new ValidacionException("El programa del estudiante es obligatorio");
        }
        estudiante.activar();
        return estudianteRepository.save(estudiante);
    }

    @Override
    public PgEstudiante actualizar(PgEstudiante estudiante) {
        buscarPorId(estudiante.getIdEstudiante());
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminar(Long idEstudiante) {
        PgEstudiante estudiante = buscarPorId(idEstudiante);
        estudiante.desactivar();
        estudianteRepository.save(estudiante);
    }

    @Override
    public PgEstudiante buscarPorId(Long idEstudiante) {
        return estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado con ID: " + idEstudiante));
    }

    @Override
    public PgEstudiante buscarPorCi(String ci) {
        return estudianteRepository.buscarPorCi(ci)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado con CI: " + ci));
    }

    @Override
    public List<PgEstudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    @Override
    public List<PgEstudiante> listarPorPrograma(Long idPrograma) {
        return estudianteRepository.listarActivosPorPrograma(idPrograma);
    }

    @Override
    public List<PgEstudiante> listarActivos() {
        return estudianteRepository.listarInscritos();
    }

    @Override
    public List<PgEstudiante> listarEgresados() {
        return estudianteRepository.listarEgresados();
    }

    @Override
    public List<PgEstudiante> listarInscritos() {
        return estudianteRepository.listarInscritos();
    }
}
