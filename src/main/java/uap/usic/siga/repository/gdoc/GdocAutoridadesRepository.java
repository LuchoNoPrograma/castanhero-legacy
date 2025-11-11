package uap.usic.siga.repository.gdoc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.gdoc.GdocAutoridades;
import uap.usic.siga.domain.gdoc.GdocConsejos;

@Repository
public interface GdocAutoridadesRepository extends JpaRepository<GdocAutoridades, Long> {
    List<GdocAutoridades> findByGdocConsejos(GdocConsejos gdocConsejos);
}
