package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgEstudiante;
import uap.usic.siga.domain.postgrado.PgPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PgEstudianteRepository extends JpaRepository<PgEstudiante, Long> {

    List<PgEstudiante> findByPrograma(PgPrograma programa);

    @Query("SELECT e FROM PgEstudiante e WHERE e.persona.ci = :ci")
    Optional<PgEstudiante> buscarPorCi(@Param("ci") String ci);

    @Query("SELECT e FROM PgEstudiante e WHERE e.programa.idPrograma = :idPrograma AND e.idEstado = 'A'")
    List<PgEstudiante> listarActivosPorPrograma(@Param("idPrograma") Long idPrograma);

    @Query("SELECT e FROM PgEstudiante e WHERE e.fechaEgreso IS NOT NULL")
    List<PgEstudiante> listarEgresados();

    @Query("SELECT e FROM PgEstudiante e WHERE e.fechaInscripcion IS NOT NULL AND e.fechaEgreso IS NULL")
    List<PgEstudiante> listarInscritos();
}
