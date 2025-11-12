package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "pg_cnt_pagos")
public class PgCntPago extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cnt_pago")
    private Long idCntPago;

    @NotNull
    @Column(name = "nro_pago")
    private Integer nroPago;

    @NotNull
    @Column(name = "gestion")
    private Short gestion;

    @NotNull
    @Column(name = "monto")
    private Double monto;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_cancelacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCancelacion;

    @NotNull
    @Column(name = "pago_parcial")
    private Double pagoParcial;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "observacion")
    private String observacion;

    public Long getIdCntPago() {
        return idCntPago;
    }

    public void setIdCntPago(Long idCntPago) {
        this.idCntPago = idCntPago;
    }

    public Integer getNroPago() {
        return nroPago;
    }

    public void setNroPago(Integer nroPago) {
        this.nroPago = nroPago;
    }

    public Short getGestion() {
        return gestion;
    }

    public void setGestion(Short gestion) {
        this.gestion = gestion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Double getPagoParcial() {
        return pagoParcial;
    }

    public void setPagoParcial(Double pagoParcial) {
        this.pagoParcial = pagoParcial;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
