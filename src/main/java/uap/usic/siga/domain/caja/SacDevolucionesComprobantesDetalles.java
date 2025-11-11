package uap.usic.siga.domain.caja;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.SigaUsicRevisiones;
import uap.usic.siga.entidades.Usuarios;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad de dominio para detalles de devoluciones de comprobantes
 * Registra el detalle de cada comprobante devuelto
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_devoluciones_comprobantes_detalles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacDevolucionesComprobantesDetalles extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_devolucion_comprobante_detalle")
    private Long idSacDevolucionComprobanteDetalle;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @NotNull(message = "El estado del comprobante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_estado_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacEstadosComprobantes sacEstadosComprobantes;

    @NotNull(message = "El préstamo de comprobante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_prestamo_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacPrestamosComprobantes sacPrestamosComprobantes;

    @NotNull(message = "El comprobante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacComprobantes sacComprobantes;

    @NotNull(message = "La devolución de comprobante es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_devolucion_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacDevolucionesComprobantes sacDevolucionesComprobantes;
}
