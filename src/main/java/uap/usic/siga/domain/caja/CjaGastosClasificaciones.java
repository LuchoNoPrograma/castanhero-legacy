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
 * Entidad de dominio para clasificaciones de gastos
 * Representa las sub-categorías de gastos dentro de un tipo de clasificación
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_gastos_clasificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaGastosClasificaciones extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_gasto_clasificacion")
    private Long idCjaGastoClasificacion;

    @NotNull(message = "El tipo de clasificación es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cja_tipo_clasificacion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposClasificaciones cjaTiposClasificaciones;

    @NotNull(message = "La clasificación del gasto es obligatoria")
    @Column(name = "gasto_clasificacion")
    private String gastoClasificacion;

    @Column(name = "nro_gasto_clasificacion")
    private Integer nroGastoClasificacion;
}
