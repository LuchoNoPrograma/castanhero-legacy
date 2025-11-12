package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_adm_tipos_admisiones")
public class PgAdmTipoAdmision extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_admision")
    private Long idTipoAdmision;

    @Column(name = "tipo_admision")
    private String tipoAdmision;

    @Column(name = "valor_descuento")
    private Integer valorDescuento;

    @Column(name = "detalles")
    private String detalles;

    public PgAdmTipoAdmision() {
    }

    public PgAdmTipoAdmision(String tipoAdmision, Integer valorDescuento) {
        this.tipoAdmision = tipoAdmision;
        this.valorDescuento = valorDescuento;
    }

    public Long getIdTipoAdmision() {
        return idTipoAdmision;
    }

    public void setIdTipoAdmision(Long idTipoAdmision) {
        this.idTipoAdmision = idTipoAdmision;
    }

    public String getTipoAdmision() {
        return tipoAdmision;
    }

    public void setTipoAdmision(String tipoAdmision) {
        this.tipoAdmision = tipoAdmision;
    }

    public Integer getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(Integer valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public boolean tieneDescuento() {
        return valorDescuento != null && valorDescuento > 0;
    }
}
