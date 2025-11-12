package uap.usic.siga.domain.sicoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SigaUsicGestiones;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "scs_prs_contratados")
public class ScsPrsContratado extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_prs_contratado")
    private Long idScsPrsContratado;

    @NotNull
    @Column(name = "scs_nit")
    private String scsNit;

    @NotNull
    @Column(name = "scs_nua_cua")
    private String scsNuaCua;

    @NotNull
    @Column(name = "scs_usuario_rupe")
    private String scsUsuarioRupe;

    @NotNull
    @Column(name = "scs_clave_rupe")
    private String scsClaveRupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    @JsonIgnore
    private Personas persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_scs_archivo_adjunto", nullable = false)
    @JsonIgnore
    private ScsArchivoAdjunto archivoAdjunto;

    @Transient
    private MultipartFile file;

    @Transient
    private String nombreArchivo;

    public Long getIdScsPrsContratado() {
        return idScsPrsContratado;
    }

    public void setIdScsPrsContratado(Long idScsPrsContratado) {
        this.idScsPrsContratado = idScsPrsContratado;
    }

    public String getScsNit() {
        return scsNit;
    }

    public void setScsNit(String scsNit) {
        this.scsNit = scsNit;
    }

    public String getScsNuaCua() {
        return scsNuaCua;
    }

    public void setScsNuaCua(String scsNuaCua) {
        this.scsNuaCua = scsNuaCua;
    }

    public String getScsUsuarioRupe() {
        return scsUsuarioRupe;
    }

    public void setScsUsuarioRupe(String scsUsuarioRupe) {
        this.scsUsuarioRupe = scsUsuarioRupe;
    }

    public String getScsClaveRupe() {
        return scsClaveRupe;
    }

    public void setScsClaveRupe(String scsClaveRupe) {
        this.scsClaveRupe = scsClaveRupe;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public ScsArchivoAdjunto getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(ScsArchivoAdjunto archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
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
