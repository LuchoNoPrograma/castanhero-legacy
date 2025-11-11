package uap.usic.siga.domain.personal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.Personas;
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
import java.util.Date;

@Entity
@Table(name = "pnl_personal_administrativo")
public class PnlPersonalAdministrativos extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_personal_administrativo")
    private Long idPnlPersonalAdministrativo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_item", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlItems pnlItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_cargos", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlCargos pnlCargos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_tipo_administrativo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlTiposAdministrativos pnlTiposAdministrativos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_funcional", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsUnidadesFuncionales insUnidadesFuncionales;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_inicio")
    @Temporal(TemporalType.DATE)
    private Date fecInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_final")
    @Temporal(TemporalType.DATE)
    private Date fecFinal;

    public Long getIdPnlPersonalAdministrativo() {
        return idPnlPersonalAdministrativo;
    }

    public void setIdPnlPersonalAdministrativo(Long idPnlPersonalAdministrativo) {
        this.idPnlPersonalAdministrativo = idPnlPersonalAdministrativo;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public PnlItems getPnlItems() {
        return pnlItems;
    }

    public void setPnlItems(PnlItems pnlItems) {
        this.pnlItems = pnlItems;
    }

    public PnlCargos getPnlCargos() {
        return pnlCargos;
    }

    public void setPnlCargos(PnlCargos pnlCargos) {
        this.pnlCargos = pnlCargos;
    }

    public PnlTiposAdministrativos getPnlTiposAdministrativos() {
        return pnlTiposAdministrativos;
    }

    public void setPnlTiposAdministrativos(PnlTiposAdministrativos pnlTiposAdministrativos) {
        this.pnlTiposAdministrativos = pnlTiposAdministrativos;
    }

    public InsUnidadesFuncionales getInsUnidadesFuncionales() {
        return insUnidadesFuncionales;
    }

    public void setInsUnidadesFuncionales(InsUnidadesFuncionales insUnidadesFuncionales) {
        this.insUnidadesFuncionales = insUnidadesFuncionales;
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
