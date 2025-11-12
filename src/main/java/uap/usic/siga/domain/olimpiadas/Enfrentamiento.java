package uap.usic.siga.domain.olimpiadas;

import java.util.ArrayList;
import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.SigaUsicRevisiones;

/**
 * Entidad que representa un enfrentamiento entre equipos en las olimpiadas
 * Asociado a un encuentro y a una etapa específica
 */
@Entity
@Table(name = "oo_enfrentamientos")
public class Enfrentamiento extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enfrentamientos")
    private Long idEnfrentamiento;

    @NotNull(message = "El encuentro es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_encuentro", referencedColumnName = "id_encuentro")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Encuentro encuentro;

    @NotNull(message = "La etapa es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etapa", referencedColumnName = "id_etapa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Etapa etapa;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "enfrentamiento")
    private List<DetalleEnfrentamiento> detallesEnfrentamiento = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "enfrentamiento")
    private List<Puntuacion> puntuaciones = new ArrayList<>();

    @Column(name = "encuentro")
    private Integer numeroEncuentro;

    @NotNull(message = "La fecha del encuentro es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_encuentro")
    @Temporal(TemporalType.DATE)
    private Date fechaEncuentro;

    // Campos transitorios para manejar los IDs de equipos en formularios
    @Transient
    private Long idEquipoParticipante;

    @Transient
    private Long idEquipoParticipanteRival;

    @Transient
    private Long idEtapa;

    // Constructores
    public Enfrentamiento() {
    }

    public Enfrentamiento(Encuentro encuentro, Etapa etapa, Date fechaEncuentro) {
        this.encuentro = encuentro;
        this.etapa = etapa;
        this.fechaEncuentro = fechaEncuentro;
    }

    // Getters y Setters
    public Long getIdEnfrentamiento() {
        return idEnfrentamiento;
    }

    public void setIdEnfrentamiento(Long idEnfrentamiento) {
        this.idEnfrentamiento = idEnfrentamiento;
    }

    public Encuentro getEncuentro() {
        return encuentro;
    }

    public void setEncuentro(Encuentro encuentro) {
        this.encuentro = encuentro;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public List<DetalleEnfrentamiento> getDetallesEnfrentamiento() {
        return detallesEnfrentamiento;
    }

    public void setDetallesEnfrentamiento(List<DetalleEnfrentamiento> detallesEnfrentamiento) {
        this.detallesEnfrentamiento = detallesEnfrentamiento;
    }

    public List<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public Integer getNumeroEncuentro() {
        return numeroEncuentro;
    }

    public void setNumeroEncuentro(Integer numeroEncuentro) {
        this.numeroEncuentro = numeroEncuentro;
    }

    public Date getFechaEncuentro() {
        return fechaEncuentro;
    }

    public void setFechaEncuentro(Date fechaEncuentro) {
        this.fechaEncuentro = fechaEncuentro;
    }

    public Long getIdEquipoParticipante() {
        return idEquipoParticipante;
    }

    public void setIdEquipoParticipante(Long idEquipoParticipante) {
        this.idEquipoParticipante = idEquipoParticipante;
    }

    public Long getIdEquipoParticipanteRival() {
        return idEquipoParticipanteRival;
    }

    public void setIdEquipoParticipanteRival(Long idEquipoParticipanteRival) {
        this.idEquipoParticipanteRival = idEquipoParticipanteRival;
    }

    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }
}
