package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.InsSede;

import java.util.List;

@Repository
public interface InsSedeRepository extends JpaRepository<InsSede, Long> {

    @Query("SELECT s FROM InsSede s WHERE s.idEstado = 'A'")
    List<InsSede> findAllActive();

    List<InsSede> findBySedeContainingIgnoreCase(String sede);
}
