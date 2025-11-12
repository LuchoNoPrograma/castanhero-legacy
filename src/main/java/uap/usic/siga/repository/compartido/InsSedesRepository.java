package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.InsSedes;

import java.util.List;

@Repository
public interface InsSedesRepository extends JpaRepository<InsSedes, Long> {

    @Query("SELECT s FROM InsSedes s WHERE s.idEstado = 'A'")
    List<InsSedes> findAllActive();

    List<InsSedes> findBySedeContainingIgnoreCase(String sede);
}
