package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrsPais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrsPaisRepository extends JpaRepository<PgPrsPais, Long> {

    @Query("SELECT e FROM PgPrsPais e WHERE e.idEstado = 'A'")
    List<PgPrsPais> listarActivos();
}
