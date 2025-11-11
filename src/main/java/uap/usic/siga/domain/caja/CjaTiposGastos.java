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
 * Entidad de dominio para tipos de gastos
 * Define los tipos específicos de gastos asociados a una clasificación
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_tipos_gastos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaTiposGastos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_tipo_gasto")
    private Long idCjaTipoGasto;

    @NotNull(message = "La clasificación del gasto es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cja_gasto_clasificacion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaGastosClasificaciones cjaGastosClasificaciones;

    @Column(name = "nro_tipo_gasto")
    private Integer nroTipoGasto;

    @NotNull(message = "El tipo de gasto es obligatorio")
    @Column(name = "tipo_gasto")
    private String tipoGasto;
}
