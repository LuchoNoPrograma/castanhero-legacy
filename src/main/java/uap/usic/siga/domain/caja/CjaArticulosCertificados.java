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
 * Entidad de dominio para artículos certificados
 * Gestiona el detalle de artículos certificados en pedidos
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_articulos_certificados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaArticulosCertificados extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_articulo_certificado")
    private Long idCjaArticuloCertificado;

    @NotNull(message = "El artículo es obligatorio")
    @Column(name = "articulo", nullable = false)
    private String articulo;

    @Column(name = "cantidad")
    private Integer cantidad;
}
