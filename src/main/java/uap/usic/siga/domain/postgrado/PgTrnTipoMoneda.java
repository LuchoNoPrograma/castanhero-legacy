package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pg_trn_tipos_monedas")
public class PgTrnTipoMoneda extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_moneda")
    private Long idTipoMoneda;

    @Column(name = "tipo_moneda")
    private String tipoMoneda;

    @Column(name = "simbolo")
    private String simbolo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_tipo_cambio", referencedColumnName = "id_tipo_cambio")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgTrnTipoCambio tipoCambio;

    public Long getIdTipoMoneda() {
        return idTipoMoneda;
    }

    public void setIdTipoMoneda(Long idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public PgTrnTipoCambio getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(PgTrnTipoCambio tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}
