package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pg_fcl_programas")
public class PgFclPrograma extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa")
    private Long idPrograma;

    @Column(name = "programa")
    private String programa;

    @Column(name = "nro_estudiante")
    private Short nroEstudiante = 0;

    @Column(name = "nota_aprobacion")
    private Short notaAprobacion = 51;

    @Column(name = "nota_admision")
    private Short notaAdmision = 51;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "resolucion_hcu")
    private String resolucionHcu;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "mision")
    private String mision;

    @Column(name = "objetivos")
    private String objetivos;

    @Column(name = "duracion")
    private Short duracion;

    @Column(name = "turno")
    private String turno;

    @Column(name = "codigo_programa")
    private String codigoPrograma;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_facultad", referencedColumnName = "id_facultad")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgFacultad facultad;

    public Long getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Short getNroEstudiante() {
        return nroEstudiante;
    }

    public void setNroEstudiante(Short nroEstudiante) {
        this.nroEstudiante = nroEstudiante;
    }

    public Short getNotaAprobacion() {
        return notaAprobacion;
    }

    public void setNotaAprobacion(Short notaAprobacion) {
        this.notaAprobacion = notaAprobacion;
    }

    public Short getNotaAdmision() {
        return notaAdmision;
    }

    public void setNotaAdmision(Short notaAdmision) {
        this.notaAdmision = notaAdmision;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getResolucionHcu() {
        return resolucionHcu;
    }

    public void setResolucionHcu(String resolucionHcu) {
        this.resolucionHcu = resolucionHcu;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public Short getDuracion() {
        return duracion;
    }

    public void setDuracion(Short duracion) {
        this.duracion = duracion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public PgFacultad getFacultad() {
        return facultad;
    }

    public void setFacultad(PgFacultad facultad) {
        this.facultad = facultad;
    }
}
