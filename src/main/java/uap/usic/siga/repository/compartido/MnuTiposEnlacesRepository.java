package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.MnuTiposEnlaces;

import java.util.List;

@Repository
public interface MnuTiposEnlacesRepository extends JpaRepository<MnuTiposEnlaces, Long> {

    @Query("SELECT t FROM MnuTiposEnlaces t WHERE t.idEstado = 'A'")
    List<MnuTiposEnlaces> findAllActive();

    List<MnuTiposEnlaces> findByMnuTipoEnlaceContainingIgnoreCase(String mnuTipoEnlace);
}
