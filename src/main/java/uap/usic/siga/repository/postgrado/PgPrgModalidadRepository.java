package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgModalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrgModalidadRepository extends JpaRepository<PgPrgModalidad, Long> {

    @Query("SELECT e FROM PgPrgModalidad e WHERE e.idEstado = 'A'")
    List<PgPrgModalidad> listarActivos();
}
