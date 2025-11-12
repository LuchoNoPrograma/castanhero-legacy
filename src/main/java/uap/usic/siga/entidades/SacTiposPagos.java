package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


/**
 *
 * @author Yessenia
 */
@Entity
@Table(name = "sac_tipos_pagos")
public class SacTiposPagos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_tipo_pago")
    private Long idSacTipoPago;

    @NotNull
    @Column(name = "sac_tipo_pago")
    private String sacTipoPago;

    public Long getIdSacTipoPago() {
        return idSacTipoPago;
    }

    public void setIdSacTipoPago(Long idSacTipoPago) {
        this.idSacTipoPago = idSacTipoPago;
    }

    public String getSacTipoPago() {
        return sacTipoPago;
    }

    public void setSacTipoPago(String sacTipoPago) {
        this.sacTipoPago = sacTipoPago;
    }

}
