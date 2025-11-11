package uap.usic.siga.repository.gdoc;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocConvenios;

@Repository
public interface GdocConveniosRepository extends JpaRepository<GdocConvenios, Long> {
    List<GdocConvenios> findByGdocConsejos(GdocConsejos gdocConsejos);

    @Query("SELECT c FROM GdocConvenios c WHERE c.fecInicio >= :fecInicio AND c.fecFinal <= :fecFinal")
    List<GdocConvenios> findByRangoFechas(@Param("fecInicio") Date fecInicio, @Param("fecFinal") Date fecFinal);
}
