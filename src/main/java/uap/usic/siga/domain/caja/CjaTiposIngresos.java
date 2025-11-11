package uap.usic.siga.domain.caja;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad de dominio para tipos de ingresos de caja
 * Representa las categorías de ingresos financieros
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_tipos_ingresos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaTiposIngresos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_tipo_ingreso")
    private Long idCjaTipoIngreso;

    @NotNull(message = "El tipo de ingreso es obligatorio")
    @Size(max = 100, message = "El tipo de ingreso no puede exceder 100 caracteres")
    @Column(name = "tipo_ingreso", length = 100)
    private String tipoIngreso;
}
