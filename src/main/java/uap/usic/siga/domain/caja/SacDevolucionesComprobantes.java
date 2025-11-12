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
import uap.usic.siga.entidades.SigaUsicRevisiones;
import uap.usic.siga.entidades.Usuarios;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;

/**
 * Entidad de dominio para devoluciones de comprobantes
 * Gestiona el registro de devolución de comprobantes prestados
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_devoluciones_comprobantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacDevolucionesComprobantes extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_devolucion_comprobante")
    private Long idSacDevolucionComprobante;

    @NotNull(message = "La persona es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @NotNull(message = "La fecha de devolución es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_devolucion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecDevolucion;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "hora_devolucion")
    private LocalTime horaDevolucion;

    @Column(name = "gestion_devolucion")
    private Integer gestionDevolucion;
}
