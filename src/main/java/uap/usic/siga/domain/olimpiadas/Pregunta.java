package uap.usic.siga.domain.olimpiadas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.SigaUsicGestiones;

/**
 * Entidad que representa una pregunta asociada a una etapa de las olimpiadas
 */
@Entity
@Table(name = "oo_pregunta")
public class Pregunta extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private Long idPregunta;

    @NotNull(message = "La etapa es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etapa", referencedColumnName = "id_etapa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Etapa etapa;

    @NotBlank(message = "La pregunta es obligatoria")
    @Column(name = "pregunta", length = 1000)
    private String pregunta;

    // Constructores
    public Pregunta() {
    }

    public Pregunta(Etapa etapa, String pregunta) {
        this.etapa = etapa;
        this.pregunta = pregunta;
    }

    // Getters y Setters
    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
