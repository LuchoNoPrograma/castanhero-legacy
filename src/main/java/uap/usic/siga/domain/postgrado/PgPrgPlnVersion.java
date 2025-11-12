package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.Usuarios;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "pg_prg_pln_versiones")
public class PgPrgPlnVersion extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_versiones")
    private Long idVersion;

    @Column(name = "version")
    private String version;

    @Column(name = "fechaInicio")
    private Date fechaInicio;

    @Column(name = "fechaFinalizacion")
    private Date fechaFinalizacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pg_archivo_adjunto")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgArchivoAdjunto archivoAdjunto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    private PgPrograma programa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_planes", referencedColumnName = "id_planes")
    private PgPrgPlan plan;

    public Long getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(Long idVersion) {
        this.idVersion = idVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public PgArchivoAdjunto getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(PgArchivoAdjunto archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public PgPrograma getPrograma() {
        return programa;
    }

    public void setPrograma(PgPrograma programa) {
        this.programa = programa;
    }

    public PgPrgPlan getPlan() {
        return plan;
    }

    public void setPlan(PgPrgPlan plan) {
        this.plan = plan;
    }

    public boolean estaVigente() {
        Date hoy = new Date(System.currentTimeMillis());
        return fechaInicio != null && fechaInicio.before(hoy) &&
               (fechaFinalizacion == null || fechaFinalizacion.after(hoy));
    }
}
