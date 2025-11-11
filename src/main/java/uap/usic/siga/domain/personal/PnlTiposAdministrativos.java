package uap.usic.siga.domain.personal;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
