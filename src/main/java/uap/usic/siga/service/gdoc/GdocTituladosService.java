package uap.usic.siga.service.gdoc;

import java.util.List;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.domain.gdoc.GdocTitulados;

public interface GdocTituladosService {

    List<GdocTitulados> listarPorConsejo(Long idGdocConsejo);

    GdocTitulados guardar(GdocTitulados gdocTitulados);

    GdocTitulados buscarPorId(Long idGdocTitulado);

    GdocTitulados actualizar(GdocTitulados gdocTitulados);

    GdocArchivosAdjuntos buscarArchivoPorTitulado(Long idGdocTitulado);
}
