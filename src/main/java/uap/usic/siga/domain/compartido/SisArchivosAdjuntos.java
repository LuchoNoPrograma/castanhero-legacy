package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad que representa archivos adjuntos del sistema.
 * Nota: Relación con SacComprobantes comentada por migración parcial
 */
@Entity
@Table(name = "sis_archivos_adjuntos")
public class SisArchivosAdjuntos extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sis_archivo_adjunto")
    private Long id;

    @NotNull
    @Column(name = "nombre_archivo", nullable = false)
    private String nombreArchivo;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion = "Anulado por Yessenia ";

    @NotNull
    @Column(name = "tipo_archivo", nullable = false)
    private String tipoArchivo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "contenido")
    private byte[] contenido = new byte[100];

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    // TODO: Descomentar cuando se migre SacComprobantes
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id_sac_comprobante", nullable = false)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    // private SacComprobantes sacComprobantes;

    public SisArchivosAdjuntos() {
    }

    public SisArchivosAdjuntos(String nombreArchivo, String descripcion, String tipoArchivo, byte[] contenido, Usuarios usuarios) {
        this.nombreArchivo = nombreArchivo;
        this.descripcion = descripcion;
        this.tipoArchivo = tipoArchivo;
        this.contenido = contenido;
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
}
