package uap.usic.siga.domain.olimpiadas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.SigaUsicGestiones;

/**
 * Entidad que representa la puntuación obtenida por un participante
 * en un enfrentamiento específico
 */
@Entity
@Table(name = "oo_puntuaciones")
public class Puntuacion extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puntuacion")
    private Long idPuntuacion;

    @NotNull(message = "El enfrentamiento es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enfrentamientos", referencedColumnName = "id_enfrentamientos")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Enfrentamiento enfrentamiento;

    @NotNull(message = "El equipo participante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_participante", referencedColumnName = "id_equipo_participante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EquipoParticipante equipoParticipante;

    @NotNull(message = "El participante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_participante", referencedColumnName = "id_participante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Participante participante;

    @Column(name = "puntaje")
    private Integer puntaje;

    // Constructores
    public Puntuacion() {
    }

    public Puntuacion(Enfrentamiento enfrentamiento, EquipoParticipante equipoParticipante,
                      Participante participante, Integer puntaje) {
        this.enfrentamiento = enfrentamiento;
        this.equipoParticipante = equipoParticipante;
        this.participante = participante;
        this.puntaje = puntaje;
    }

    // Getters y Setters
    public Long getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(Long idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    public Enfrentamiento getEnfrentamiento() {
        return enfrentamiento;
    }

    public void setEnfrentamiento(Enfrentamiento enfrentamiento) {
        this.enfrentamiento = enfrentamiento;
    }

    public EquipoParticipante getEquipoParticipante() {
        return equipoParticipante;
    }

    public void setEquipoParticipante(EquipoParticipante equipoParticipante) {
        this.equipoParticipante = equipoParticipante;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
}
