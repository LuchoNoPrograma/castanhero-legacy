
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
 * @author Usuario
 */
@Entity
@Table(name = "pnl_tipos_externos")
public class PnlTiposExternos extends SigaUsicRevisiones{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_tipo_externo")
    private Long idPnlTipoExterno;
    
    @NotNull
    @Column(name = "tipo_externo")
    private String tipoExterno;

    public Long getIdPnlTipoExterno() {
        return idPnlTipoExterno;
    }

    public void setIdPnlTipoExterno(Long idPnlTipoExterno) {
        this.idPnlTipoExterno = idPnlTipoExterno;
    }

    public String getTipoExterno() {
        return tipoExterno;
    }

    public void setTipoExterno(String tipoExterno) {
        this.tipoExterno = tipoExterno;
    }
    
}
