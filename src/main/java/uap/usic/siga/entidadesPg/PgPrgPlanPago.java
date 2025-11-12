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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_prg_planes_pagos")
public class PgPrgPlanPago extends SigaUsicRevisiones{
    /*================RELACIONES==================
     * ult_usuario did_usuario
     * 
     * id_programa
     * 
     * id_plan
     * 
     * id_tipo_clasificacion
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prg_plan_pago")
    private Long id_prg_plan_pago;

    //colegiatura dmoneda2 <- Observar

    private Short nro_cuotas;

    //monto_parcial dmoneda2 <- Observar

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private Date fecha_ini;
}
