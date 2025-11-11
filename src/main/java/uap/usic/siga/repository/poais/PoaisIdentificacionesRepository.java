package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisIdentificaciones;

import java.util.List;

@Repository
public interface PoaisIdentificacionesRepository extends JpaRepository<PoaisIdentificaciones, Long> {

    @Query("SELECT pi FROM PoaisIdentificaciones pi WHERE pi.poais.gestion = :gestion")
    List<PoaisIdentificaciones> findByGestion(@Param("gestion") Integer gestion);
}
