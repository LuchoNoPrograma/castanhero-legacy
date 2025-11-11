package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgInsCampus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgInsCampusRepository extends JpaRepository<PgInsCampus, Long> {

    @Query("SELECT e FROM PgInsCampus e WHERE e.idEstado = 'A'")
    List<PgInsCampus> listarActivos();
}
