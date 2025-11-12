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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Yessenia Velasco
 */
@Entity
@Table(name = "sac_comp_nro_comprobante")
public class SacCompNroComprobante extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacComprobantes sacComprobantes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_comp_nro_comprobante")
    private Long idSacCompNroComprobante;

    @NotNull
    @Column(name = "sac_nro_comprobante")
    private Integer sacNroComprobante;

    public SacComprobantes getSacComprobantes() {
        return sacComprobantes;
    }

    public void setSacComprobantes(SacComprobantes sacComprobantes) {
        this.sacComprobantes = sacComprobantes;
    }

    public Long getIdSacCompNroComprobante() {
        return idSacCompNroComprobante;
    }

    public void setIdSacCompNroComprobante(Long idSacCompNroComprobante) {
        this.idSacCompNroComprobante = idSacCompNroComprobante;
    }

    public Integer getSacNroComprobante() {
        return sacNroComprobante;
    }

    public void setSacNroComprobante(Integer sacNroComprobante) {
        this.sacNroComprobante = sacNroComprobante;
    }

}
