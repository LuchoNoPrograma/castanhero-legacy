package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.Universidades;

import java.util.List;

@Repository
public interface UniversidadesRepository extends JpaRepository<Universidades, Long> {

    @Query("SELECT u FROM Universidades u WHERE u.idEstado = 'A'")
    List<Universidades> findAllActive();

    List<Universidades> findByUniversidadContainingIgnoreCase(String universidad);
}
