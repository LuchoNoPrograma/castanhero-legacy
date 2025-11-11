package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgModalidadBeca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgModalidadBecaRepository extends JpaRepository<PgModalidadBeca, Long> {

    @Query("SELECT e FROM PgModalidadBeca e WHERE e.idEstado = 'A'")
    List<PgModalidadBeca> listarActivos();
}
