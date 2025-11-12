package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa tipos de enlaces de menú.
 */
@Entity
@Table(name = "mnu_tipos_enlaces")
public class MnuTiposEnlaces extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_tipo_enlace")
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_tipo_enlace", nullable = false, length = 100)
    private String mnuTipoEnlace;

    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_imagen", nullable = false, length = 100)
    private String mnuImagen;

    @Column(name = "mnu_tipo_enlace_orden")
    private Integer mnuTipoEnlaceOrden;

    public MnuTiposEnlaces() {
    }

    public MnuTiposEnlaces(String mnuTipoEnlace, String mnuImagen, Integer mnuTipoEnlaceOrden) {
        this.mnuTipoEnlace = mnuTipoEnlace;
        this.mnuImagen = mnuImagen;
        this.mnuTipoEnlaceOrden = mnuTipoEnlaceOrden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMnuTipoEnlace() {
        return mnuTipoEnlace;
    }

    public void setMnuTipoEnlace(String mnuTipoEnlace) {
        this.mnuTipoEnlace = mnuTipoEnlace;
    }

    public String getMnuImagen() {
        return mnuImagen;
    }

    public void setMnuImagen(String mnuImagen) {
        this.mnuImagen = mnuImagen;
    }

    public Integer getMnuTipoEnlaceOrden() {
        return mnuTipoEnlaceOrden;
    }

    public void setMnuTipoEnlaceOrden(Integer mnuTipoEnlaceOrden) {
        this.mnuTipoEnlaceOrden = mnuTipoEnlaceOrden;
    }
}
