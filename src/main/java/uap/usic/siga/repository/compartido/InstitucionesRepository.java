package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.Instituciones;

import java.util.List;

@Repository
public interface InstitucionesRepository extends JpaRepository<Instituciones, Long> {

    @Query("SELECT i FROM Instituciones i WHERE i.idEstado = 'A'")
    List<Instituciones> findAllActive();

    List<Instituciones> findByInstitucionContainingIgnoreCase(String institucion);

    List<Instituciones> findBySigla(String sigla);
}
