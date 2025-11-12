package uap.usic.siga.entidadesPg;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_cnt_pagos")
public class PgCntPago extends SigaUsicRevisiones{
    /*=================RELACIONES======================
     * id_contrato
     * 
     * _id_estado did_estado
     * 
     * ult_usuario did_usuario DEFAULT 1
     * 
     * id_materia dentero4 DEFAULT 0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cnt_pago")
    private Long id_cnt_pago;

    @NotNull
    private Integer nro_pago;

    @NotNull
    private Short gestion;  //Por el momento es heredado de SigaUsicRevisiones

    @NotNull
    private Double monto;   //numeric(7,2)

    @NotNull
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fec_pago;

    @NotNull
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fec_cancelacion;

    @NotNull
    private Double pago_parcial; //numeric(7,2)

    private Double saldo; //numeric(7,2)

    private String observacion;
}
