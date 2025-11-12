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
 * Entidad de dominio para tipos de carpetas de archivo
 * Define las categorías de carpetas para organización documental
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_tipos_carpetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacTiposCarpetas extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_tipo_carpeta")
    private Long idSacTipoCarpeta;

    @NotNull(message = "El tipo de carpeta es obligatorio")
    @Column(name = "sac_tipo_carpeta", nullable = false)
    private String sacTipoCarpeta;
}
