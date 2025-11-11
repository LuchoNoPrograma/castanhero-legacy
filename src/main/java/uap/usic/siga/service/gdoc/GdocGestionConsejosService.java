package uap.usic.siga.service.gdoc;

import java.util.List;

import uap.usic.siga.domain.gdoc.GdocGestionConsejos;

public interface GdocGestionConsejosService {

    List<GdocGestionConsejos> listarPorConsejo(Long idGdocConsejo);

    GdocGestionConsejos guardar(GdocGestionConsejos gdocGestionConsejos);

    GdocGestionConsejos buscarPorConsejoYGestion(Long idGdocConsejo, Integer gestion);
}
