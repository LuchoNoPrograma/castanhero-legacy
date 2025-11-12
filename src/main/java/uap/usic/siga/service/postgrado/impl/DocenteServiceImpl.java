package uap.usic.siga.service.postgrado.impl;

import uap.usic.siga.domain.postgrado.PgDocente;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.postgrado.PgDocenteRepository;
import uap.usic.siga.service.postgrado.DocenteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private PgDocenteRepository docenteRepository;

    @Override
    public PgDocente crear(PgDocente docente) {
        if (docente.getNombres() == null || docente.getNombres().isEmpty()) {
            throw new ValidacionException("El nombre del docente es obligatorio");
        }
        if (docente.getCi() == null || docente.getCi().isEmpty()) {
            throw new ValidacionException("El CI del docente es obligatorio");
        }

        if (docenteRepository.findByCi(docente.getCi()).isPresent()) {
            throw new ValidacionException("Ya existe un docente con el CI: " + docente.getCi());
        }

        docente.activar();
        return docenteRepository.save(docente);
    }

    @Override
    public PgDocente actualizar(PgDocente docente) {
        buscarPorId(docente.getIdDocente());
        return docenteRepository.save(docente);
    }

    @Override
    public void eliminar(Long idDocente) {
        PgDocente docente = buscarPorId(idDocente);
        docente.desactivar();
        docenteRepository.save(docente);
    }

    @Override
    public PgDocente buscarPorId(Long idDocente) {
        return docenteRepository.findById(idDocente)
                .orElseThrow(() -> new RecursoNoEncontradoException("Docente no encontrado con ID: " + idDocente));
    }

    @Override
    public PgDocente buscarPorCi(String ci) {
        return docenteRepository.findByCi(ci)
                .orElseThrow(() -> new RecursoNoEncontradoException("Docente no encontrado con CI: " + ci));
    }

    @Override
    public PgDocente buscarPorEmail(String email) {
        return docenteRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNoEncontradoException("Docente no encontrado con email: " + email));
    }

    @Override
    public List<PgDocente> listarTodos() {
        return docenteRepository.findAll();
    }

    @Override
    public List<PgDocente> listarActivos() {
        return docenteRepository.listarActivos();
    }

    @Override
    public List<PgDocente> buscarPorNombre(String nombre) {
        return docenteRepository.buscarPorNombre(nombre);
    }

    @Override
    public List<PgDocente> listarPorGradoAcademico(Long idGrado) {
        return docenteRepository.listarPorGradoAcademico(idGrado);
    }
}
