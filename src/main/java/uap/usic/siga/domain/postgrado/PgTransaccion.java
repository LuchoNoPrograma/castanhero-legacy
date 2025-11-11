package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadConGestion;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transacciones")
public class PgTransaccion extends EntidadConGestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long idTransaccion;

    @Column(name = "nror_recibo")
    private String nroRecibo;

    @Column(name = "cantidad")
    private Integer cantidad = 1;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "pagado")
    private BigDecimal pagado;

    @Column(name = "descuento")
    private BigDecimal descuento;

    @Column(name = "efectivo")
    private BigDecimal efectivo;

    @Column(name = "deposito")
    private BigDecimal deposito;

    @Column(name = "nro_f_pago")
    private String nroFPago;

    @Column(name = "observacion")
    private String observacion = "Ninguna";

    @Column(name = "ult_ip")
    private String ultIp;

    @Column(name = "mpst")
    private Integer mpst;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_tipo_moneda", referencedColumnName = "id_tipo_moneda")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgTrnTipoMoneda tipoMoneda;

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getNroRecibo() {
        return nroRecibo;
    }

    public void setNroRecibo(String nroRecibo) {
        this.nroRecibo = nroRecibo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public BigDecimal getDeposito() {
        return deposito;
    }

    public void setDeposito(BigDecimal deposito) {
        this.deposito = deposito;
    }

    public String getNroFPago() {
        return nroFPago;
    }

    public void setNroFPago(String nroFPago) {
        this.nroFPago = nroFPago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUltIp() {
        return ultIp;
    }

    public void setUltIp(String ultIp) {
        this.ultIp = ultIp;
    }

    public Integer getMpst() {
        return mpst;
    }

    public void setMpst(Integer mpst) {
        this.mpst = mpst;
    }

    public PgTrnTipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(PgTrnTipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
}
