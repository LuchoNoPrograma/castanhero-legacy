package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.MnuEnlaces;

import java.util.List;

@Repository
public interface MnuEnlacesRepository extends JpaRepository<MnuEnlaces, Long> {

    @Query("SELECT e FROM MnuEnlaces e WHERE e.idEstado = 'A'")
    List<MnuEnlaces> findAllActive();

    List<MnuEnlaces> findByMnuEnlaceContainingIgnoreCase(String mnuEnlace);

    @Query("SELECT e FROM MnuEnlaces e WHERE e.idEstado = 'A' ORDER BY e.mnuEnlaceOrden")
    List<MnuEnlaces> findAllActiveOrderedByOrden();
}
