package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_documentos")
public class PgTipoDocumento extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipos_documentos")
    private Long idTipoDocumento;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    public PgTipoDocumento() {
    }

    public PgTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public PgTipoDocumento(Long idTipoDocumento, String tipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.tipoDocumento = tipoDocumento;
    }

    public Long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
