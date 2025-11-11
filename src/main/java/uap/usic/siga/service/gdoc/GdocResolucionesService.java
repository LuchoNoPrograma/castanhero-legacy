package uap.usic.siga.service.gdoc;

import java.util.List;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.domain.gdoc.GdocAutoridades;
import uap.usic.siga.domain.gdoc.GdocResoluciones;
import uap.usic.siga.domain.gdoc.GdocResolucionesDigitales;

public interface GdocResolucionesService {

    List<GdocResoluciones> listarPorConsejo(Long idGdocConsejo);

    List<GdocAutoridades> listarAutoridades(Long idGdocConsejo);

    GdocResoluciones guardar(GdocResoluciones gdocResoluciones);

    GdocResoluciones buscarPorId(Long idGdocResolucion);

    GdocResoluciones actualizar(GdocResoluciones gdocResoluciones);

    GdocArchivosAdjuntos buscarArchivoPorResolucion(Long idGdocResolucion);

    List<GdocResolucionesDigitales> listarResolucionesDigitales(Long idGdocConsejo, Long idGdocGestionConsejo, Integer gestion);

    GdocResolucionesDigitales guardarResolucionDigital(GdocResolucionesDigitales gdocResolucionesDigitales);

    GdocResolucionesDigitales buscarResolucionDigitalPorId(Long idGdocResolucionDigital);
}
