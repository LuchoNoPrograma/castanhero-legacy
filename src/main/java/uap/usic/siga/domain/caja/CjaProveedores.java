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
 * Entidad de dominio para proveedores
 * Gestiona la información de los proveedores de bienes y servicios
 *
 * @author Sistema de Caja - USIC
 */
@Entity
@Table(name = "cja_proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CjaProveedores extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_proveedor")
    private Long idCjaProveedor;

    @NotNull(message = "El nombre del proveedor es obligatorio")
    @Column(name = "proveedor")
    private String proveedor;

    @NotNull(message = "La sigla del proveedor es obligatoria")
    @Size(max = 20, message = "La sigla no puede exceder 20 caracteres")
    @Column(name = "sigla", length = 20)
    private String sigla;
}
