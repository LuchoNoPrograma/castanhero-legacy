package uap.usic.siga.service.postgrado.impl;

import uap.usic.siga.domain.postgrado.*;
import uap.usic.siga.exception.RecursoNoEncontradoException;
import uap.usic.siga.exception.ValidacionException;
import uap.usic.siga.repository.postgrado.*;
import uap.usic.siga.service.postgrado.ProgramaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProgramaServiceImpl implements ProgramaService {

    @Autowired
    private PgProgramaRepository programaRepository;

    @Autowired
    private PgPrgPlanRepository planRepository;

    @Autowired
    private PgPrgModuloRepository moduloRepository;

    @Autowired
    private PgPrgPlnVersionRepository versionRepository;

    @Autowired
    private PgPrgPlnGrupoRepository grupoRepository;

    @Override
    public PgPrograma crear(PgPrograma programa) {
        if (programa.getPrograma() == null || programa.getPrograma().isEmpty()) {
            throw new ValidacionException("El nombre del programa es obligatorio");
        }
        programa.activar();
        return programaRepository.save(programa);
    }

    @Override
    public PgPrograma actualizar(PgPrograma programa) {
        buscarPorId(programa.getIdPrograma());
        return programaRepository.save(programa);
    }

    @Override
    public void eliminar(Long idPrograma) {
        PgPrograma programa = buscarPorId(idPrograma);
        programa.desactivar();
        programaRepository.save(programa);
    }

    @Override
    public PgPrograma buscarPorId(Long idPrograma) {
        return programaRepository.findById(idPrograma)
                .orElseThrow(() -> new RecursoNoEncontradoException("Programa no encontrado con ID: " + idPrograma));
    }

    @Override
    public PgPrograma buscarPorSigla(String sigla) {
        return programaRepository.findBySigla(sigla)
                .orElseThrow(() -> new RecursoNoEncontradoException("Programa no encontrado con sigla: " + sigla));
    }

    @Override
    public List<PgPrograma> listarTodos() {
        return programaRepository.findAll();
    }

    @Override
    public List<PgPrograma> listarActivos() {
        return programaRepository.listarActivos();
    }

    @Override
    public List<PgPrograma> listarDisponibles() {
        return programaRepository.listarDisponibles();
    }

    @Override
    public List<PgPrograma> buscarPorNombre(String nombre) {
        return programaRepository.buscarPorNombre(nombre);
    }

    @Override
    public List<PgPrograma> listarPorModalidad(Long idModalidad) {
        return programaRepository.findAll();
    }

    @Override
    public List<PgPrograma> listarPorGradoAcademico(Long idGrado) {
        return programaRepository.listarPorGradoAcademico(idGrado);
    }

    @Override
    public PgPrgPlan crearPlan(PgPrgPlan plan) {
        if (plan.getPlan() == null || plan.getPlan().isEmpty()) {
            throw new ValidacionException("El nombre del plan es obligatorio");
        }
        plan.activar();
        return planRepository.save(plan);
    }

    @Override
    public PgPrgPlan buscarPlanPorId(Long idPlan) {
        return planRepository.findById(idPlan)
                .orElseThrow(() -> new RecursoNoEncontradoException("Plan no encontrado con ID: " + idPlan));
    }

    @Override
    public List<PgPrgPlan> listarPlanesPorPrograma(Long idPrograma) {
        return planRepository.listarActivos();
    }

    @Override
    public PgPrgModulo crearModulo(PgPrgModulo modulo) {
        if (modulo.getModulo() == null || modulo.getModulo().isEmpty()) {
            throw new ValidacionException("El nombre del módulo es obligatorio");
        }
        modulo.activar();
        return moduloRepository.save(modulo);
    }

    @Override
    public List<PgPrgModulo> listarModulosPorPlan(Long idPlan) {
        return moduloRepository.listarActivos();
    }

    @Override
    public PgPrgPlnVersion crearVersion(PgPrgPlnVersion version) {
        if (version.getVersion() == null || version.getVersion().isEmpty()) {
            throw new ValidacionException("El nombre de la versión es obligatorio");
        }
        version.activar();
        return versionRepository.save(version);
    }

    @Override
    public List<PgPrgPlnVersion> listarVersionesPorPrograma(Long idPrograma) {
        return versionRepository.listarActivos();
    }

    @Override
    public PgPrgPlnGrupo crearGrupo(PgPrgPlnGrupo grupo) {
        if (grupo.getGrupo() == null || grupo.getGrupo().isEmpty()) {
            throw new ValidacionException("El nombre del grupo es obligatorio");
        }
        grupo.activar();
        return grupoRepository.save(grupo);
    }

    @Override
    public List<PgPrgPlnGrupo> listarGruposPorVersion(Long idVersion) {
        return grupoRepository.listarActivos();
    }
}
