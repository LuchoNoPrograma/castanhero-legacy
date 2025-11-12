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
 * Entidad de dominio para tipos de comprobantes contables
 * Define las categorías de comprobantes (egreso, ingreso, traspaso, etc.)
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_tipos_comprobantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacTiposComprobantes extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_tipo_comprobante")
    private Long idSacTipoComprobante;

    @NotNull(message = "El tipo de comprobante es obligatorio")
    @Column(name = "sac_tipo_comprobante", nullable = false)
    private String sacTipoComprobante;
}
