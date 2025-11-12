package uap.usic.siga.servicios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.modelos.AdministradorDao;
import uap.usic.siga.servicios.AdministradorServicios;

/**
 *
 * @author Usuario
 */
@Service("administradorServicios")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AdministradorServiciosImpl implements AdministradorServicios{
    
    private final AdministradorDao dao;
       

    @Override
    public SisAdministrador buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(Long idMnuTipoFuncion, Long idPersona) {
        return dao.buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(idMnuTipoFuncion, idPersona);
    }
}
