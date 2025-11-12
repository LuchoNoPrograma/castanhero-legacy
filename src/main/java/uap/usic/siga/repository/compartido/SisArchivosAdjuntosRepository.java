package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.SisArchivosAdjuntos;

import java.util.List;

@Repository
public interface SisArchivosAdjuntosRepository extends JpaRepository<SisArchivosAdjuntos, Long> {

    @Query("SELECT a FROM SisArchivosAdjuntos a WHERE a.idEstado = 'A'")
    List<SisArchivosAdjuntos> findAllActive();

    @Query("SELECT a FROM SisArchivosAdjuntos a WHERE a.usuarios.id = :idUsuario AND a.idEstado = 'A'")
    List<SisArchivosAdjuntos> findByUsuarioId(@Param("idUsuario") Long idUsuario);
}
