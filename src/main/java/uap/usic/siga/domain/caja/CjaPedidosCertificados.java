package uap.usic.siga.domain.caja;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uap.usic.siga.entidades.SigaUsicGestiones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entidad de dominio para certificados de pedidos
 * Gestiona la certificación de pedidos de gastos
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_pedidos_certificados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaPedidosCertificados extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_pedido_certificado")
    private Long idCjaPedidoCertificado;

    @NotNull(message = "La fecha del certificado es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_certificado", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecCertificado;
}
