package uap.usic.siga.service.gdoc;

import java.util.List;

import uap.usic.siga.domain.gdoc.GdocArchivosAdjuntos;
import uap.usic.siga.domain.gdoc.GdocTiposTitulos;
import uap.usic.siga.domain.gdoc.GdocTiposTitulosGrados;
import uap.usic.siga.domain.gdoc.GdocTitulos;

public interface GdocTitulosService {

    List<GdocTitulos> listarPorConsejo(Long idGdocConsejo);

    GdocTitulos guardar(GdocTitulos gdocTitulos);

    GdocTitulos buscarPorId(Long idGdocTitulo);

    GdocTitulos actualizar(GdocTitulos gdocTitulos);

    GdocArchivosAdjuntos buscarArchivoPorTitulo(Long idGdocTitulo);

    List<GdocTiposTitulos> listarTiposTitulos();

    List<GdocTiposTitulosGrados> listarTiposTitulosGrados();
}
