package uap.usic.siga.servicios;

import java.util.List;

import uap.usic.siga.entidadesPg.Estudiantes;
import uap.usic.siga.entidadesPg.Matriculas;
import uap.usic.siga.entidadesPg.PgTipoEstudiante;
import uap.usic.siga.entidadesPg.TiposDescuentos;

public interface EstudiantesServicios {
	
	public List<Estudiantes> listarEstudiantesPorFiltro(String palabraClave);

    public List<Estudiantes> listarEstudiantes();

    public List<PgTipoEstudiante> listarTipoEstudiante();

    void registrarEstudiante(Estudiantes estudiantes);
    
    public void modificarEstudiante(Estudiantes estudiantes);

    public void eliminarEstudiante(Estudiantes estudiantes);

    public Estudiantes buscarEstudiantePorIdEstudiante(Long idEstudiante);
    
    //modulo matriculas
    
    public List<Matriculas> listarMatriculado();

    public List<TiposDescuentos> listarTiposDescuentos();

    void registrarMatricula(Matriculas matriculas);
    
    public void modificarMatricula(Matriculas matriculas);

    public void eliminarMatricula(Matriculas matriculas);

    public Matriculas buscarMatriculasPorIdMatriculas(Long idMatricula);
}
