package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgDocente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PgDocenteRepository extends JpaRepository<PgDocente, Long> {

    Optional<PgDocente> findByCi(String ci);

    Optional<PgDocente> findByEmail(String email);

    @Query("SELECT d FROM PgDocente d WHERE d.idEstado = 'A'")
    List<PgDocente> listarActivos();

    @Query("SELECT d FROM PgDocente d WHERE LOWER(d.nombres) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
           "OR LOWER(d.paterno) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
           "OR LOWER(d.materno) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<PgDocente> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT d FROM PgDocente d WHERE d.gradoAcademico.idGradoAcademico = :idGrado")
    List<PgDocente> listarPorGradoAcademico(@Param("idGrado") Long idGrado);
}
