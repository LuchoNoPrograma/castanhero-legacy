package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pg_mtr_planes")
public class PgMtrPlan extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mtr_plan")
    private Long idMtrPlan;

    @Column(name = "nivel_academico")
    private Short nivelAcademico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgMtrMateria materia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_planes", referencedColumnName = "id_planes")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPrgPlan pgPrgPlan;

    public Long getIdMtrPlan() {
        return idMtrPlan;
    }

    public void setIdMtrPlan(Long idMtrPlan) {
        this.idMtrPlan = idMtrPlan;
    }

    public Short getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(Short nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public PgMtrMateria getMateria() {
        return materia;
    }

    public void setMateria(PgMtrMateria materia) {
        this.materia = materia;
    }

    public PgPrgPlan getPgPrgPlan() {
        return pgPrgPlan;
    }

    public void setPgPrgPlan(PgPrgPlan pgPrgPlan) {
        this.pgPrgPlan = pgPrgPlan;
    }
}
