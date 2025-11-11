package uap.usic.siga.domain.sicoes;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "scs_formularios")
public class ScsFormulario extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_formulario")
    private Long idScsFormulario;

    @NotNull
    @Column(name = "scs_formulario")
    private String scsFormulario;

    @NotNull
    @Column(name = "scs_codigo_formulario")
    private String scsCodigoFormulario;

    public Long getIdScsFormulario() {
        return idScsFormulario;
    }

    public void setIdScsFormulario(Long idScsFormulario) {
        this.idScsFormulario = idScsFormulario;
    }

    public String getScsFormulario() {
        return scsFormulario;
    }

    public void setScsFormulario(String scsFormulario) {
        this.scsFormulario = scsFormulario;
    }

    public String getScsCodigoFormulario() {
        return scsCodigoFormulario;
    }

    public void setScsCodigoFormulario(String scsCodigoFormulario) {
        this.scsCodigoFormulario = scsCodigoFormulario;
    }
}
