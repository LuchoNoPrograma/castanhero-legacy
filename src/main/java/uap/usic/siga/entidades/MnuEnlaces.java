package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "mnu_enlaces")
public class MnuEnlaces extends SigaUsicRevisiones{
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_tipo_enlace", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuTiposEnlaces mnuTiposEnlaces;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_enlace")   
    private Long idMnuEnlace;
    
    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_enlace")
    private String mnuEnlace;

    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_ruta")
    private String mnuRuta;
    
    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_imagen")
    private String mnuImagen;

    @Column(name = "mnu_nivel")
    private Integer mnuNivel;

    @Column(name = "mnu_enlace_orden")
    private Integer mnuEnlaceOrden;

    

    public MnuEnlaces(MnuTiposEnlaces mnuTiposEnlaces, @Size(max = 100) @NotNull String mnuEnlace,
            @Size(max = 100) @NotNull String mnuRuta, @Size(max = 100) @NotNull String mnuImagen, Integer mnuNivel,
            Integer mnuEnlaceOrden) {
        this.mnuTiposEnlaces = mnuTiposEnlaces;
        this.mnuEnlace = mnuEnlace;
        this.mnuRuta = mnuRuta;
        this.mnuImagen = mnuImagen;
        this.mnuNivel = mnuNivel;
        this.mnuEnlaceOrden = mnuEnlaceOrden;
    }

    public MnuEnlaces() {
    }

    public String getMnuImagen() {
        return mnuImagen;
    }

    public void setMnuImagen(String mnuImagen) {
        this.mnuImagen = mnuImagen;
    }

    
    
    public MnuTiposEnlaces getMnuTiposEnlaces() {
        return mnuTiposEnlaces;
    }

    public void setMnuTiposEnlaces(MnuTiposEnlaces mnuTiposEnlaces) {
        this.mnuTiposEnlaces = mnuTiposEnlaces;
    }

    public Long getIdMnuEnlace() {
        return idMnuEnlace;
    }

    public void setIdMnuEnlace(Long idMnuEnlace) {
        this.idMnuEnlace = idMnuEnlace;
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

    
}
