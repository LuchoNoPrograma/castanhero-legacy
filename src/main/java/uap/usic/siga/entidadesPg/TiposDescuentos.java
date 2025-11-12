package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_descuentos")
public class TiposDescuentos extends SigaUsicRevisiones{

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_descuento")
    private Long idTipoDescuento;
	
	@Column(name = "tipo_descuento")
    private String tipoDescuento;

    public Long getIdTipoDescuento() {
        return idTipoDescuento;
    }

    public void setIdTipoDescuento(Long idTipoDescuento) {
        this.idTipoDescuento = idTipoDescuento;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    
}
