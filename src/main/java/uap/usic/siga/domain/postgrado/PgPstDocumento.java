package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "pg_pst_documentos")
public class PgPstDocumento extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pst_documento")
    private Long idPstDocumento;

    @Column(name = "pst_documento")
    private String pstDocumento;

    @Column(name = "numero")
    private String numero;

    @Column(name = "observacion")
    private String observacion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_pst_programa", referencedColumnName = "id_pst_programas")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPstPrograma pstPrograma;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_tipo_documento", referencedColumnName = "id_tipos_documentos")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgTipoDocumento tipoDocumento;

    public PgPstDocumento() {
    }

    public PgPstDocumento(String pstDocumento) {
        this.pstDocumento = pstDocumento;
    }

    public PgPstDocumento(Long idPstDocumento, String pstDocumento) {
        this.idPstDocumento = idPstDocumento;
        this.pstDocumento = pstDocumento;
    }

    public Long getIdPstDocumento() {
        return idPstDocumento;
    }

    public void setIdPstDocumento(Long idPstDocumento) {
        this.idPstDocumento = idPstDocumento;
    }

    public String getPstDocumento() {
        return pstDocumento;
    }

    public void setPstDocumento(String pstDocumento) {
        this.pstDocumento = pstDocumento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public PgPstPrograma getPstPrograma() {
        return pstPrograma;
    }

    public void setPstPrograma(PgPstPrograma pstPrograma) {
        this.pstPrograma = pstPrograma;
    }

    public PgTipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(PgTipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
