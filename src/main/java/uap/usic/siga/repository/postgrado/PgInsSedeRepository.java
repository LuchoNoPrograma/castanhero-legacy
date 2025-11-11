package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgInsSede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgInsSedeRepository extends JpaRepository<PgInsSede, Long> {

    @Query("SELECT e FROM PgInsSede e WHERE e.idEstado = 'A'")
    List<PgInsSede> listarActivos();
}
