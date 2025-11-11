package uap.usic.siga.service.gdoc.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocTitulados;
import uap.usic.siga.repository.gdoc.GdocConsejosRepository;
import uap.usic.siga.repository.gdoc.GdocTituladosRepository;
import uap.usic.siga.service.gdoc.GdocTituladosService;

@Service
@Transactional
public class GdocTituladosServiceImpl implements GdocTituladosService {

    private final GdocTituladosRepository tituladosRepository;
    private final GdocConsejosRepository consejosRepository;

    public GdocTituladosServiceImpl(GdocTituladosRepository tituladosRepository,
                                    GdocConsejosRepository consejosRepository) {
        this.tituladosRepository = tituladosRepository;
        this.consejosRepository = consejosRepository;
    }

    @Override
    public List<GdocTitulados> listarPorConsejo(Long idGdocConsejo) {
        GdocConsejos consejo = consejosRepository.findById(idGdocConsejo)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado"));
        return tituladosRepository.findByGdocConsejos(consejo);
    }

    @Override
    public GdocTitulados guardar(GdocTitulados gdocTitulados) {
        return tituladosRepository.save(gdocTitulados);
    }

    @Override
    public GdocTitulados buscarPorId(Long idGdocTitulado) {
        return tituladosRepository.findById(idGdocTitulado)
                .orElseThrow(() -> new RuntimeException("Titulado no encontrado"));
    }

    @Override
    public GdocTitulados actualizar(GdocTitulados gdocTitulados) {
        return tituladosRepository.save(gdocTitulados);
    }

    @Override
    public GdocArchivosAdjuntos buscarArchivoPorTitulado(Long idGdocTitulado) {
        GdocTitulados titulado = buscarPorId(idGdocTitulado);
        return titulado.getGdocArchivosAdjuntos();
    }
}
