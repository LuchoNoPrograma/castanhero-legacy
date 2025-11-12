package uap.usic.siga.domain.personal;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
