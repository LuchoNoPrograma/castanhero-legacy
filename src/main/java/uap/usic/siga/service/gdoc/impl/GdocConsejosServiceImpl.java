package uap.usic.siga.service.gdoc.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocUsrTiposFunciones;
import uap.usic.siga.repository.gdoc.GdocConsejosRepository;
import uap.usic.siga.repository.gdoc.GdocUsrTiposFuncionesRepository;
import uap.usic.siga.service.gdoc.GdocConsejosService;

@Service
@Transactional
public class GdocConsejosServiceImpl implements GdocConsejosService {

    private final GdocConsejosRepository consejosRepository;
    private final GdocUsrTiposFuncionesRepository usrTiposFuncionesRepository;

    public GdocConsejosServiceImpl(GdocConsejosRepository consejosRepository,
                                   GdocUsrTiposFuncionesRepository usrTiposFuncionesRepository) {
        this.consejosRepository = consejosRepository;
        this.usrTiposFuncionesRepository = usrTiposFuncionesRepository;
    }

    @Override
    public List<GdocConsejos> listarTodos() {
        return consejosRepository.findAll();
    }

    @Override
    public GdocConsejos guardar(GdocConsejos gdocConsejos) {
        return consejosRepository.save(gdocConsejos);
    }

    @Override
    public GdocConsejos buscarPorId(Long idGdocConsejo) {
        return consejosRepository.findById(idGdocConsejo)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado"));
    }

    @Override
    public GdocConsejos buscarPorUsuarioYFuncion(Long idUsuario, Long idMnuTipoFuncion) {
        List<GdocUsrTiposFunciones> funciones = usrTiposFuncionesRepository.findAll().stream()
                .filter(f -> f.getUsuarios().getIdUsuario().equals(idUsuario) &&
                             f.getMnuTiposFunciones().getIdMnuTipoFuncion().equals(idMnuTipoFuncion))
                .toList();

        if (!funciones.isEmpty()) {
            return funciones.get(0).getGdocConsejos();
        }
        throw new RuntimeException("No se encontro consejo para el usuario y funcion especificados");
    }
}
