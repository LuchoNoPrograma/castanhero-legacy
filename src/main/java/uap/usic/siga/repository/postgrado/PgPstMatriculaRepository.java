package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPstMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPstMatriculaRepository extends JpaRepository<PgPstMatricula, Long> {

    @Query("SELECT e FROM PgPstMatricula e WHERE e.idEstado = 'A'")
    List<PgPstMatricula> listarActivos();
}
