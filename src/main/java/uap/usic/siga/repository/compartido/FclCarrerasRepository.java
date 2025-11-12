package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.FclCarreras;

import java.util.List;

@Repository
public interface FclCarrerasRepository extends JpaRepository<FclCarreras, Long> {

    @Query("SELECT c FROM FclCarreras c WHERE c.idEstado = 'A'")
    List<FclCarreras> findAllActive();

    List<FclCarreras> findByCarreraContainingIgnoreCase(String carrera);
}
