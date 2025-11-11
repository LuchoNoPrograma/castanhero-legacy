package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPstPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPstProgramaRepository extends JpaRepository<PgPstPrograma, Long> {

    @Query("SELECT e FROM PgPstPrograma e WHERE e.idEstado = 'A'")
    List<PgPstPrograma> listarActivos();
}
