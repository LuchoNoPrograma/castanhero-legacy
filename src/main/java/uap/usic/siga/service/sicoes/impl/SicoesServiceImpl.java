package uap.usic.siga.service.sicoes.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.domain.sicoes.*;
import uap.usic.siga.repository.sicoes.*;
import uap.usic.siga.service.sicoes.SicoesService;

import java.util.List;

@Service
@Transactional
public class SicoesServiceImpl implements SicoesService {

    @Autowired
    private ScsBoletaRespaldatoriaRepository boletaRepository;

    @Autowired
    private ScsFormularioRepository formularioRepository;

    @Autowired
    private ScsProyectoRepository proyectoRepository;

    @Autowired
    private ScsModalidadRepository modalidadRepository;

    @Autowired
    private ScsTipoModalidadRepository tipoModalidadRepository;

    @Autowired
    private ScsTipoContratoRepository tipoContratoRepository;

    @Autowired
    private ScsArchivoAdjuntoRepository archivoAdjuntoRepository;

    @Autowired
    private ScsContratacionRepository contratacionRepository;

    @Autowired
    private ScsPrsContratadoRepository prsContratadoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ScsBoletaRespaldatoria> listarBoletasRespaldatorias() {
        return boletaRepository.findByIdEstadoNot("X");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsFormulario> listarFormularios() {
        return formularioRepository.findByIdEstadoNot("X");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsProyecto> listarProyectos() {
        return proyectoRepository.findByIdEstadoNot("X");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsModalidad> listarModalidades() {
        return modalidadRepository.findByIdEstadoNot("X");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsTipoModalidad> listarTiposModalidades() {
        return tipoModalidadRepository.findByIdEstadoNot("X");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsTipoContrato> listarTiposContratos() {
        return tipoContratoRepository.findByIdEstadoNot("X");
    }

    @Override
    public ScsArchivoAdjunto registrarArchivoAdjunto(ScsArchivoAdjunto archivoAdjunto) {
        return archivoAdjuntoRepository.save(archivoAdjunto);
    }

    @Override
    public ScsContratacion registrarContratacion(ScsContratacion contratacion) {
        return contratacionRepository.save(contratacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContrataciones() {
        return contratacionRepository.findByIdEstadoNot("X");
    }

    @Override
    @Transactional(readOnly = true)
    public ScsArchivoAdjunto buscarArchivoAdjuntoPorId(Long idArchivoAdjunto) {
        return archivoAdjuntoRepository.findById(idArchivoAdjunto).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public ScsContratacion buscarContratacionPorId(Long idContratacion) {
        return contratacionRepository.findById(idContratacion).orElse(null);
    }

    @Override
    public void modificarContratacion(ScsContratacion contratacion) {
        contratacionRepository.save(contratacion);
    }

    @Override
    public void modificarArchivoAdjunto(ScsArchivoAdjunto archivoAdjunto) {
        archivoAdjuntoRepository.save(archivoAdjunto);
    }

    @Override
    @Transactional(readOnly = true)
    public Long extraerMaximoIdContratacion() {
        Long maxId = contratacionRepository.findMaxId();
        return maxId != null ? maxId : 0L;
    }

    @Override
    public ScsProyecto registrarProyecto(ScsProyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void actualizarProyecto(ScsProyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    @Override
    @Transactional(readOnly = true)
    public ScsProyecto buscarProyectoPorId(Long idProyecto) {
        return proyectoRepository.findById(idProyecto).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorModalidadYGestion(Long idModalidad, Integer gestion) {
        return contratacionRepository.findByModalidadAndGestion(idModalidad, gestion);
    }

    @Override
    @Transactional(readOnly = true)
    public ScsModalidad buscarModalidadPorId(Long idModalidad) {
        return modalidadRepository.findById(idModalidad).orElse(null);
    }

    @Override
    public ScsPrsContratado registrarPrsContratado(ScsPrsContratado prsContratado) {
        return prsContratadoRepository.save(prsContratado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsPrsContratado> listarPrsContratados() {
        return prsContratadoRepository.findByIdEstadoNot("X");
    }

    @Override
    @Transactional(readOnly = true)
    public ScsPrsContratado buscarPrsContratadoPorId(Long idPrsContratado) {
        return prsContratadoRepository.findById(idPrsContratado).orElse(null);
    }

    @Override
    public void actualizarPrsContratado(ScsPrsContratado prsContratado) {
        prsContratadoRepository.save(prsContratado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorProyectoYGestion(Long idProyecto, Integer gestion) {
        return contratacionRepository.findByProyectoAndGestion(idProyecto, gestion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorPersonaYGestion(Long idPersona, Integer gestion) {
        return contratacionRepository.findByPersonaAndGestion(idPersona, gestion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorDireccionYGestion(Long idDireccion, Integer gestion) {
        return contratacionRepository.findByDireccionFuncionalAndGestion(idDireccion, gestion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorDireccion(Long idDireccion) {
        return contratacionRepository.findByDireccionFuncional(idDireccion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorPersona(Long idPersona) {
        return contratacionRepository.findByPersona(idPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorGestion(Integer gestion) {
        return contratacionRepository.findByGestionAndIdEstadoNot(gestion, "X");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorUnidadYGestion(Long idUnidad, Integer gestion) {
        return contratacionRepository.findByUnidadFuncionalAndGestion(idUnidad, gestion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorUnidad(Long idUnidad) {
        return contratacionRepository.findByUnidadFuncional(idUnidad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorProyecto(Long idProyecto) {
        return contratacionRepository.findByProyecto(idProyecto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScsContratacion> listarContratacionesPorModalidad(Long idModalidad) {
        return contratacionRepository.findByModalidad(idModalidad);
    }
}
