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
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.SigaUsicRevisiones;

/**
 * Entidad que representa el detalle de un enfrentamiento
 * Indica qué equipos participan en un enfrentamiento específico
 */
@Entity
@Table(name = "oo_detalle_enfrentamiento")
public class DetalleEnfrentamiento extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_eenfrentamiento")
    private Long idDetalleEnfrentamiento;

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

    // Constructores
    public DetalleEnfrentamiento() {
    }

    public DetalleEnfrentamiento(Enfrentamiento enfrentamiento, EquipoParticipante equipoParticipante) {
        this.enfrentamiento = enfrentamiento;
        this.equipoParticipante = equipoParticipante;
    }

    // Getters y Setters
    public Long getIdDetalleEnfrentamiento() {
        return idDetalleEnfrentamiento;
    }

    public void setIdDetalleEnfrentamiento(Long idDetalleEnfrentamiento) {
        this.idDetalleEnfrentamiento = idDetalleEnfrentamiento;
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
}
