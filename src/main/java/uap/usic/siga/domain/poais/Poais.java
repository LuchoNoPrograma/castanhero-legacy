package uap.usic.siga.domain.poais;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.personal.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.SigaUsicGestiones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "poais")
public class Poais extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poai")
    private Long idPoai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_personal_administrativo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlPersonalAdministrativos pnlPersonalAdministrativos;

    @Column(name = "descripcion")
    private String descripcion;

    public Long getIdPoai() {
        return idPoai;
    }

    public void setIdPoai(Long idPoai) {
        this.idPoai = idPoai;
    }

    public PnlPersonalAdministrativos getPnlPersonalAdministrativos() {
        return pnlPersonalAdministrativos;
    }

    public void setPnlPersonalAdministrativos(PnlPersonalAdministrativos pnlPersonalAdministrativos) {
        this.pnlPersonalAdministrativos = pnlPersonalAdministrativos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
