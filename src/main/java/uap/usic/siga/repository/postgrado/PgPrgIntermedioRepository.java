package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgIntermedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrgIntermedioRepository extends JpaRepository<PgPrgIntermedio, Long> {

    @Query("SELECT e FROM PgPrgIntermedio e WHERE e.idEstado = 'A'")
    List<PgPrgIntermedio> listarActivos();
}
