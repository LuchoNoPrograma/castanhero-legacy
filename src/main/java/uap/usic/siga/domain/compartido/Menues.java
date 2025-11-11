package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

/**
 * Entidad que representa menús del sistema.
 */
@Entity
@Table(name = "menues")
public class Menues extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Long id;

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

    public Menues() {
    }

    public Menues(MnuEnlaces mnuEnlaces, MnuTiposFunciones mnuTiposFunciones) {
        this.mnuEnlaces = mnuEnlaces;
        this.mnuTiposFunciones = mnuTiposFunciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MnuEnlaces getMnuEnlaces() {
        return mnuEnlaces;
    }

    public void setMnuEnlaces(MnuEnlaces mnuEnlaces) {
        this.mnuEnlaces = mnuEnlaces;
    }

    public MnuTiposFunciones getMnuTiposFunciones() {
        return mnuTiposFunciones;
    }

    public void setMnuTiposFunciones(MnuTiposFunciones mnuTiposFunciones) {
        this.mnuTiposFunciones = mnuTiposFunciones;
    }
}
