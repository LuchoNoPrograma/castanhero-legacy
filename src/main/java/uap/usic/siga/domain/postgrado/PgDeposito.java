package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import uap.usic.siga.domain.compartido.Usuarios;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "deposito")
public class PgDeposito extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deposito")
    private Long idDeposito;

    @Column(name = "cod_deposito")
    private String codDeposito;

    @Column(name = "cant_deposito")
    private Float cantDeposito;

    @Transient
    private MultipartFile file;

    @Transient
    private String nombreArchivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pg_archivo_adjunto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgArchivoAdjunto archivoAdjunto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_pst_programas", referencedColumnName = "id_pst_programas")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPstPrograma pstPrograma;

    public Long getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Long idDeposito) {
        this.idDeposito = idDeposito;
    }

    public String getCodDeposito() {
        return codDeposito;
    }

    public void setCodDeposito(String codDeposito) {
        this.codDeposito = codDeposito;
    }

    public Float getCantDeposito() {
        return cantDeposito;
    }

    public void setCantDeposito(Float cantDeposito) {
        this.cantDeposito = cantDeposito;
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

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public PgArchivoAdjunto getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(PgArchivoAdjunto archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public PgPstPrograma getPstPrograma() {
        return pstPrograma;
    }

    public void setPstPrograma(PgPstPrograma pstPrograma) {
        this.pstPrograma = pstPrograma;
    }
}
