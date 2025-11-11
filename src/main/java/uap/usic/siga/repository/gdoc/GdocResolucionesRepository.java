package uap.usic.siga.repository.gdoc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocResoluciones;

@Repository
public interface GdocResolucionesRepository extends JpaRepository<GdocResoluciones, Long> {
    List<GdocResoluciones> findByGdocConsejos(GdocConsejos gdocConsejos);
}
