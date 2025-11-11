package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PgMatriculaRepository extends JpaRepository<PgMatricula, Long> {

    Optional<PgMatricula> findByMatricula(String matricula);

    @Query("SELECT m FROM PgMatricula m WHERE m.idEstado = 'A'")
    List<PgMatricula> listarActivas();

    @Query("SELECT m FROM PgMatricula m WHERE m.postulante.idPstProgramas = :idPstPrograma")
    Optional<PgMatricula> buscarPorPostulante(@Param("idPstPrograma") Long idPstPrograma);
}
