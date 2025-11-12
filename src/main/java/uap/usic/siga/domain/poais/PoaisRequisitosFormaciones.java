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
@Table(name = "poais_requisitos_formaciones")
public class PoaisRequisitosFormaciones extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisito_formacion")
    private Long idRequisitoFormacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_requisito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisRequisitos poaisRequisitos;

    @Column(name = "requisito_formacion")
    private String requisitoFormacion;

    public Long getIdRequisitoFormacion() {
        return idRequisitoFormacion;
    }

    public void setIdRequisitoFormacion(Long idRequisitoFormacion) {
        this.idRequisitoFormacion = idRequisitoFormacion;
    }

    public PoaisRequisitos getPoaisRequisitos() {
        return poaisRequisitos;
    }

    public void setPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
        this.poaisRequisitos = poaisRequisitos;
    }

    public String getRequisitoFormacion() {
        return requisitoFormacion;
    }

    public void setRequisitoFormacion(String requisitoFormacion) {
        this.requisitoFormacion = requisitoFormacion;
    }
}
