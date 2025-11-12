
package uap.usic.siga.servicios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuTiposEnlaces;
import uap.usic.siga.modelos.MnuEnlacesDao;
import uap.usic.siga.servicios.MnuEnlacesServicios;

/**
 *
 * @author Yessenia
 */
@Service("mnuEnlacesServicio")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MnuEnlacesServiciosImpl implements MnuEnlacesServicios{
    
    private final MnuEnlacesDao dao;

    @Override
    public List<MnuTiposEnlaces> listarMnuTiposEnlaces() {
      return dao.listarMnuTiposEnlacesJPQL();
    }

    @Override
    public List<MnuEnlaces> listarMnuEnlaces() {
    return dao.listarMnuEnlacesJPQL();
    }

    @Override
    public void registrarMnuTiposEnlaces(MnuTiposEnlaces mnuTiposEnlaces) {
      dao.registrarMnuTiposEnlacesSET(mnuTiposEnlaces);
    }

    @Override
    public void registrarMnuEnlaces(MnuEnlaces mnuEnlaces) {
    dao.registrarMnuEnlacesSET(mnuEnlaces);
    }

    @Override
    public void modificarMnuEnlaces(MnuEnlaces mnuEnlaces) {
      dao.modificarMnuEnlacesSET(mnuEnlaces);
    }

    @Override
    public void EliminarMnuEnlaces(MnuEnlaces mnuEnlaces) {
     dao.EliminarMnuEnlacesSET(mnuEnlaces);
    }

    @Override
    public MnuEnlaces buscarMnuEnlacesPorIdMnuEnlace(Long idMnuEnlace) {
    return dao.buscarMnuEnlacesPorIdMnuEnlaceGET(idMnuEnlace);
    }
    
}
