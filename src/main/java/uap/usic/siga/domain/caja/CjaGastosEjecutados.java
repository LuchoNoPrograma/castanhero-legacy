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
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SigaUsicGestiones;
import uap.usic.siga.entidades.Usuarios;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entidad de dominio para gastos ejecutados
 * Registra y gestiona los gastos realizados desde la caja
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_gastos_ejecutados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaGastosEjecutados extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_gasto_ejecutado")
    private Long idCjaGastoEjecutado;

    @NotNull(message = "El tipo de gasto es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cja_tipo_gasto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposGastos cjaTiposGastos;

    @NotNull(message = "El ingreso de caja es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cja_ingreso", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaIngresos cjaIngresos;

    @NotNull(message = "La persona es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

    @NotNull(message = "El proveedor es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cja_proveedor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaProveedores cjaProveedores;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @NotNull(message = "El personal administrativo es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pnl_personal_administrativo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlPersonalAdministrativos pnlPersonalAdministrativos;

    @Column(name = "nro_documento")
    private Integer nroDocumento;

    @NotNull(message = "La fecha del gasto es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_gasto", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecGasto;

    @Lob
    @NotNull(message = "La descripción es obligatoria")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull(message = "El número de factura es obligatorio")
    @Column(name = "nro_factura", nullable = false)
    private String nroFactura;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @NotNull(message = "El total es obligatorio")
    @Column(name = "totalG", nullable = false)
    private Double totalG;

    @NotNull(message = "La certificación de almacén es obligatoria")
    @Column(name = "certificacion_almacen", nullable = false)
    private Integer certificacionAlmacen;

    @Column(name = "retencion")
    private Double retencion;

    @Column(name = "retencion_check")
    private Integer retencionCheck;
}
