package uap.usic.siga.domain.caja;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SigaUsicGestiones;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entidad de dominio para ingresos de caja
 * Gestiona los ingresos de fondos y su control de saldo
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_ingresos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaIngresos extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_ingreso")
    private Long idCjaIngreso;

    @NotNull(message = "El tipo de ingreso es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cja_tipo_ingreso", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposIngresos cjaTiposIngresos;

    @NotNull(message = "La persona es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

    @NotNull(message = "El monto es obligatorio")
    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "saldo")
    private Double saldo;

    @Builder.Default
    @Column(name = "nro_documento")
    private Integer nroDocumento = 0;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_ingreso", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecIngreso;

    @Column(name = "porcentaje_gasto")
    private Double porcentajeGasto;

    @Column(name = "monto_maximo")
    private Double montoMaximo;

    @Column(name = "porcentaje_saldo")
    private Double porcentajeSaldo;

    @Builder.Default
    @Column(name = "caja")
    private Integer caja = 0;
}
