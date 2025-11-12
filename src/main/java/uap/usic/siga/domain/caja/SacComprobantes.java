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
import org.springframework.web.multipart.MultipartFile;
import uap.usic.siga.entidades.SigaUsicGestiones;
import uap.usic.siga.entidades.Usuarios;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entidad de dominio para comprobantes contables
 * Representa los comprobantes de pago y documentos contables
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_comprobantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacComprobantes extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_comprobante")
    private Long idSacComprobante;

    @NotNull(message = "El tipo de pago es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_tipo_pago", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacTiposPagos sacTiposPagos;

    @NotNull(message = "El tipo de comprobante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_tipo_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacTiposComprobantes sacTiposComprobantes;

    @NotNull(message = "La carpeta es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_carpeta", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacCarpetas sacCarpetas;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @NotNull(message = "La fecha de elaboración es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_elaboracion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecElaboracion;

    @NotNull(message = "La hoja de ruta es obligatoria")
    @Column(name = "hoja_ruta", nullable = false)
    private Integer hojaRuta;

    @NotNull(message = "La glosa es obligatoria")
    @Column(name = "glosa", nullable = false)
    private String glosa;

    @NotNull(message = "El monto es obligatorio")
    @Column(name = "sac_monto", nullable = false)
    private Double sacMonto;

    @Transient
    private MultipartFile file;

    @Transient
    private String nombreArchivo;
}
