package uap.usic.siga.domain.olimpiadas;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.SigaUsicGestiones;

/**
 * Entidad que representa un equipo participante en las olimpiadas
 * Asociado a una competición y a una unidad educativa
 */
@Entity
@Table(name = "oo_equipos_participantes")
public class EquipoParticipante extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo_participante")
    private Long idEquipoParticipante;

    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @NotNull(message = "La competición es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competiciones", referencedColumnName = "id_competiciones")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Competicion competicion;

    @NotNull(message = "La unidad educativa es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_educativa", referencedColumnName = "id_unidad_educativa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UnidadEducativa unidadEducativa;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "equipoParticipante")
    private List<Puntuacion> puntuaciones = new ArrayList<>();

    // Constructores
    public EquipoParticipante() {
    }

    public EquipoParticipante(String nombre, String descripcion, Competicion competicion, UnidadEducativa unidadEducativa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.competicion = competicion;
        this.unidadEducativa = unidadEducativa;
    }

    // Getters y Setters
    public Long getIdEquipoParticipante() {
        return idEquipoParticipante;
    }

    public void setIdEquipoParticipante(Long idEquipoParticipante) {
        this.idEquipoParticipante = idEquipoParticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }

    public UnidadEducativa getUnidadEducativa() {
        return unidadEducativa;
    }

    public void setUnidadEducativa(UnidadEducativa unidadEducativa) {
        this.unidadEducativa = unidadEducativa;
    }

    public List<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }
}
