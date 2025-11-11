package uap.usic.siga.service.gdoc.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocGestionConsejos;
import uap.usic.siga.repository.gdoc.GdocConsejosRepository;
import uap.usic.siga.repository.gdoc.GdocGestionConsejosRepository;
import uap.usic.siga.service.gdoc.GdocGestionConsejosService;

@Service
@Transactional
public class GdocGestionConsejosServiceImpl implements GdocGestionConsejosService {

    private final GdocGestionConsejosRepository gestionConsejosRepository;
    private final GdocConsejosRepository consejosRepository;

    public GdocGestionConsejosServiceImpl(GdocGestionConsejosRepository gestionConsejosRepository,
                                          GdocConsejosRepository consejosRepository) {
        this.gestionConsejosRepository = gestionConsejosRepository;
        this.consejosRepository = consejosRepository;
    }

    @Override
    public List<GdocGestionConsejos> listarPorConsejo(Long idGdocConsejo) {
        GdocConsejos consejo = consejosRepository.findById(idGdocConsejo)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado"));
        return gestionConsejosRepository.findByGdocConsejos(consejo);
    }

    @Override
    public GdocGestionConsejos guardar(GdocGestionConsejos gdocGestionConsejos) {
        return gestionConsejosRepository.save(gdocGestionConsejos);
    }

    @Override
    public GdocGestionConsejos buscarPorConsejoYGestion(Long idGdocConsejo, Integer gestion) {
        List<GdocGestionConsejos> gestiones = listarPorConsejo(idGdocConsejo).stream()
                .filter(g -> g.getGestionConsejo().equals(gestion.toString()))
                .toList();

        if (!gestiones.isEmpty()) {
            return gestiones.get(0);
        }
        throw new RuntimeException("No se encontro gestion de consejo");
    }
}
