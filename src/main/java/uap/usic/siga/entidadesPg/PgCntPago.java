package uap.usic.siga.entidadesPg;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

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
