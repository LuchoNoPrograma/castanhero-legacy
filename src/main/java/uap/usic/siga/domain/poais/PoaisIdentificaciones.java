package uap.usic.siga.domain.poais;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.personal.PnlCargos;
import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "poais_identificaciones")
public class PoaisIdentificaciones extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_identificacion")
    private Long idIdentificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_poai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Poais poais;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_cargos", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlCargos pnlCargos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_supervisor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisSupervisores poaisSupervisores;

    @Column(name = "relacion_interinstitucional")
    private String relacionInterinstitucional;

    @Column(name = "relacion_intrainstitucional")
    private String relacionIntrainstitucional;

    public Long getIdIdentificacion() {
        return idIdentificacion;
    }

    public void setIdIdentificacion(Long idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public Poais getPoais() {
        return poais;
    }

    public void setPoais(Poais poais) {
        this.poais = poais;
    }

    public PnlCargos getPnlCargos() {
        return pnlCargos;
    }

    public void setPnlCargos(PnlCargos pnlCargos) {
        this.pnlCargos = pnlCargos;
    }

    public PoaisSupervisores getPoaisSupervisores() {
        return poaisSupervisores;
    }

    public void setPoaisSupervisores(PoaisSupervisores poaisSupervisores) {
        this.poaisSupervisores = poaisSupervisores;
    }

    public String getRelacionInterinstitucional() {
        return relacionInterinstitucional;
    }

    public void setRelacionInterinstitucional(String relacionInterinstitucional) {
        this.relacionInterinstitucional = relacionInterinstitucional;
    }

    public String getRelacionIntrainstitucional() {
        return relacionIntrainstitucional;
    }

    public void setRelacionIntrainstitucional(String relacionIntrainstitucional) {
        this.relacionIntrainstitucional = relacionIntrainstitucional;
    }
}
