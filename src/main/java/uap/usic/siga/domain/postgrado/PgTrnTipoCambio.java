package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_trn_tipos_cambios")
public class PgTrnTipoCambio extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cambio")
    private Long idTipoCambio;

    @Column(name = "tipo_cambio")
    private Double tipoCambio;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "tipoCambio")
    @JsonIgnore
    private PgTrnTipoMoneda tipoMoneda;

    public Long getIdTipoCambio() {
        return idTipoCambio;
    }

    public void setIdTipoCambio(Long idTipoCambio) {
        this.idTipoCambio = idTipoCambio;
    }

    public Double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public PgTrnTipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(PgTrnTipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
}
