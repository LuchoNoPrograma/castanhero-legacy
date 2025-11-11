package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPstDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPstDocumentoRepository extends JpaRepository<PgPstDocumento, Long> {

    @Query("SELECT e FROM PgPstDocumento e WHERE e.idEstado = 'A'")
    List<PgPstDocumento> listarActivos();
}
