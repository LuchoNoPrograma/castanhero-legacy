package uap.usic.siga.domain.personal;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pnl_tipos_administrativos")
public class PnlTiposAdministrativos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_tipo_administrativo")
    private Long idPnlTipoAdministrativo;

    @Column(name = "tipo_administrativo")
    private String tipoAdministrativo;

    public Long getIdPnlTipoAdministrativo() {
        return idPnlTipoAdministrativo;
    }

    public void setIdPnlTipoAdministrativo(Long idPnlTipoAdministrativo) {
        this.idPnlTipoAdministrativo = idPnlTipoAdministrativo;
    }

    public String getTipoAdministrativo() {
        return tipoAdministrativo;
    }

    public void setTipoAdministrativo(String tipoAdministrativo) {
        this.tipoAdministrativo = tipoAdministrativo;
    }
}
