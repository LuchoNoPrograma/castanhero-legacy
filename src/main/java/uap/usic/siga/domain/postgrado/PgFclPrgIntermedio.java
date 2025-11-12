package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_fcl_prg_intermedios")
public class PgFclPrgIntermedio extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prg_intermedio")
    private Long idPrgIntermedio;

    @Column(name = "nivel_academico")
    private Short nivelAcademico;

    public Long getIdPrgIntermedio() {
        return idPrgIntermedio;
    }

    public void setIdPrgIntermedio(Long idPrgIntermedio) {
        this.idPrgIntermedio = idPrgIntermedio;
    }

    public Short getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(Short nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }
}
