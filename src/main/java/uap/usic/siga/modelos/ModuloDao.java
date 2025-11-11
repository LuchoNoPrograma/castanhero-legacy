package uap.usic.siga.modelos;

import java.util.List;
import java.util.Set;

import uap.usic.siga.entidadesPg.Docentes;
import uap.usic.siga.entidadesPg.EjecucionesModulos;
import uap.usic.siga.entidadesPg.PgPrgModulos;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.entidadesPg.EjecucionModuloEstudiante;

public interface ModuloDao {
    public EjecucionesModulos buscarEjecucionesModulosPorId(Long idEjecucionModulo);//ok
    public EjecucionModuloEstudiante buscarEjecucionModuloEstudiantePorId(Long idEjecucionModuloEstudiante);

    public Set<PgPrgModulos> listarModulosPorDocente(Docentes docente);//ok

    public List<EjecucionesModulos> listarEjecucionesModulosPorIdGrupo(Long idGrupo);
    public List<EjecucionModuloEstudiante> listarEjecucionesModuloEstudiantePorIdModulo(Long idModulo);//ok
    public List<EjecucionModuloEstudiante> listarEjecucionModuloEstudiantePorIdEjecucion(Long idEjecucion);//ok

    public void registrarEjecucionesModulos(EjecucionesModulos ejecucionesModulos);//ok
    public void modificarEjecucionesModulos(EjecucionesModulos ejecucionesModulos);
    public void eliminarEjecucionesModulos(EjecucionesModulos ejecucionesModulos);
    
    public void registrarEjecucionModuloEstudiante(EjecucionModuloEstudiante ejecucionModuloEstudiante);//ok
    public void modificarEjecucionModuloEstudiante(EjecucionModuloEstudiante ejecucionModuloEstudiante);//ok
}
