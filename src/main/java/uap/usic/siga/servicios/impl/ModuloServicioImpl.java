package uap.usic.siga.servicios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Set;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import uap.usic.siga.entidadesPg.Docentes;
import uap.usic.siga.entidadesPg.EjecucionModuloEstudiante;
import uap.usic.siga.entidadesPg.EjecucionesModulos;
import uap.usic.siga.entidadesPg.PgPrgModulos;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.modelos.ModuloDao;
import uap.usic.siga.servicios.ModuloServicio;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ModuloServicioImpl implements ModuloServicio{
    private final ModuloDao dao;

    @Override
    public EjecucionesModulos buscarEjecucionesModulosPorId(Long idEjecucionModulo) {
        return dao.buscarEjecucionesModulosPorId(idEjecucionModulo);
    }

    @Override
    public EjecucionModuloEstudiante buscarEjecucionModuloEstudiantePorId(Long idEjecucionModuloEstudiante){
        return dao.buscarEjecucionModuloEstudiantePorId(idEjecucionModuloEstudiante);
    }

    @Override
    public Set<PgPrgModulos> listarModulosPorDocente(Docentes docente) {
        return dao.listarModulosPorDocente(docente);
    }

    @Override
    public List<EjecucionesModulos> listarEjecucionesModulosPorIdGrupo(Long idGrupo){
        return dao.listarEjecucionesModulosPorIdGrupo(idGrupo);
    }

    @Override
    public List<EjecucionModuloEstudiante> listarEjecucionesModuloEstudiantePorIdModulo(Long idModulo) {
        return dao.listarEjecucionesModuloEstudiantePorIdModulo(idModulo);
    }

    @Override
    public List<EjecucionModuloEstudiante> listarEjecucionModuloEstudiantePorIdEjecucion(Long idEjecucion){
        return dao.listarEjecucionModuloEstudiantePorIdEjecucion(idEjecucion);
    }

    @Override
    public void registrarEjecucionesModulos(EjecucionesModulos ejecucionesModulos) {
        dao.registrarEjecucionesModulos(ejecucionesModulos);
    }

    @Override
    public void modificarEjecucionesModulos(EjecucionesModulos ejecucionesModulos) {
        dao.modificarEjecucionesModulos(ejecucionesModulos);
    }

    @Override
    public void eliminarEjecucionesModulos(EjecucionesModulos ejecucionesModulos) {
        dao.eliminarEjecucionesModulos(ejecucionesModulos);
    }

    @Override
    public void registrarEjecucionModuloEstudiante(EjecucionModuloEstudiante ejecucionModuloEstudiante) {
        dao.registrarEjecucionModuloEstudiante(ejecucionModuloEstudiante);
    }

    @Override
    public void modificarEjecucionModuloEstudiante(EjecucionModuloEstudiante ejecucionModuloEstudiante){
        dao.modificarEjecucionModuloEstudiante(ejecucionModuloEstudiante);
    }
    
}
