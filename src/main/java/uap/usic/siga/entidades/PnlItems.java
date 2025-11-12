package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "pnl_items")
public class PnlItems extends SigaUsicRevisiones{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_item")
    private Long idPnlItem;

    @Column(name = "item")
    private String item;

    @Column(name = "codigo_item")
    private String codigoItem;

    public Long getIdPnlItem() {
        return idPnlItem;
    }

    public void setIdPnlItem(Long idPnlItem) {
        this.idPnlItem = idPnlItem;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    
}
