package uap.usic.siga.repository.gdoc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.gdoc.GdocConsejos;
import uap.usic.siga.domain.gdoc.GdocUsrTiposFunciones;
import uap.usic.siga.entidades.Usuarios;

@Repository
public interface GdocUsrTiposFuncionesRepository extends JpaRepository<GdocUsrTiposFunciones, Long> {
    List<GdocUsrTiposFunciones> findByUsuarios(Usuarios usuarios);
    List<GdocUsrTiposFunciones> findByGdocConsejos(GdocConsejos gdocConsejos);
}
