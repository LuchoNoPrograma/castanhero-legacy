package uap.usic.siga.domain.caja;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.SigaUsicGestiones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad de dominio para contabilización de gastos por unidad funcional
 * Gestiona totales de gastos por unidad administrativa
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_cnt_unidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaCntUnidades extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_cnt_unidades")
    private Long idCjaCntUnidades;

    @NotNull(message = "La unidad funcional es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_funcional", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsUnidadesFuncionales insUnidadesFuncionales;

    @NotNull(message = "El total de gasto es obligatorio")
    @Column(name = "totalGasto", nullable = false)
    private Double totalGasto;
}
