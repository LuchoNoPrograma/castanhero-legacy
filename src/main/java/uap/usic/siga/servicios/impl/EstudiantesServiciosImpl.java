package uap.usic.siga.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.entidadesPg.Estudiantes;
import uap.usic.siga.entidadesPg.Matriculas;
import uap.usic.siga.entidadesPg.PgTipoEstudiante;
import uap.usic.siga.entidadesPg.TiposDescuentos;
import uap.usic.siga.modelos.EstudiantesDao;
import uap.usic.siga.servicios.EstudiantesServicios;


@Service("estudiantesServicios")
@Transactional
public class EstudiantesServiciosImpl implements EstudiantesServicios {

	@Autowired
    private EstudiantesDao dao;
	
	@Override
	public List<Estudiantes> listarEstudiantesPorFiltro(String palabraClave) {
		
		return dao.listarEstudiantesPorFiltroGET(palabraClave);
	}

	@Override
	public List<Estudiantes> listarEstudiantes() {
		
		return dao.listarEstudiantesGET();
	}

	@Override
	public List<PgTipoEstudiante> listarTipoEstudiante() {
		
		return dao.listarTipoEstudianteGET();
	}

	@Override
	public void registrarEstudiante(Estudiantes estudiantes) {
		dao.registrarEstudianteSET(estudiantes);

	}

	@Override
	public void modificarEstudiante(Estudiantes estudiantes) {
		dao.modificarEstudianteSET(estudiantes);

	}

	@Override
	public void eliminarEstudiante(Estudiantes estudiantes) {
		dao.eliminarEstudianteSET(estudiantes);

	}

	@Override
	public Estudiantes buscarEstudiantePorIdEstudiante(Long idEstudiante) {
		
		return dao.buscarEstudiantePorIdEstudianteGET(idEstudiante);
	}

	@Override
	public List<Matriculas> listarMatriculado() {
		
		return dao.listarMatriculadoGET();
	}

	@Override
	public List<TiposDescuentos> listarTiposDescuentos() {
		
		return dao.listarTiposDescuentosGET();
	}

	@Override
	public void registrarMatricula(Matriculas matriculas) {
		dao.registrarMatriculaSET(matriculas);
		
	}

	@Override
	public void modificarMatricula(Matriculas matriculas) {
		dao.modificarMatriculaSET(matriculas);
		
	}

	@Override
	public void eliminarMatricula(Matriculas matriculas) {
		dao.eliminarMatriculaSET(matriculas);
		
	}

	@Override
	public Matriculas buscarMatriculasPorIdMatriculas(Long idMatricula) {
		
		return dao.buscarMatriculasPorIdMatriculasGET(idMatricula);
	}

}
