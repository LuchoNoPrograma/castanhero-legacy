package uap.usic.siga.servicios;

import java.util.List;

import uap.usic.siga.entidadesPg.Docentes;

public interface DocentesServicios {
    public Docentes buscarDocentePorId(Long idDocente);

    public Docentes buscarDocentesPorCi(String ciDocente);

    public void registrarDocentes(Docentes docentes);

    public void modificarDocentes(Docentes docentes);

    public void eliminarDocentes(Docentes docentes);

    public List<Docentes> listarDocentesPorPaterno();
}
