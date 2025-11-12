package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


/**
 *
 * @author Yessenia
 */
@Entity
@Table(name = "sac_tipos_comprobantes")
public class SacTiposComprobantes extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_tipo_comprobante")
    private Long idSacTipoComprobante;

    @NotNull
    @Column(name = "sac_tipo_comprobante")
    private String sacTipoComprobante;

    public Long getIdSacTipoComprobante() {
        return idSacTipoComprobante;
    }

    public void setIdSacTipoComprobante(Long idSacTipoComprobante) {
        this.idSacTipoComprobante = idSacTipoComprobante;
    }

    public String getSacTipoComprobante() {
        return sacTipoComprobante;
    }

    public void setSacTipoComprobante(String sacTipoComprobante) {
        this.sacTipoComprobante = sacTipoComprobante;
    }
    
}
