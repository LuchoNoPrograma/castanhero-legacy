package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPostulante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PgPostulanteRepository extends JpaRepository<PgPostulante, Long> {

    Optional<PgPostulante> findByCi(String ci);

    Optional<PgPostulante> findByEmail(String email);

    @Query("SELECT p FROM PgPostulante p WHERE p.idEstado = 'A'")
    List<PgPostulante> listarActivos();

    @Query("SELECT p FROM PgPostulante p WHERE LOWER(p.nombres) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
           "OR LOWER(p.paterno) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
           "OR LOWER(p.materno) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<PgPostulante> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM PgPostulante p WHERE p.gradoAcademico.idGradoAcademico = :idGrado")
    List<PgPostulante> listarPorGradoAcademico(@Param("idGrado") Long idGrado);
}
