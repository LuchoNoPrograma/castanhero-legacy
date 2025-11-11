package uap.usic.siga.repository.gdoc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.gdoc.GdocConsejos;

@Repository
public interface GdocConsejosRepository extends JpaRepository<GdocConsejos, Long> {
}
