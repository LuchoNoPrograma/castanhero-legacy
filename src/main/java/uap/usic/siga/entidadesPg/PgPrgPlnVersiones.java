package uap.usic.siga.entidadesPg;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.Usuarios;


@Entity
@Table(name = "pg_prg_pln_versiones")
public class PgPrgPlnVersiones extends SigaUsicRevisiones{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pg_archivo_adjunto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgArchivosAdjuntos pgArchivosAdjuntos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    private Programas programa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_planes", referencedColumnName = "id_planes")
    private PgPrgPlanes plan;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_versiones")
    private Long idPgPrgPlnVersiones;

    private String version;

    private Date fechaInicio;

    private Date fechaFinalizacion;

    @Transient
    private MultipartFile file; 
    
    @Transient
    private String nombreArchivo;


    public Programas getPrograma() {
        return programa;
    }

    public void setPrograma(Programas programa) {
        this.programa = programa;
    }

    public Long getIdPgPrgPlnVersiones() {
        return idPgPrgPlnVersiones;
    }

    public void setIdPgPrgPlnVersiones(Long idPgPrgPlnVersiones) {
        this.idPgPrgPlnVersiones = idPgPrgPlnVersiones;
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

    public PgPrgPlanes getPlan() {
        return plan;
    }

    public void setPlan(PgPrgPlanes plan) {
        this.plan = plan;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public PgArchivosAdjuntos getPgArchivosAdjuntos() {
        return pgArchivosAdjuntos;
    }

    public void setPgArchivosAdjuntos(PgArchivosAdjuntos pgArchivosAdjuntos) {
        this.pgArchivosAdjuntos = pgArchivosAdjuntos;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }


}
