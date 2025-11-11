package uap.usic.siga.domain.congreso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "cng_comisiones")
public class CngComisiones extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cng_comision")
    private Long idCngComision;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cng_congresista")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngCongresistas cngCongresistas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_comision")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngTiposComisiones cngTiposComisiones;

    @Column(name = "obs-")
    private String obs;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecInicio;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_final", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecFinal;

    public Long getIdCngComision() {
        return idCngComision;
    }

    public void setIdCngComision(Long idCngComision) {
        this.idCngComision = idCngComision;
    }

    public CngCongresistas getCngCongresistas() {
        return cngCongresistas;
    }

    public void setCngCongresistas(CngCongresistas cngCongresistas) {
        this.cngCongresistas = cngCongresistas;
    }

    public CngTiposComisiones getCngTiposComisiones() {
        return cngTiposComisiones;
    }

    public void setCngTiposComisiones(CngTiposComisiones cngTiposComisiones) {
        this.cngTiposComisiones = cngTiposComisiones;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecFinal() {
        return fecFinal;
    }

    public void setFecFinal(Date fecFinal) {
        this.fecFinal = fecFinal;
    }
}
