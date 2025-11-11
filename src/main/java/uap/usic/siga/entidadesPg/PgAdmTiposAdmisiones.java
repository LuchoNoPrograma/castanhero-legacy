package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pg_adm_tipos_admisiones")
public class PgAdmTiposAdmisiones extends SigaUsicRevisiones{
    

    //---------------Llave Tipo admision----------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_admision")
    private Long idTipoAdmision;
    
    //-------------Atributos--------------------
    @Column(name = "tipo_admision")
    private String tipoAdmision;

    @Column(name="valor_descuento")
    private Integer valorDescuento;

    private String detalles;

    public PgAdmTiposAdmisiones() {
    }

    public PgAdmTiposAdmisiones(String tipoAdmision, Integer valorDescuento) {
        this.tipoAdmision = tipoAdmision;
        this.valorDescuento = valorDescuento;
    }

    public Long getIdTipoAdmision() {
        return idTipoAdmision;
    }

    public void setIdTipoAdmision(Long idTipoAdmision) {
        this.idTipoAdmision = idTipoAdmision;
    }

    public String getTipoAdmision() {
        return tipoAdmision;
    }

    public void setTipoAdmision(String tipoAdmision) {
        this.tipoAdmision = tipoAdmision;
    }

    public Integer getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(Integer valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    
    //-------- falta fechas modificaciones y usuario que registro
    
    
}
