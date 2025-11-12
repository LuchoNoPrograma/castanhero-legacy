package uap.usic.siga.servicios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.Menues;
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuFunciones;
import uap.usic.siga.entidades.MnuTiposEnlaces;
import uap.usic.siga.entidades.MnuTiposFunciones;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.entidades.SisNivelesAccesos;
import uap.usic.siga.modelos.MenuesDao;
import uap.usic.siga.servicios.MenuesServicios;

/**
 *
 * @author fmbma
 */
@Service("menuesServicio")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MenuesServiciosImpl implements MenuesServicios {

    private final MenuesDao dao;

    @Override
    public List<MnuTiposEnlaces> listarMenuesTiposEnlacesJPQL(Long idPnlCargo, Long idPersona) {
        return dao.listarMenuesTiposEnlacesJPQL(idPnlCargo, idPersona);
    }

    @Override
    public List<MnuEnlaces> listarMenuesEnlaces(Long idPnlCargo, Long idPersona) {
        return dao.listarMenuesEnlacesJPQL(idPnlCargo, idPersona);
    }

    @Override
    public List<MnuFunciones> listarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(Long idPersona, Long idMnuTipoFuncion) {
        return dao.listarMenusFuncionesPorIdPersonaIdMnuTipoFuncionJPQL(idPersona, idMnuTipoFuncion);
    }

    @Override
    public List<MnuTiposFunciones> listarMenusTiposFuncionesPorIdPersona(Long idPersona) {
        return dao.listarMenusTiposFuncionesPorIdPersonaJPQL(idPersona);
    }

    @Override
    public List<MnuEnlaces> listarMenuesEnlacesPorIdMnuTipoFuncion(Long idMnuTipoFuncion) {
        return dao.listarMenuesEnlacesPorIdMnuTipoFuncionJPQL(idMnuTipoFuncion);
    }

    @Override
    public List<MnuTiposEnlaces> listarMenuesTiposEnlacesPorIdMnuTipoFuncion(Long idMnuTipoFuncion) {
        return dao.listarMenuesTiposEnlacesPorIdMnuTipoFuncionJPQL(idMnuTipoFuncion);
    }

    @Override
    public MnuFunciones buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(Long idPersona, Long idMnuTipoFuncion,Long idSisAdministrador) {
        return dao.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncionGET(idPersona, idMnuTipoFuncion, idSisAdministrador);
    }

    @Override
    public List<Menues> listarMenues() {
        return dao.listarMenuesJPQL();

    }

    @Override
    public List<MnuTiposFunciones> listarMnuTiposFunciones() {
        return dao.listarMnuTiposFuncionesJPQL();
    }

    @Override
    public void registrarMenues(Menues menues) {
        dao.registrarMenuesSET(menues);
    }

    @Override
    public void registrarMnuTiposFunciones(MnuTiposFunciones mnuTiposFunciones) {
        dao.registrarMnuTiposFuncionesSET(mnuTiposFunciones);
    }

    @Override
    public void modificarMenues(Menues menues) {
        dao.modificarMenuesSET(menues);

    }

    @Override
    public void EliminarMenues(Menues menues) {
        dao.EliminarMenuesSET(menues);
    }

    @Override
    public Menues buscarMenuesPorIdMenu(Long idMenu) {
        return dao.buscarMenuesPorIdMenuGET(idMenu);

    }

    @Override
    public List<MnuFunciones> listarMnuFunciones() {
        return dao.listarMnuFuncionesJPQL();
    }

    @Override
    public List<SisAdministrador> listarSisAdministrador() {
        return dao.listarSisAdministradorJPQL();
    }

    @Override
    public List<SisNivelesAccesos> listarSisNivelesAccesos() {
        return dao.listarSisNivelesAccesosJPQL();
    }

    @Override
    public void registrarMnuFunciones(MnuFunciones mnuFunciones) {
        dao.registrarMnuFuncionesSET(mnuFunciones);
    }

    @Override
    public void registrarSisAdministrador(SisAdministrador sisAdministrador) {
        dao.registrarSisAdministradorSET(sisAdministrador);
    }

    @Override
    public void modificarMnuFunciones(MnuFunciones mnuFunciones) {
        dao.modificarMnuFuncionesSET(mnuFunciones);
    }

    @Override
    public void eliminaMnuFunciones(MnuFunciones mnuFunciones) {
        dao.eliminaMnuFuncionesSET(mnuFunciones);
    }

    @Override
    public MnuFunciones buscarMnuFuncionesPorIdMnuFuncion(Long idMnuFuncion) {
        return dao.buscarMnuFuncionesPorIdMnuFuncionGET(idMnuFuncion);
    }

    @Override
    public void registrarSisNivelesAccesos(SisNivelesAccesos sisNivelesAccesos) {
        dao.registrarSisNivelesAccesosSET(sisNivelesAccesos);
    }

    @Override
    public void registrarTipoEnlace(MnuTiposEnlaces menuTipoEnlace){
        dao.registrarTipoEnlace(menuTipoEnlace);
    }

    @Override
    public void registrarEnlace(MnuEnlaces enlace){
        dao.registrarEnlace(enlace);
    }
}
