package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pg_trn_perfiles_conceptos")
public class PgTrnPerfilConcepto extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_concepto")
    private Long idPerfilConcepto;

    @Column(name = "costo")
    private BigDecimal costo;

    @Column(name = "descuento")
    private Boolean descuento;

    @Column(name = "cantidad")
    private Float cantidad;

    public Long getIdPerfilConcepto() {
        return idPerfilConcepto;
    }

    public void setIdPerfilConcepto(Long idPerfilConcepto) {
        this.idPerfilConcepto = idPerfilConcepto;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Boolean getDescuento() {
        return descuento;
    }

    public void setDescuento(Boolean descuento) {
        this.descuento = descuento;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }
}
