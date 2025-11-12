package uap.usic.siga.domain.sicoes;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "scs_tipos_contratos")
public class ScsTipoContrato extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_tipo_contrato")
    private Long idScsTipoContrato;

    @NotNull
    @Column(name = "scs_tipo_contrato")
    private String scsTipoContrato;

    public Long getIdScsTipoContrato() {
        return idScsTipoContrato;
    }

    public void setIdScsTipoContrato(Long idScsTipoContrato) {
        this.idScsTipoContrato = idScsTipoContrato;
    }

    public String getScsTipoContrato() {
        return scsTipoContrato;
    }

    public void setScsTipoContrato(String scsTipoContrato) {
        this.scsTipoContrato = scsTipoContrato;
    }
}
