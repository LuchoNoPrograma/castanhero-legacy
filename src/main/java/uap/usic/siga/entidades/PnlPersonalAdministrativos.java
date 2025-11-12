
package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "pnl_personal_administrativo")
public class PnlPersonalAdministrativos extends SigaUsicGestiones {

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_personal_administrativo")
    private Long idPnlPersonalAdministrativo;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecInicio;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_final")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecFinal;

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


    public Long getIdPnlPersonalAdministrativo() {
        return idPnlPersonalAdministrativo;
    }

    public void setIdPnlPersonalAdministrativo(Long idPnlPersonalAdministrativo) {
        this.idPnlPersonalAdministrativo = idPnlPersonalAdministrativo;
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
