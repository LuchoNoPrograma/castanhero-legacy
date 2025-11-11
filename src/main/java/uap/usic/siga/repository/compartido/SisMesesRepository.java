package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.SisMeses;

import java.util.List;

@Repository
public interface SisMesesRepository extends JpaRepository<SisMeses, Long> {

    @Query("SELECT m FROM SisMeses m WHERE m.idEstado = 'A'")
    List<SisMeses> findAllActive();

    List<SisMeses> findByMesContainingIgnoreCase(String mes);
}
