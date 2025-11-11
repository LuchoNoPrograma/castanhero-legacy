package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisObjetivos;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoaisObjetivosRepository extends JpaRepository<PoaisObjetivos, Long> {

    @Query("SELECT o FROM PoaisObjetivos o WHERE o.poais.pnlPersonalAdministrativos.idPnlPersonalAdministrativo = :idPersonal")
    List<PoaisObjetivos> findByPersonalAdministrativo(@Param("idPersonal") Long idPersonal);

    Optional<PoaisObjetivos> findByPoais_IdPoai(Long idPoai);
}
