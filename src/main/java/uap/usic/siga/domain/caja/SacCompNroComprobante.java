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

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad de dominio para números de comprobantes
 * Gestiona la numeración secuencial de comprobantes contables
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_comp_nro_comprobante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacCompNroComprobante extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_comp_nro_comprobante")
    private Long idSacCompNroComprobante;

    @NotNull(message = "El comprobante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacComprobantes sacComprobantes;

    @NotNull(message = "El número de comprobante es obligatorio")
    @Column(name = "sac_nro_comprobante", nullable = false)
    private Integer sacNroComprobante;
}
