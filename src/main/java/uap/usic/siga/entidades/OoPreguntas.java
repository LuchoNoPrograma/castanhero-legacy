package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "oo_pregunta")
public class OoPreguntas extends SigaUsicGestiones  {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etapa", referencedColumnName = "id_etapa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OoEtapas ooEtapas;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private Long idPregunta;
    
    @Column(name = "pregunta")
    private String pregunta;

    public OoEtapas getOoEtapas() {
        return ooEtapas;
    }

    public void setOoEtapas(OoEtapas ooEtapas) {
        this.ooEtapas = ooEtapas;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    

}
