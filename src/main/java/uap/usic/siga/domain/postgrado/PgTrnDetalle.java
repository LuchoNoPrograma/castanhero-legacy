package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pg_trn_detalles")
public class PgTrnDetalle extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalle;

    @Column(name = "cantidad")
    private Integer cantidad = 1;

    @Column(name = "costo")
    private BigDecimal costo;

    @Column(name = "pagado")
    private BigDecimal pagado;

    @Column(name = "descuento")
    private BigDecimal descuento;

    @Column(name = "ulp_ip")
    private String ulpIp;

    @Column(name = "mpst")
    private Integer mpst;

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getPagado() {
        return pagado;
    }

    public void setPagado(BigDecimal pagado) {
        this.pagado = pagado;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getUlpIp() {
        return ulpIp;
    }

    public void setUlpIp(String ulpIp) {
        this.ulpIp = ulpIp;
    }

    public Integer getMpst() {
        return mpst;
    }

    public void setMpst(Integer mpst) {
        this.mpst = mpst;
    }
}
