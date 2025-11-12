package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.Usuarios;

@Entity
@Table(name = "deposito")
public class Depositos extends SigaUsicRevisiones{

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
    @JoinColumn(name = "fk_id_pst_programas", referencedColumnName = "id_pst_programas")
    private PgPstProgramas pgPstProgramas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deposito")
    private Long idDeposito;

    private String codDeposito;
    
    private Float cantDeposito;

    @Transient
    private MultipartFile file; 
    
    @Transient
    private String nombreArchivo;
    
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
    public PgPstProgramas getPgPstProgramas() {
        return pgPstProgramas;
    }
    public void setPgPstProgramas(PgPstProgramas pgPstProgramas) {
        this.pgPstProgramas = pgPstProgramas;
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
