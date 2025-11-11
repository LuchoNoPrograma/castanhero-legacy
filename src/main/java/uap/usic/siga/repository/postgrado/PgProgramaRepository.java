package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrograma;
import uap.usic.siga.domain.postgrado.PgPrgModalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PgProgramaRepository extends JpaRepository<PgPrograma, Long> {

    Optional<PgPrograma> findBySigla(String sigla);

    List<PgPrograma> findByModalidad(PgPrgModalidad modalidad);

    @Query("SELECT p FROM PgPrograma p WHERE p.idEstado = 'A'")
    List<PgPrograma> listarActivos();

    @Query("SELECT p FROM PgPrograma p WHERE p.disponibilidad > 0 AND p.idEstado = 'A'")
    List<PgPrograma> listarDisponibles();

    @Query("SELECT p FROM PgPrograma p WHERE LOWER(p.programa) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<PgPrograma> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM PgPrograma p WHERE p.gradoAcademico.idGrado = :idGrado")
    List<PgPrograma> listarPorGradoAcademico(@Param("idGrado") Long idGrado);
}
