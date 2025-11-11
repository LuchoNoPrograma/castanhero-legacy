package uap.usic.siga.service.gdoc.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.domain.gdoc.GdocAutoridades;
import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocGestionConsejos;
import uap.usic.siga.domain.gdoc.GdocResoluciones;
import uap.usic.siga.domain.gdoc.GdocResolucionesDigitales;
import uap.usic.siga.repository.gdoc.GdocAutoridadesRepository;
import uap.usic.siga.repository.gdoc.GdocConsejosRepository;
import uap.usic.siga.repository.gdoc.GdocGestionConsejosRepository;
import uap.usic.siga.repository.gdoc.GdocResolucionesDigitalesRepository;
import uap.usic.siga.repository.gdoc.GdocResolucionesRepository;
import uap.usic.siga.service.gdoc.GdocResolucionesService;

@Service
@Transactional
public class GdocResolucionesServiceImpl implements GdocResolucionesService {

    private final GdocResolucionesRepository resolucionesRepository;
    private final GdocAutoridadesRepository autoridadesRepository;
    private final GdocResolucionesDigitalesRepository resolucionesDigitalesRepository;
    private final GdocConsejosRepository consejosRepository;
    private final GdocGestionConsejosRepository gestionConsejosRepository;

    public GdocResolucionesServiceImpl(GdocResolucionesRepository resolucionesRepository,
                                       GdocAutoridadesRepository autoridadesRepository,
                                       GdocResolucionesDigitalesRepository resolucionesDigitalesRepository,
                                       GdocConsejosRepository consejosRepository,
                                       GdocGestionConsejosRepository gestionConsejosRepository) {
        this.resolucionesRepository = resolucionesRepository;
        this.autoridadesRepository = autoridadesRepository;
        this.resolucionesDigitalesRepository = resolucionesDigitalesRepository;
        this.consejosRepository = consejosRepository;
        this.gestionConsejosRepository = gestionConsejosRepository;
    }

    @Override
    public List<GdocResoluciones> listarPorConsejo(Long idGdocConsejo) {
        GdocConsejos consejo = consejosRepository.findById(idGdocConsejo)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado"));
        return resolucionesRepository.findByGdocConsejos(consejo);
    }

    @Override
    public List<GdocAutoridades> listarAutoridades(Long idGdocConsejo) {
        GdocConsejos consejo = consejosRepository.findById(idGdocConsejo)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado"));
        return autoridadesRepository.findByGdocConsejos(consejo);
    }

    @Override
    public GdocResoluciones guardar(GdocResoluciones gdocResoluciones) {
        return resolucionesRepository.save(gdocResoluciones);
    }

    @Override
    public GdocResoluciones buscarPorId(Long idGdocResolucion) {
        return resolucionesRepository.findById(idGdocResolucion)
                .orElseThrow(() -> new RuntimeException("Resolucion no encontrada"));
    }

    @Override
    public GdocResoluciones actualizar(GdocResoluciones gdocResoluciones) {
        return resolucionesRepository.save(gdocResoluciones);
    }

    @Override
    public GdocArchivosAdjuntos buscarArchivoPorResolucion(Long idGdocResolucion) {
        GdocResoluciones resolucion = buscarPorId(idGdocResolucion);
        return resolucion.getGdocArchivosAdjuntos();
    }

    @Override
    public List<GdocResolucionesDigitales> listarResolucionesDigitales(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion) {
        GdocGestionConsejos gestionConsejo = gestionConsejosRepository.findById(idGdocGestionConsejo)
                .orElseThrow(() -> new RuntimeException("Gestion de consejo no encontrada"));
        return resolucionesDigitalesRepository.findByGdocGestionConsejos(gestionConsejo);
    }

    @Override
    public GdocResolucionesDigitales guardarResolucionDigital(GdocResolucionesDigitales gdocResolucionesDigitales) {
        return resolucionesDigitalesRepository.save(gdocResolucionesDigitales);
    }

    @Override
    public GdocResolucionesDigitales buscarResolucionDigitalPorId(Long idGdocResolucionDigital) {
        return resolucionesDigitalesRepository.findById(idGdocResolucionDigital)
                .orElseThrow(() -> new RuntimeException("Resolucion digital no encontrada"));
    }
}
