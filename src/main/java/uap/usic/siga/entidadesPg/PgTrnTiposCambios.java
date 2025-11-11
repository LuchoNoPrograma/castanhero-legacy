package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pg_trn_tipos_cambios")
public class PgTrnTiposCambios extends SigaUsicRevisiones{
    /*===================RELACIONES===================
     * ult_usuario did_usuario
     */
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "tipoCambio")
    @JsonIgnore
    private PgTrnTiposMonedas tipoMoneda;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cambio")
    private Long idTipoCambio;

    
    //tipo_cambio dmoneda -> Observar
    @Column(name = "tipo_cambio")
    private Double tipoCambio;


    public PgTrnTiposMonedas getTipoMoneda() {
        return tipoMoneda;
    }


    public void setTipoMoneda(PgTrnTiposMonedas tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }


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

    
}
