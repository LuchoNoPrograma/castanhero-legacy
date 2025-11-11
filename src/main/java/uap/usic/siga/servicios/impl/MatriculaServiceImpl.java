package uap.usic.siga.servicios.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uap.usic.siga.entidadesPg.Depositos;
import uap.usic.siga.entidadesPg.PgArchivosAdjuntos;
import uap.usic.siga.entidadesPg.PgPstMatriculas;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.modelos.MatriculaDao;
import uap.usic.siga.servicios.MatriculaService;

@Service("matriculaService")
@Transactional
public class MatriculaServiceImpl implements MatriculaService{

    @Autowired
	private MatriculaDao dao;

    @Override
    public PgPstMatriculas buscarMatriculasPorIdPstPrograma(Long idPstPrograma){
        return dao.buscarMatriculasPorIdPstPrograma(idPstPrograma);
    }

    @Override
    public List<PgPstMatriculas> listarMatriculasPorIdGrupo(Long idGrupo){
        return dao.listarMatriculasPorIdGrupo(idGrupo);
    }

    @Override
    public List<PgPstMatriculas> listaMatriculas() {
        return dao.listaMatriculas();
    }

    @Override
    public PgPstMatriculas registrarMatriculas(PgPstMatriculas matricula) {
        return dao.registrarMatriculas(matricula);
    }

    @Override
    public PgPstMatriculas modificarPgPstMatriculas(PgPstMatriculas matricula){
        return dao.modificarPgPstMatriculas(matricula);
    }

    @Override
    public void registrarfkPgPstProgramas(PgPstProgramas programa) {
        dao.registrarfkPgPstProgramas(programa);
    }

    @Override
    public List<Depositos> listaDepositos() {
        return dao.listaDepositos();
    }

    @Override
    public Depositos registrarDepositos(Depositos deposito) {
        return dao.registrarDepositos(deposito);
    }

    @Override
    public PgArchivosAdjuntos registrarReciboAbjuntoSET(PgArchivosAdjuntos pgArchivosAdjuntos) {
        return dao.registrarReciboAbjuntoSET(pgArchivosAdjuntos);
    }
    
}
