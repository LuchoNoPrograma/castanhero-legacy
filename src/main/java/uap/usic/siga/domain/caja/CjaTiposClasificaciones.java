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
 * Entidad de dominio para tipos de clasificaciones de gastos
 * Define las categorías principales para clasificar gastos
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_tipos_clasificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaTiposClasificaciones extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_tipo_clasificacion")
    private Long idCjaTipoClasificacion;

    @NotNull(message = "El tipo de clasificación es obligatorio")
    @Column(name = "tipo_clasificacion")
    private String tipoClasificacion;

    @Column(name = "nro_tipo_clasificacion")
    private Integer nroTipoClasificacion;
}
