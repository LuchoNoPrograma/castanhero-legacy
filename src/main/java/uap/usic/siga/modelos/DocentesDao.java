package uap.usic.siga.modelos;

import java.util.List;

import uap.usic.siga.entidadesPg.Docentes;

public interface DocentesDao {

    public Docentes buscarDocentePorId(Long idDocente);

    public Docentes buscarDocentesPorCi(String ciDocente);

    public void registrarDocentes(Docentes docentes);

    public void modificarDocentes(Docentes docentes);

    public void eliminarDocentes(Docentes docentes);

    public List<Docentes> listarDocentesPorPaterno();

}
