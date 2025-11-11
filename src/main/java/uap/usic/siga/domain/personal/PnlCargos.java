package uap.usic.siga.domain.personal;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pnl_cargos")
public class PnlCargos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_cargos")
    private Long idPnlCargos;

    @Size(max = 100)
    @NotNull
    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;

    @Column(name = "objetivo_cargo", length = 10485760)
    private String objetivoCargo;

    public Long getIdPnlCargos() {
        return idPnlCargos;
    }

    public void setIdPnlCargos(Long idPnlCargos) {
        this.idPnlCargos = idPnlCargos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getObjetivoCargo() {
        return objetivoCargo;
    }

    public void setObjetivoCargo(String objetivoCargo) {
        this.objetivoCargo = objetivoCargo;
    }
}
