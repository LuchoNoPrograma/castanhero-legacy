package uap.usic.siga.domain.sicoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "scs_archivos_adjuntos")
public class ScsArchivoAdjunto extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_archivo_adjunto")
    private Long idScsArchivoAdjunto;

    @NotNull
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Column(name = "tipo_archivo")
    private String tipoArchivo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "contenido")
    private byte[] contenido;

    @NotNull
    @Column(name = "ruta_archivo")
    private String rutaArchivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    @JsonIgnore
    private Personas persona;

    public Long getIdScsArchivoAdjunto() {
        return idScsArchivoAdjunto;
    }

    public void setIdScsArchivoAdjunto(Long idScsArchivoAdjunto) {
        this.idScsArchivoAdjunto = idScsArchivoAdjunto;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }
}
