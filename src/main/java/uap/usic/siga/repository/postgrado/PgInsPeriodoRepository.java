package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgInsPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgInsPeriodoRepository extends JpaRepository<PgInsPeriodo, Long> {

    @Query("SELECT e FROM PgInsPeriodo e WHERE e.idEstado = 'A'")
    List<PgInsPeriodo> listarActivos();
}
