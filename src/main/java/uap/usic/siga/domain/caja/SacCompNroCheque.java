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
 * Entidad de dominio para números de cheques
 * Gestiona la numeración de cheques asociados a comprobantes
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_comp_nro_cheque")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacCompNroCheque extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_comp_nro_cheque")
    private Long idSacCompNroCheque;

    @NotNull(message = "El comprobante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacComprobantes sacComprobantes;

    @NotNull(message = "El número de cheque es obligatorio")
    @Column(name = "sac_nro_cheque", nullable = false)
    private Integer sacNroCheque;
}
