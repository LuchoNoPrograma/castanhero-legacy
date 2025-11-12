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
 * Entidad de dominio para estados de comprobantes
 * Define los estados del ciclo de vida de un comprobante (prestado, archivado, etc.)
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_estados_comprobantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacEstadosComprobantes extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_estado_comprobante")
    private Long idSacEstadoComprobante;

    @NotNull(message = "El nombre del estado es obligatorio")
    @Column(name = "sac_nombre_estado", nullable = false)
    private String sacNombreEstado;
}
