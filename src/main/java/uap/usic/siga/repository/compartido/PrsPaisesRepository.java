package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.PrsPaises;

import java.util.List;

@Repository
public interface PrsPaisesRepository extends JpaRepository<PrsPaises, Long> {

    @Query("SELECT p FROM PrsPaises p WHERE p.idEstado = 'A'")
    List<PrsPaises> findAllActive();

    List<PrsPaises> findByPaisContainingIgnoreCase(String pais);
}
