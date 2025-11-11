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
@Table(name = "poais_requisitos_experiencias")
public class PoaisRequisitosExperiencias extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisito_experiencia")
    private Long idRequisitoExperiencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_requisito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisRequisitos poaisRequisitos;

    @Column(name = "requisito_experiencia")
    private String requisitoExperiencia;

    public Long getIdRequisitoExperiencia() {
        return idRequisitoExperiencia;
    }

    public void setIdRequisitoExperiencia(Long idRequisitoExperiencia) {
        this.idRequisitoExperiencia = idRequisitoExperiencia;
    }

    public PoaisRequisitos getPoaisRequisitos() {
        return poaisRequisitos;
    }

    public void setPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
        this.poaisRequisitos = poaisRequisitos;
    }

    public String getRequisitoExperiencia() {
        return requisitoExperiencia;
    }

    public void setRequisitoExperiencia(String requisitoExperiencia) {
        this.requisitoExperiencia = requisitoExperiencia;
    }
}
