package uap.usic.siga.service.gdoc;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;

public interface GdocArchivosAdjuntosService {

    GdocArchivosAdjuntos guardar(GdocArchivosAdjuntos gdocArchivosAdjuntos);

    GdocArchivosAdjuntos buscarPorId(Long idArchivoAdjunto);

    GdocArchivosAdjuntos actualizar(GdocArchivosAdjuntos gdocArchivosAdjuntos);
}
