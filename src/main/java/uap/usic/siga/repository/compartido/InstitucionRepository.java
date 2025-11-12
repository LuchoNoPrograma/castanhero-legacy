package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.Institucion;

import java.util.List;

@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Long> {

    @Query("SELECT i FROM Institucion i WHERE i.idEstado = 'A'")
    List<Institucion> findAllActive();

    List<Institucion> findByInstitucionContainingIgnoreCase(String institucion);

    List<Institucion> findBySigla(String sigla);
}
