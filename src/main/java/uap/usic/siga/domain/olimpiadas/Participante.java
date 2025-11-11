package uap.usic.siga.domain.olimpiadas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SigaUsicGestiones;

/**
 * Entidad que representa un participante individual de las olimpiadas
 * Asociado a una persona y a un equipo participante
 */
@Entity
@Table(name = "oo_participantes")
public class Participante extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participante")
    private Long idParticipante;

    @NotNull(message = "La persona es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas persona;

    @NotNull(message = "El equipo participante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_participante", referencedColumnName = "id_equipo_participante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EquipoParticipante equipoParticipante;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "participante")
    private List<Puntuacion> puntuaciones = new ArrayList<>();

    @Column(name = "edad")
    private String edad;

    @Column(name = "imagen")
    private String imagen;

    // Constructores
    public Participante() {
    }

    public Participante(Personas persona, EquipoParticipante equipoParticipante, String edad) {
        this.persona = persona;
        this.equipoParticipante = equipoParticipante;
        this.edad = edad;
    }

    // Getters y Setters
    public Long getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Long idParticipante) {
        this.idParticipante = idParticipante;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public EquipoParticipante getEquipoParticipante() {
        return equipoParticipante;
    }

    public void setEquipoParticipante(EquipoParticipante equipoParticipante) {
        this.equipoParticipante = equipoParticipante;
    }

    public List<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
