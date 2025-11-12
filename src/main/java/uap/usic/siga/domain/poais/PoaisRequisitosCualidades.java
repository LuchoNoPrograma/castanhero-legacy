package uap.usic.siga.domain.poais;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "poais_requisitos_cualidades")
public class PoaisRequisitosCualidades extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisito_cualidad")
    private Long idRequisitoCualidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_requisito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisRequisitos poaisRequisitos;

    @Column(name = "requisito_cualidad")
    private String requisitoCualidad;

    public Long getIdRequisitoCualidad() {
        return idRequisitoCualidad;
    }

    public void setIdRequisitoCualidad(Long idRequisitoCualidad) {
        this.idRequisitoCualidad = idRequisitoCualidad;
    }

    public PoaisRequisitos getPoaisRequisitos() {
        return poaisRequisitos;
    }

    public void setPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
        this.poaisRequisitos = poaisRequisitos;
    }

    public String getRequisitoCualidad() {
        return requisitoCualidad;
    }

    public void setRequisitoCualidad(String requisitoCualidad) {
        this.requisitoCualidad = requisitoCualidad;
    }
}
