package uap.usic.siga.domain.caja;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad de dominio para tipos de pagos contables
 * Define las modalidades de pago (efectivo, cheque, transferencia, etc.)
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_tipos_pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacTiposPagos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_tipo_pago")
    private Long idSacTipoPago;

    @NotNull(message = "El tipo de pago es obligatorio")
    @Column(name = "sac_tipo_pago", nullable = false)
    private String sacTipoPago;
}
