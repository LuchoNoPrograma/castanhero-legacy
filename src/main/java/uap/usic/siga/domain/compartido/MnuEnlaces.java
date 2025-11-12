package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa enlaces de menú.
 */
@Entity
@Table(name = "mnu_enlaces")
public class MnuEnlaces extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_enlace")
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_enlace", nullable = false, length = 100)
    private String mnuEnlace;

    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_ruta", nullable = false, length = 100)
    private String mnuRuta;

    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_imagen", nullable = false, length = 100)
    private String mnuImagen;

    @Column(name = "mnu_nivel")
    private Integer mnuNivel;

    @Column(name = "mnu_enlace_orden")
    private Integer mnuEnlaceOrden;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_tipo_enlace", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuTiposEnlaces mnuTiposEnlaces;

    public MnuEnlaces() {
    }

    public MnuEnlaces(String mnuEnlace, String mnuRuta, String mnuImagen, Integer mnuNivel, Integer mnuEnlaceOrden, MnuTiposEnlaces mnuTiposEnlaces) {
        this.mnuEnlace = mnuEnlace;
        this.mnuRuta = mnuRuta;
        this.mnuImagen = mnuImagen;
        this.mnuNivel = mnuNivel;
        this.mnuEnlaceOrden = mnuEnlaceOrden;
        this.mnuTiposEnlaces = mnuTiposEnlaces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMnuEnlace() {
        return mnuEnlace;
    }

    public void setMnuEnlace(String mnuEnlace) {
        this.mnuEnlace = mnuEnlace;
    }

    public String getMnuRuta() {
        return mnuRuta;
    }

    public void setMnuRuta(String mnuRuta) {
        this.mnuRuta = mnuRuta;
    }

    public String getMnuImagen() {
        return mnuImagen;
    }

    public void setMnuImagen(String mnuImagen) {
        this.mnuImagen = mnuImagen;
    }

    public Integer getMnuNivel() {
        return mnuNivel;
    }

    public void setMnuNivel(Integer mnuNivel) {
        this.mnuNivel = mnuNivel;
    }

    public Integer getMnuEnlaceOrden() {
        return mnuEnlaceOrden;
    }

    public void setMnuEnlaceOrden(Integer mnuEnlaceOrden) {
        this.mnuEnlaceOrden = mnuEnlaceOrden;
    }

    public MnuTiposEnlaces getMnuTiposEnlaces() {
        return mnuTiposEnlaces;
    }

    public void setMnuTiposEnlaces(MnuTiposEnlaces mnuTiposEnlaces) {
        this.mnuTiposEnlaces = mnuTiposEnlaces;
    }
}
