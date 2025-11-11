package uap.usic.siga.servicios;

import java.util.List;
import java.util.Set;

import uap.usic.siga.entidadesPg.Docentes;
import uap.usic.siga.entidadesPg.EjecucionModuloEstudiante;
import uap.usic.siga.entidadesPg.EjecucionesModulos;
import uap.usic.siga.entidadesPg.PgPrgModulos;
import uap.usic.siga.entidadesPg.PgPstProgramas;

public interface ModuloServicio {
    public EjecucionesModulos buscarEjecucionesModulosPorId(Long idEjecucionModulo);
    public EjecucionModuloEstudiante buscarEjecucionModuloEstudiantePorId(Long idEjecucionModuloEstudiante);
    public Set<PgPrgModulos> listarModulosPorDocente(Docentes docente);

    public List<EjecucionesModulos> listarEjecucionesModulosPorIdGrupo(Long idGrupo);
    public List<EjecucionModuloEstudiante> listarEjecucionesModuloEstudiantePorIdModulo(Long idModulo);
    public List<EjecucionModuloEstudiante> listarEjecucionModuloEstudiantePorIdEjecucion(Long idEjecucion);

    public void registrarEjecucionesModulos(EjecucionesModulos ejecucionesModulos);
    public void modificarEjecucionesModulos(EjecucionesModulos ejecucionesModulos);
    public void eliminarEjecucionesModulos(EjecucionesModulos ejecucionesModulos);
    
    public void registrarEjecucionModuloEstudiante(EjecucionModuloEstudiante ejecucionModuloEstudiante);
    public void modificarEjecucionModuloEstudiante(EjecucionModuloEstudiante ejecucionModuloEstudiante);
}
