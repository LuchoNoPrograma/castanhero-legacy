package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.PrsEstadoCivil;

import java.util.List;

@Repository
public interface PrsEstadoCivilRepository extends JpaRepository<PrsEstadoCivil, Long> {

    @Query("SELECT e FROM PrsEstadoCivil e WHERE e.idEstado = 'A'")
    List<PrsEstadoCivil> findAllActive();

    List<PrsEstadoCivil> findByEstadoCivilContainingIgnoreCase(String estadoCivil);
}
