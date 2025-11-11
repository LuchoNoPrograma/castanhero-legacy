package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgMateriaRepository extends JpaRepository<PgMateria, Long> {

    @Query("SELECT e FROM PgMateria e WHERE e.idEstado = 'A'")
    List<PgMateria> listarActivos();
}
