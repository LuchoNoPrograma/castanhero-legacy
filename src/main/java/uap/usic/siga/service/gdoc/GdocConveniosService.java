package uap.usic.siga.service.gdoc;

import java.util.Date;
import java.util.List;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.domain.gdoc.GdocConvenios;
import uap.usic.siga.domain.gdoc.GdocRepresentantes;
import uap.usic.siga.domain.gdoc.GdocTiposConvenios;

public interface GdocConveniosService {

    List<GdocConvenios> listarPorConsejo(Long idGdocConsejo);

    List<GdocConvenios> listarPorTipoYGestion(Long idGdocTipoConvenio, Integer gestion);

    List<GdocConvenios> listarPorRangoFechas(Date fecInicio, Date fecFinal);

    GdocConvenios guardar(GdocConvenios gdocConvenios);

    GdocConvenios buscarPorId(Long idGdocConvenio);

    GdocConvenios actualizar(GdocConvenios gdocConvenios);

    GdocArchivosAdjuntos buscarArchivoPorConvenio(Long idGdocConvenio);

    List<GdocRepresentantes> listarRepresentantes();

    List<GdocTiposConvenios> listarTiposConvenios();
}
