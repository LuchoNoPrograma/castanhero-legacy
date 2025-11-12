package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.PrsGradosAcademicos;

import java.util.List;

@Repository
public interface PrsGradosAcademicosRepository extends JpaRepository<PrsGradosAcademicos, Long> {

    @Query("SELECT g FROM PrsGradosAcademicos g WHERE g.idEstado = 'A'")
    List<PrsGradosAcademicos> findAllActive();

    List<PrsGradosAcademicos> findByGradoAcademicoContainingIgnoreCase(String gradoAcademico);
}
