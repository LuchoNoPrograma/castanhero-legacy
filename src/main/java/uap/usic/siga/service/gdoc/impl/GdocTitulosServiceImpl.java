package uap.usic.siga.service.gdoc.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocTiposTitulos;
import uap.usic.siga.domain.gdoc.GdocTiposTitulosGrados;
import uap.usic.siga.domain.gdoc.GdocTitulos;
import uap.usic.siga.repository.gdoc.GdocConsejosRepository;
import uap.usic.siga.repository.gdoc.GdocTiposTitulosGradosRepository;
import uap.usic.siga.repository.gdoc.GdocTiposTitulosRepository;
import uap.usic.siga.repository.gdoc.GdocTitulosRepository;
import uap.usic.siga.service.gdoc.GdocTitulosService;

@Service
@Transactional
public class GdocTitulosServiceImpl implements GdocTitulosService {

    private final GdocTitulosRepository titulosRepository;
    private final GdocTiposTitulosRepository tiposTitulosRepository;
    private final GdocTiposTitulosGradosRepository tiposTitulosGradosRepository;
    private final GdocConsejosRepository consejosRepository;

    public GdocTitulosServiceImpl(GdocTitulosRepository titulosRepository,
                                  GdocTiposTitulosRepository tiposTitulosRepository,
                                  GdocTiposTitulosGradosRepository tiposTitulosGradosRepository,
                                  GdocConsejosRepository consejosRepository) {
        this.titulosRepository = titulosRepository;
        this.tiposTitulosRepository = tiposTitulosRepository;
        this.tiposTitulosGradosRepository = tiposTitulosGradosRepository;
        this.consejosRepository = consejosRepository;
    }

    @Override
    public List<GdocTitulos> listarPorConsejo(Long idGdocConsejo) {
        GdocConsejos consejo = consejosRepository.findById(idGdocConsejo)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado"));
        return titulosRepository.findByGdocConsejos(consejo);
    }

    @Override
    public GdocTitulos guardar(GdocTitulos gdocTitulos) {
        return titulosRepository.save(gdocTitulos);
    }

    @Override
    public GdocTitulos buscarPorId(Long idGdocTitulo) {
        return titulosRepository.findById(idGdocTitulo)
                .orElseThrow(() -> new RuntimeException("Titulo no encontrado"));
    }

    @Override
    public GdocTitulos actualizar(GdocTitulos gdocTitulos) {
        return titulosRepository.save(gdocTitulos);
    }

    @Override
    public GdocArchivosAdjuntos buscarArchivoPorTitulo(Long idGdocTitulo) {
        GdocTitulos titulo = buscarPorId(idGdocTitulo);
        return titulo.getGdocArchivosAdjuntos();
    }

    @Override
    public List<GdocTiposTitulos> listarTiposTitulos() {
        return tiposTitulosRepository.findAll();
    }

    @Override
    public List<GdocTiposTitulosGrados> listarTiposTitulosGrados() {
        return tiposTitulosGradosRepository.findAll();
    }
}
