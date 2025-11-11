package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrsEstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrsEstadoCivilRepository extends JpaRepository<PgPrsEstadoCivil, Long> {

    @Query("SELECT e FROM PgPrsEstadoCivil e WHERE e.idEstado = 'A'")
    List<PgPrsEstadoCivil> listarActivos();
}
