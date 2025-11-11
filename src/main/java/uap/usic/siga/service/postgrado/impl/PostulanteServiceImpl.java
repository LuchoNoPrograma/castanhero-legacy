package uap.usic.siga.service.postgrado.impl;

import uap.usic.siga.domain.postgrado.PgPostulante;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.postgrado.PgPostulanteRepository;
import uap.usic.siga.service.postgrado.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostulanteServiceImpl implements PostulanteService {

    @Autowired
    private PgPostulanteRepository postulanteRepository;

    @Override
    public PgPostulante crear(PgPostulante postulante) {
        if (postulante.getNombres() == null || postulante.getNombres().isEmpty()) {
            throw new ValidacionException("El nombre del postulante es obligatorio");
        }
        if (postulante.getCi() == null || postulante.getCi().isEmpty()) {
            throw new ValidacionException("El CI del postulante es obligatorio");
        }

        if (postulanteRepository.findByCi(postulante.getCi()).isPresent()) {
            throw new ValidacionException("Ya existe un postulante con el CI: " + postulante.getCi());
        }

        postulante.activar();
        return postulanteRepository.save(postulante);
    }

    @Override
    public PgPostulante actualizar(PgPostulante postulante) {
        buscarPorId(postulante.getIdPostulante());
        return postulanteRepository.save(postulante);
    }

    @Override
    public void eliminar(Long idPostulante) {
        PgPostulante postulante = buscarPorId(idPostulante);
        postulante.desactivar();
        postulanteRepository.save(postulante);
    }

    @Override
    public PgPostulante buscarPorId(Long idPostulante) {
        return postulanteRepository.findById(idPostulante)
                .orElseThrow(() -> new RecursoNoEncontradoException("Postulante no encontrado con ID: " + idPostulante));
    }

    @Override
    public PgPostulante buscarPorCi(String ci) {
        return postulanteRepository.findByCi(ci)
                .orElseThrow(() -> new RecursoNoEncontradoException("Postulante no encontrado con CI: " + ci));
    }

    @Override
    public PgPostulante buscarPorEmail(String email) {
        return postulanteRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNoEncontradoException("Postulante no encontrado con email: " + email));
    }

    @Override
    public List<PgPostulante> listarTodos() {
        return postulanteRepository.findAll();
    }

    @Override
    public List<PgPostulante> listarActivos() {
        return postulanteRepository.listarActivos();
    }

    @Override
    public List<PgPostulante> buscarPorNombre(String nombre) {
        return postulanteRepository.buscarPorNombre(nombre);
    }

    @Override
    public List<PgPostulante> listarPorGradoAcademico(Long idGrado) {
        return postulanteRepository.listarPorGradoAcademico(idGrado);
    }
}
