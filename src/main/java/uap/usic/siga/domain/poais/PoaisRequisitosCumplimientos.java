package uap.usic.siga.domain.poais;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.SigaUsicRevisiones;

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
@Table(name = "poais_requisitos_cumplimientos")
public class PoaisRequisitosCumplimientos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisito_cumplimiento")
    private Long idRequisitoCumplimiento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_requisito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisRequisitos poaisRequisitos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_cumplimiento", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisTiposCumplimientos poaisTiposCumplimientos;

    @Column(name = "requisito_cumpkimiento")
    private String requisitoCumplimiento;

    public Long getIdRequisitoCumplimiento() {
        return idRequisitoCumplimiento;
    }

    public void setIdRequisitoCumplimiento(Long idRequisitoCumplimiento) {
        this.idRequisitoCumplimiento = idRequisitoCumplimiento;
    }

    public PoaisRequisitos getPoaisRequisitos() {
        return poaisRequisitos;
    }

    public void setPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
        this.poaisRequisitos = poaisRequisitos;
    }

    public PoaisTiposCumplimientos getPoaisTiposCumplimientos() {
        return poaisTiposCumplimientos;
    }

    public void setPoaisTiposCumplimientos(PoaisTiposCumplimientos poaisTiposCumplimientos) {
        this.poaisTiposCumplimientos = poaisTiposCumplimientos;
    }

    public String getRequisitoCumplimiento() {
        return requisitoCumplimiento;
    }

    public void setRequisitoCumplimiento(String requisitoCumplimiento) {
        this.requisitoCumplimiento = requisitoCumplimiento;
    }
}
