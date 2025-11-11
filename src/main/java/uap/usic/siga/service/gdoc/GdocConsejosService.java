package uap.usic.siga.service.gdoc;

import java.util.List;

import uap.usic.siga.domain.gdoc.GdocConsejos;

public interface GdocConsejosService {

    List<GdocConsejos> listarTodos();

    GdocConsejos guardar(GdocConsejos gdocConsejos);

    GdocConsejos buscarPorId(Long idGdocConsejo);

    GdocConsejos buscarPorUsuarioYFuncion(Long idUsuario, Long idMnuTipoFuncion);
}
