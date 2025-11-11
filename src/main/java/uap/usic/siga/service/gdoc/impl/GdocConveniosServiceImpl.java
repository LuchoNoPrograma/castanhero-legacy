package uap.usic.siga.service.gdoc.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocConvenios;
import uap.usic.siga.domain.gdoc.GdocRepresentantes;
import uap.usic.siga.domain.gdoc.GdocTiposConvenios;
import uap.usic.siga.repository.gdoc.GdocConsejosRepository;
import uap.usic.siga.repository.gdoc.GdocConveniosRepository;
import uap.usic.siga.repository.gdoc.GdocRepresentantesRepository;
import uap.usic.siga.repository.gdoc.GdocTiposConveniosRepository;
import uap.usic.siga.service.gdoc.GdocConveniosService;

@Service
@Transactional
public class GdocConveniosServiceImpl implements GdocConveniosService {

    private final GdocConveniosRepository conveniosRepository;
    private final GdocRepresentantesRepository representantesRepository;
    private final GdocTiposConveniosRepository tiposConveniosRepository;
    private final GdocConsejosRepository consejosRepository;

    public GdocConveniosServiceImpl(GdocConveniosRepository conveniosRepository,
                                    GdocRepresentantesRepository representantesRepository,
                                    GdocTiposConveniosRepository tiposConveniosRepository,
                                    GdocConsejosRepository consejosRepository) {
        this.conveniosRepository = conveniosRepository;
        this.representantesRepository = representantesRepository;
        this.tiposConveniosRepository = tiposConveniosRepository;
        this.consejosRepository = consejosRepository;
    }

    @Override
    public List<GdocConvenios> listarPorConsejo(Long idGdocConsejo) {
        GdocConsejos consejo = consejosRepository.findById(idGdocConsejo)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado"));
        return conveniosRepository.findByGdocConsejos(consejo);
    }

    @Override
    public List<GdocConvenios> listarPorTipoYGestion(Long idGdocTipoConvenio, Integer gestion) {
        return conveniosRepository.findAll().stream()
                .filter(c -> c.getGdocTiposConvenios().getIdGdocTipoConvenio().equals(idGdocTipoConvenio))
                .toList();
    }

    @Override
    public List<GdocConvenios> listarPorRangoFechas(Date fecInicio, Date fecFinal) {
        return conveniosRepository.findByRangoFechas(fecInicio, fecFinal);
    }

    @Override
    public GdocConvenios guardar(GdocConvenios gdocConvenios) {
        return conveniosRepository.save(gdocConvenios);
    }

    @Override
    public GdocConvenios buscarPorId(Long idGdocConvenio) {
        return conveniosRepository.findById(idGdocConvenio)
                .orElseThrow(() -> new RuntimeException("Convenio no encontrado"));
    }

    @Override
    public GdocConvenios actualizar(GdocConvenios gdocConvenios) {
        return conveniosRepository.save(gdocConvenios);
    }

    @Override
    public GdocArchivosAdjuntos buscarArchivoPorConvenio(Long idGdocConvenio) {
        GdocConvenios convenio = buscarPorId(idGdocConvenio);
        return convenio.getGdocArchivosAdjuntos();
    }

    @Override
    public List<GdocRepresentantes> listarRepresentantes() {
        return representantesRepository.findAll();
    }

    @Override
    public List<GdocTiposConvenios> listarTiposConvenios() {
        return tiposConveniosRepository.findAll();
    }
}
