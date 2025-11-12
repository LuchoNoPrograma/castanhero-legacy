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
import uap.usic.siga.entidades.Usuarios;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad de dominio para archivos adjuntos de comprobantes
 * Gestiona documentos digitales asociados a comprobantes contables
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_comp_archivos_adjuntos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacCompArchivosAdjuntos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_comp_archivo_adjunto")
    private Long idSacCompArchivoAdjunto;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @NotNull(message = "El comprobante es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sac_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacComprobantes sacComprobantes;

    @NotNull(message = "El nombre del archivo es obligatorio")
    @Column(name = "nombre_archivo", nullable = false)
    private String nombreArchivo;

    @Builder.Default
    @Column(name = "descripcion")
    private String descripcion = "Registrado Correctamente";

    @NotNull(message = "El tipo de archivo es obligatorio")
    @Column(name = "tipo_archivo", nullable = false)
    private String tipoArchivo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Builder.Default
    @Column(name = "contenido")
    private byte[] contenido = new byte[100];

    @NotNull(message = "La ruta del archivo es obligatoria")
    @Column(name = "ruta_archivo", nullable = false)
    private String rutaArchivo;
}
