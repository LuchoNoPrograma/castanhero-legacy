package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pg_mtr_planes")
public class PgMtrPlanes extends SigaUsicRevisiones{
    /*===================RELACIONES=====================
     * id_plan dtexto
     * 
     * id_programa
     * 
     * id_plan_ant dtexto
     * 
     * id_mencion dentero DEFAULT 0
     * 
     * id_materia_ant
     * 
     * id_materia
     * 
     * id_tipo_materia
     * 
     * id_tipo_grado
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     * 
     * _id_estado character(1)
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    private PgMtrMaterias materia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_planes", referencedColumnName = "id_planes")
    private PgPrgPlanes pgPrgPlanes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mtr_plan")
    private Long idMtrPlan;

    @Column(name = "nivel_academico")
    private Short nivelAcademico;

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

    
}
