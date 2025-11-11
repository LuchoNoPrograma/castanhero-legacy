package uap.usic.siga.modelos;

import java.util.List;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidadesPg.Estudiantes;
import uap.usic.siga.entidadesPg.Matriculas;
import uap.usic.siga.entidadesPg.PgTipoEstudiante;
import uap.usic.siga.entidadesPg.TiposDescuentos;

public interface EstudiantesDao {

	
	public List<Estudiantes> listarEstudiantesPorFiltroGET(String palabraClave);

    public List<Estudiantes> listarEstudiantesGET();

    public List<PgTipoEstudiante> listarTipoEstudianteGET();

    void registrarEstudianteSET(Estudiantes estudiantes);
    
    public void modificarEstudianteSET(Estudiantes estudiantes);

    public void eliminarEstudianteSET(Estudiantes estudiantes);

    public Estudiantes buscarEstudiantePorIdEstudianteGET(Long idEstudiante);
    
    //Modulo Matricula
    
   

    public List<Matriculas> listarMatriculadoGET();

    public List<TiposDescuentos> listarTiposDescuentosGET();

    void registrarMatriculaSET(Matriculas matriculas);
    
    public void modificarMatriculaSET(Matriculas matriculas);

    public void eliminarMatriculaSET(Matriculas matriculas);

    public Matriculas buscarMatriculasPorIdMatriculasGET(Long idMatricula);
}
