package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.Menues;

import java.util.List;

@Repository
public interface MenuesRepository extends JpaRepository<Menues, Long> {

    @Query("SELECT m FROM Menues m WHERE m.idEstado = 'A'")
    List<Menues> findAllActive();
}
