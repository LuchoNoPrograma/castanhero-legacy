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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "menues")
public class Menues extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_enlace", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuEnlaces mnuEnlaces;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_tipo_funcion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuTiposFunciones mnuTiposFunciones;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Long idMenu;

   

    public Menues(MnuEnlaces mnuEnlaces, MnuTiposFunciones mnuTiposFunciones) {
        this.mnuEnlaces = mnuEnlaces;
        this.mnuTiposFunciones = mnuTiposFunciones;
    }

    public Menues() {
    }

    public MnuEnlaces getMnuEnlaces() {
        return mnuEnlaces;
    }

    public void setMnuEnlaces(MnuEnlaces mnuEnlaces) {
        this.mnuEnlaces = mnuEnlaces;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public MnuTiposFunciones getMnuTiposFunciones() {
        return mnuTiposFunciones;
    }

    public void setMnuTiposFunciones(MnuTiposFunciones mnuTiposFunciones) {
        this.mnuTiposFunciones = mnuTiposFunciones;
    }

  

}
