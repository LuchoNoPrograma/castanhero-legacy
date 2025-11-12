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
 * Entidad de dominio para estantes de archivo
 * Gestiona la ubicación física de las carpetas contables
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Entity
@Table(name = "sac_estantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SacEstantes extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_estante")
    private Long idSacEstante;

    @NotNull(message = "El nombre del estante es obligatorio")
    @Column(name = "sac_nombre_estante", nullable = false)
    private String sacNombreEstante;

    @Column(name = "sac_codigo_estante")
    private String sacCodigoEstante;

    @Column(name = "sac_numero_estante")
    private Integer sacNumeroEstante;
}
