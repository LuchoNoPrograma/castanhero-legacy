package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "admision")
public class PgAdmision extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admision")
    private Long idAdmision;

    public Long getIdAdmision() {
        return idAdmision;
    }

    public void setIdAdmision(Long idAdmision) {
        this.idAdmision = idAdmision;
    }
}
