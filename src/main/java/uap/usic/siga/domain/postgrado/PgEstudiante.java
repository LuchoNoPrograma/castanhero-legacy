package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.Personas;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "estudiantes")
public class PgEstudiante extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas persona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPrograma programa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_estudiante", referencedColumnName = "id_tipo_estudiante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgTipoEstudiante tipoEstudiante;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_egreso")
    @Temporal(TemporalType.DATE)
    private Date fechaEgreso;

    @Column(name = "observacion")
    private String observacion;

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public PgPrograma getPrograma() {
        return programa;
    }

    public void setPrograma(PgPrograma programa) {
        this.programa = programa;
    }

    public PgTipoEstudiante getTipoEstudiante() {
        return tipoEstudiante;
    }

    public void setTipoEstudiante(PgTipoEstudiante tipoEstudiante) {
        this.tipoEstudiante = tipoEstudiante;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean estaEgresado() {
        return fechaEgreso != null;
    }

    public boolean estaInscrito() {
        return fechaInscripcion != null && !estaEgresado();
    }

    public String obtenerNombreCompleto() {
        if (persona != null) {
            return persona.getNombres() + " " +
                   (persona.getPaterno() != null ? persona.getPaterno() : "") + " " +
                   (persona.getMaterno() != null ? persona.getMaterno() : "");
        }
        return "";
    }
}
