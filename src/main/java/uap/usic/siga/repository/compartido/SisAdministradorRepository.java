package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.SisAdministrador;

import java.util.List;

@Repository
public interface SisAdministradorRepository extends JpaRepository<SisAdministrador, Long> {

    @Query("SELECT s FROM SisAdministrador s WHERE s.idEstado = 'A'")
    List<SisAdministrador> findAllActive();

    List<SisAdministrador> findByNombreSisContainingIgnoreCase(String nombreSis);
}
