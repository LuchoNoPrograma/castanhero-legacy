package uap.usic.siga.service.gdoc.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.repository.gdoc.GdocArchivosAdjuntosRepository;
import uap.usic.siga.service.gdoc.GdocArchivosAdjuntosService;

@Service
@Transactional
public class GdocArchivosAdjuntosServiceImpl implements GdocArchivosAdjuntosService {

    private final GdocArchivosAdjuntosRepository archivosAdjuntosRepository;

    public GdocArchivosAdjuntosServiceImpl(GdocArchivosAdjuntosRepository archivosAdjuntosRepository) {
        this.archivosAdjuntosRepository = archivosAdjuntosRepository;
    }

    @Override
    public GdocArchivosAdjuntos guardar(GdocArchivosAdjuntos gdocArchivosAdjuntos) {
        return archivosAdjuntosRepository.save(gdocArchivosAdjuntos);
    }

    @Override
    public GdocArchivosAdjuntos buscarPorId(Long idArchivoAdjunto) {
        return archivosAdjuntosRepository.findById(idArchivoAdjunto)
                .orElseThrow(() -> new RuntimeException("Archivo adjunto no encontrado"));
    }

    @Override
    public GdocArchivosAdjuntos actualizar(GdocArchivosAdjuntos gdocArchivosAdjuntos) {
        return archivosAdjuntosRepository.save(gdocArchivosAdjuntos);
    }
}
