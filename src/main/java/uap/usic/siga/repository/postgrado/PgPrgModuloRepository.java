package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgModulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrgModuloRepository extends JpaRepository<PgPrgModulo, Long> {

    @Query("SELECT e FROM PgPrgModulo e WHERE e.idEstado = 'A'")
    List<PgPrgModulo> listarActivos();
}
