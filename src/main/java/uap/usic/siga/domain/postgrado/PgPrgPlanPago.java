package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pg_prg_planes_pagos")
public class PgPrgPlanPago extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prg_plan_pago")
    private Long idPrgPlanPago;

    @Column(name = "colegiatura")
    private BigDecimal colegiatura;

    @Column(name = "nro_cuotas")
    private Short nroCuotas;

    @Column(name = "monto_parcial")
    private BigDecimal montoParcial;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_ini")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    public Long getIdPrgPlanPago() {
        return idPrgPlanPago;
    }

    public void setIdPrgPlanPago(Long idPrgPlanPago) {
        this.idPrgPlanPago = idPrgPlanPago;
    }

    public BigDecimal getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(BigDecimal colegiatura) {
        this.colegiatura = colegiatura;
    }

    public Short getNroCuotas() {
        return nroCuotas;
    }

    public void setNroCuotas(Short nroCuotas) {
        this.nroCuotas = nroCuotas;
    }

    public BigDecimal getMontoParcial() {
        return montoParcial;
    }

    public void setMontoParcial(BigDecimal montoParcial) {
        this.montoParcial = montoParcial;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
