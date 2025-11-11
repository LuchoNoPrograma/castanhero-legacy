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
import uap.usic.siga.entidades.SisMeses;
import uap.usic.siga.entidades.Usuarios;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad de dominio para carpetas de archivo contable
 * Gestiona las carpetas físicas donde se archivan los comprobantes
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_carpetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacCarpetas extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_carpeta")
    private Long idSacCarpeta;

    @NotNull(message = "El tipo de carpeta es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_tipo_carpeta", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacTiposCarpetas sacTiposCarpetas;

    @NotNull(message = "El estante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_estante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacEstantes sacEstantes;

    @NotNull(message = "El mes es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mes", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SisMeses sisMeses;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @NotNull(message = "El código de carpeta es obligatorio")
    @Column(name = "sac_codigo_carpeta", nullable = false)
    private String sacCodigoCarpeta;

    @Column(name = "sac_numero_carpeta")
    private Integer sacNumeroCarpeta;
}
