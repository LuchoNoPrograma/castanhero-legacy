package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "pg_trn_tipos_monedas")
public class PgTrnTiposMonedas extends SigaUsicRevisiones{
    /*===================RELACIONES================== 
     * 
     * ult_usuario did_usuario
     * 
    */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_tipo_cambio", referencedColumnName = "id_tipo_cambio")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgTrnTiposCambios tipoCambio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_moneda")
    private Long idTipoMoneda;

    @Column(name = "tipo_moneda")
    private String tipoMoneda;

    private String simbolo;

    public PgTrnTiposCambios getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(PgTrnTiposCambios tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

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

    
}
