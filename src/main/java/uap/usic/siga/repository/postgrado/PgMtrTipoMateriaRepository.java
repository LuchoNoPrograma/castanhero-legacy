package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgMtrTipoMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgMtrTipoMateriaRepository extends JpaRepository<PgMtrTipoMateria, Long> {

    @Query("SELECT e FROM PgMtrTipoMateria e WHERE e.idEstado = 'A'")
    List<PgMtrTipoMateria> listarActivos();
}
