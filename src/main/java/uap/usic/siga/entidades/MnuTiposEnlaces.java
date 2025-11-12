package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "mnu_tipos_enlaces")
public class MnuTiposEnlaces extends SigaUsicRevisiones{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_tipo_enlace")   
    private Long idMnuTipoEnlace;
    
    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_tipo_enlace")
    private String mnuTipoEnlace;

    @Size(max = 100)
    @NotNull
    @Column(name = "mnu_imagen")
    private String mnuImagen;

    @Column(name = "mnu_tipo_enlace_orden")
    private Integer mnuTipoEnlaceOrden;

    

    public MnuTiposEnlaces(@Size(max = 100) @NotNull String mnuTipoEnlace, @Size(max = 100) @NotNull String mnuImagen,
            Integer mnuTipoEnlaceOrden) {
        this.mnuTipoEnlace = mnuTipoEnlace;
        this.mnuImagen = mnuImagen;
        this.mnuTipoEnlaceOrden = mnuTipoEnlaceOrden;
    }

    public MnuTiposEnlaces() {
    }

    public String getMnuImagen() {
        return mnuImagen;
    }

    public void setMnuImagen(String mnuImagen) {
        this.mnuImagen = mnuImagen;
    }

    
    public Long getIdMnuTipoEnlace() {
        return idMnuTipoEnlace;
    }

    public void setIdMnuTipoEnlace(Long idMnuTipoEnlace) {
        this.idMnuTipoEnlace = idMnuTipoEnlace;
    }

    public String getMnuTipoEnlace() {
        return mnuTipoEnlace;
    }

    public void setMnuTipoEnlace(String mnuTipoEnlace) {
        this.mnuTipoEnlace = mnuTipoEnlace;
    }

    public Integer getMnuTipoEnlaceOrden() {
        return mnuTipoEnlaceOrden;
    }

    public void setMnuTipoEnlaceOrden(Integer mnuTipoEnlaceOrden) {
        this.mnuTipoEnlaceOrden = mnuTipoEnlaceOrden;
    }

    
    
}
