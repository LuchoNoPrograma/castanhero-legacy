package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrsTipoEmpresaTelefonica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgPrsTipoEmpresaTelefonicaRepository extends JpaRepository<PgPrsTipoEmpresaTelefonica, Long> {
}
