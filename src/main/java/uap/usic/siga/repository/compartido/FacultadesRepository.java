package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.Facultades;

import java.util.List;

@Repository
public interface FacultadesRepository extends JpaRepository<Facultades, Long> {

    @Query("SELECT f FROM Facultades f WHERE f.idEstado = 'A'")
    List<Facultades> findAllActive();

    List<Facultades> findByFacultadContainingIgnoreCase(String facultad);
}
