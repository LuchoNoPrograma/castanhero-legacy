package uap.usic.siga.servicios;

import java.util.List;

import uap.usic.siga.entidadesPg.Depositos;
import uap.usic.siga.entidadesPg.PgArchivosAdjuntos;
import uap.usic.siga.entidadesPg.PgPstMatriculas;
import uap.usic.siga.entidadesPg.PgPstProgramas;

public interface MatriculaService {
    public PgPstMatriculas buscarMatriculasPorIdPstPrograma(Long idPstPrograma);

    public List<PgPstMatriculas> listarMatriculasPorIdGrupo(Long idGrupo);
    public List<PgPstMatriculas> listaMatriculas();
    public List<Depositos> listaDepositos();

    public PgPstMatriculas registrarMatriculas(PgPstMatriculas matricula);
    public PgPstMatriculas modificarPgPstMatriculas(PgPstMatriculas matricula);

    public Depositos registrarDepositos(Depositos deposito);

    public void registrarfkPgPstProgramas(PgPstProgramas programa);

    public PgArchivosAdjuntos registrarReciboAbjuntoSET(PgArchivosAdjuntos pgArchivosAdjuntos);
    
}
