package uap.usic.siga.domain.caja;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.SigaUsicGestiones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad de dominio para contabilización de gastos por tipo
 * Gestiona totales de gastos por tipo de gasto
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_cnt_tipos_gastos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaCntTiposGastos extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_cnt_tipo_gasto")
    private Long idCjaCntTipoGasto;

    @NotNull(message = "El tipo de gasto es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cja_tipo_gasto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposGastos cjaTiposGastos;

    @NotNull(message = "El total de gasto es obligatorio")
    @Column(name = "totalGasto", nullable = false)
    private Double totalGasto;
}
