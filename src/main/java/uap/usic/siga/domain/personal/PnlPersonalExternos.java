package uap.usic.siga.domain.personal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.SigaUsicGestiones;

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
@Table(name = "pnl_personal_externos")
public class PnlPersonalExternos extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_personal_externo")
    private Long idPnlPersonalExterno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_tipo_externo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlTiposExternos pnlTiposExternos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_cargos", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlCargos pnlCargos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_funcional", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsUnidadesFuncionales insUnidadesFuncionales;

    public Long getIdPnlPersonalExterno() {
        return idPnlPersonalExterno;
    }

    public void setIdPnlPersonalExterno(Long idPnlPersonalExterno) {
        this.idPnlPersonalExterno = idPnlPersonalExterno;
    }

    public PnlTiposExternos getPnlTiposExternos() {
        return pnlTiposExternos;
    }

    public void setPnlTiposExternos(PnlTiposExternos pnlTiposExternos) {
        this.pnlTiposExternos = pnlTiposExternos;
    }

    public PnlCargos getPnlCargos() {
        return pnlCargos;
    }

    public void setPnlCargos(PnlCargos pnlCargos) {
        this.pnlCargos = pnlCargos;
    }

    public InsUnidadesFuncionales getInsUnidadesFuncionales() {
        return insUnidadesFuncionales;
    }

    public void setInsUnidadesFuncionales(InsUnidadesFuncionales insUnidadesFuncionales) {
        this.insUnidadesFuncionales = insUnidadesFuncionales;
    }
}
