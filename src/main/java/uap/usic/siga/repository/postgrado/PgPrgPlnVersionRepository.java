package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgPlnVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrgPlnVersionRepository extends JpaRepository<PgPrgPlnVersion, Long> {

    @Query("SELECT e FROM PgPrgPlnVersion e WHERE e.idEstado = 'A'")
    List<PgPrgPlnVersion> listarActivos();
}
