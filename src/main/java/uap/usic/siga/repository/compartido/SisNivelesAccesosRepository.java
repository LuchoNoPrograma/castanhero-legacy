package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.SisNivelesAccesos;

import java.util.List;

@Repository
public interface SisNivelesAccesosRepository extends JpaRepository<SisNivelesAccesos, Integer> {

    @Query("SELECT n FROM SisNivelesAccesos n WHERE n.idEstado = 'A'")
    List<SisNivelesAccesos> findAllActive();

    List<SisNivelesAccesos> findByNivelAccesoContainingIgnoreCase(String nivelAcceso);
}
