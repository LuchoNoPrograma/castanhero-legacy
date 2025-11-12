package uap.usic.siga.domain.caja;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uap.usic.siga.entidades.SigaUsicGestiones;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entidad de dominio para pedidos de gastos
 * Gestiona las solicitudes de gastos
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_pedidos_gastos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaPedidosGastos extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_pedido_gasto")
    private Long idCjaPedidoGasto;

    @NotNull(message = "La fecha del pedido es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_pedido", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecPedido;
}
