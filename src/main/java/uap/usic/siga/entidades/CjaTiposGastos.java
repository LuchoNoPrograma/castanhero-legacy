package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "cja_tipos_gastos")
public class CjaTiposGastos extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_gasto_clasificacion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaGastosClasificaciones cjaGastosClasificaciones;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_tipo_gasto")
    private Long idCjaTipoGasto;

    @Column(name = "nro_tipo_gasto")
    private Integer nroTipoGasto;

    @Column(name = "tipo_gasto")
    private String tipoGasto;

    public CjaGastosClasificaciones getCjaGastosClasificaciones() {
        return cjaGastosClasificaciones;
    }

    public void setCjaGastosClasificaciones(CjaGastosClasificaciones cjaGastosClasificaciones) {
        this.cjaGastosClasificaciones = cjaGastosClasificaciones;
    }

    

    public Long getIdCjaTipoGasto() {
        return idCjaTipoGasto;
    }

    public void setIdCjaTipoGasto(Long idCjaTipoGasto) {
        this.idCjaTipoGasto = idCjaTipoGasto;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public Integer getNroTipoGasto() {
        return nroTipoGasto;
    }

    public void setNroTipoGasto(Integer nroTipoGasto) {
        this.nroTipoGasto = nroTipoGasto;
    }
}
