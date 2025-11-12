package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tipos_documentos")
public class TiposDocumentos extends SigaUsicRevisiones{            
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipos_documentos")
    private Long idTipodocumento;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    public TiposDocumentos(Long idTipodocumento, String tipoDocumento) {
        this.idTipodocumento = idTipodocumento;
        this.tipoDocumento = tipoDocumento;
    }

    public TiposDocumentos(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TiposDocumentos() {
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Long getIdTipodocumento() {
        return idTipodocumento;
    }

    public void setIdTipodocumento(Long idTipodocumento) {
        this.idTipodocumento = idTipodocumento;
    }
}
