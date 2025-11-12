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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


/**
 * Rectorado - USIC
 * Fecha: 2019-07-29
 * @author Freddy Morales
 */
@Entity
@Table(name = "cja_cnt_tipos_gastos")
public class CjaCntTiposGastos extends SigaUsicGestiones{
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_tipo_gasto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposGastos cjaTiposGastos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_cnt_tipo_gasto")
    private Long idCjaCntTipoGasto;

    @Column(name = "totalGasto")
    @NotNull
    private Double totalGasto;

    public CjaTiposGastos getCjaTiposGastos() {
        return cjaTiposGastos;
    }

    public void setCjaTiposGastos(CjaTiposGastos cjaTiposGastos) {
        this.cjaTiposGastos = cjaTiposGastos;
    }

    public Long getIdCjaCntTipoGasto() {
        return idCjaCntTipoGasto;
    }

    public void setIdCjaCntTipoGasto(Long idCjaCntTipoGasto) {
        this.idCjaCntTipoGasto = idCjaCntTipoGasto;
    }

    public Double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(Double totalGasto) {
        this.totalGasto = totalGasto;
    }

    
}
