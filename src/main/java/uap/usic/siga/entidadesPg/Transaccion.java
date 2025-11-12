package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transacciones")
public class Transaccion extends SigaUsicGestiones{
    /*===============RELACIONES==================
     * id_sede
     * 
     * id_persona DEFAULT 0
     * 
     * id_persona_pst DEFAULT 0
     * 
     * id_programa DEFAULT 0
     * 
     * id_perfil
     * 
     * cajero did_usuario
     * 
     * remitente did_usuario
     * 
     * id_tipo_descuento DEFAULT 0
     * 
     * ult_usuario did_usuario
     * 
     * id_tipo_cuenta DEFAULT 0
     * 
     * id_estudiante 
     * 
     * id_contrato
     * 
     * _id_estado dtexto2
     * 
     * id_trn_cliente_arancel???
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_tipo_moneda", referencedColumnName = "id_tipo_moneda")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgTrnTiposMonedas tipoMoneda;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long idTransaccion;

    private String nrorRecibo;

    private Integer cantidad = 1;//DEFAULT 1

    //total dmoneda2 <- Observar

    //pagado dmoneda2 <- Observar

    //descuendo dmoneda2 <- Observar

    //efectivo dmoneda2 <- Observar

    //deposito dmoneda2 <- Observar

    //fec_pago dfec_modificacion    

    //private String nro_resolucion;

    private String nroFPago;

    private String observacion = "Ninguna";//DEFAULT

    private String ultIp;

    private Integer mpst;

    public PgTrnTiposMonedas getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(PgTrnTiposMonedas tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getNrorRecibo() {
        return nrorRecibo;
    }

    public void setNrorRecibo(String nrorRecibo) {
        this.nrorRecibo = nrorRecibo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNroFPago() {
        return nroFPago;
    }

    public void setNroFPago(String nroFPago) {
        this.nroFPago = nroFPago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUltIp() {
        return ultIp;
    }

    public void setUltIp(String ultIp) {
        this.ultIp = ultIp;
    }

    public Integer getMpst() {
        return mpst;
    }

    public void setMpst(Integer mpst) {
        this.mpst = mpst;
    }

     
}