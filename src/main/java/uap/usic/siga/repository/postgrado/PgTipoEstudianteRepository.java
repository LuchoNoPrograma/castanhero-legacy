package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTipoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgTipoEstudianteRepository extends JpaRepository<PgTipoEstudiante, Long> {

    @Query("SELECT e FROM PgTipoEstudiante e WHERE e.idEstado = 'A'")
    List<PgTipoEstudiante> listarActivos();
}
