package uap.usic.siga.repository.gdoc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocGestionConsejos;
import uap.usic.siga.domain.gdoc.GdocResolucionesDigitales;

@Repository
public interface GdocResolucionesDigitalesRepository extends JpaRepository<GdocResolucionesDigitales, Long> {
    List<GdocResolucionesDigitales> findByGdocConsejos(GdocConsejos gdocConsejos);
    List<GdocResolucionesDigitales> findByGdocGestionConsejos(GdocGestionConsejos gdocGestionConsejos);
}
