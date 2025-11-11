package uap.usic.siga.domain.congreso;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cng_tipos_comisiones")
public class CngTiposComisiones extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_comision")
    private Long idTipoComision;

    @NotNull
    @Column(name = "tipo_comision", nullable = false)
    private String tipoComision;

    @Column(name = "codigo")
    private String codigo;

    public Long getIdTipoComision() {
        return idTipoComision;
    }

    public void setIdTipoComision(Long idTipoComision) {
        this.idTipoComision = idTipoComision;
    }

    public String getTipoComision() {
        return tipoComision;
    }

    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
