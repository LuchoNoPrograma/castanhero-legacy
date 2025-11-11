package uap.usic.siga.servicios.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uap.usic.siga.entidadesPg.Docentes;
import uap.usic.siga.modelos.DocentesDao;
import uap.usic.siga.servicios.DocentesServicios;

@Service
@Transactional
public class DocentesServiciosImpl implements DocentesServicios{
    @Autowired
    private DocentesDao dao;

    @Override
    public Docentes buscarDocentePorId(Long idDocente) {
        return dao.buscarDocentePorId(idDocente);
    }

    @Override
    public Docentes buscarDocentesPorCi(String ciDocente) {
        return dao.buscarDocentesPorCi(ciDocente);
    }

    @Override
    public void registrarDocentes(Docentes docentes) {
        dao.registrarDocentes(docentes);
    }

    @Override
    public void modificarDocentes(Docentes docentes) {
        dao.modificarDocentes(docentes);
    }

    @Override
    public void eliminarDocentes(Docentes docentes) {
        dao.modificarDocentes(docentes);
    }

    @Override
    public List<Docentes> listarDocentesPorPaterno() {
        return dao.listarDocentesPorPaterno();
    }
    
}
