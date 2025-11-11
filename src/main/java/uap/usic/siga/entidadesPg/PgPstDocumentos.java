
package uap.usic.siga.entidadesPg;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pg_pst_documentos")
public class PgPstDocumentos extends SigaUsicRevisiones{
    /*==================RELACIONES======================*/
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_pst_programa", referencedColumnName = "id_pst_programas")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPstProgramas pgPstProgramas;//Tabla intermedia

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_tipo_documento", referencedColumnName = "id_tipos_documentos")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TiposDocumentos tipodocumento;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pst_documento")
    private Long idPstDocumento;

    @Column(name = "pst_documento")
    private String pstDocumento;

    private String numero;

    private String observacion;

    public PgPstDocumentos() {
    }

    public PgPstDocumentos(Long idPstDocumento, String pstDocumento) {
        this.idPstDocumento = idPstDocumento;
        this.pstDocumento = pstDocumento;
    }
    
    public TiposDocumentos getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(TiposDocumentos tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Long getIdPstDocumento() {
        return idPstDocumento;
    }

    public PgPstDocumentos(String pstDocumento) {
        this.pstDocumento = pstDocumento;
    }

    public void setIdPstDocumento(Long idPstDocumento) {
        this.idPstDocumento = idPstDocumento;
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

    public String getPstDocumento() {
        return pstDocumento;
    }

    public void setPstDocumento(String pstDocumento) {
        this.pstDocumento = pstDocumento;
    }

    public PgPstProgramas getPgPstProgramas() {
        return pgPstProgramas;
    }

    public void setPgPstProgramas(PgPstProgramas pgPstProgramas) {
        this.pgPstProgramas = pgPstProgramas;
    }    
}
