package uap.usic.siga.domain.sicoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.SigaUsicGestiones;
import uap.usic.siga.entidades.Usuarios;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "scs_proyectos")
public class ScsProyecto extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_proyecto")
    private Long idScsProyecto;

    @NotNull
    @Column(name = "scs_proyecto")
    private String scsProyecto;

    @NotNull
    @Column(name = "scs_codigo_proyecto")
    private String scsCodigoProyecto;

    @NotNull
    @Column(name = "scs_nro_proyecto")
    private Integer scsNroProyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_funcional", nullable = false)
    @JsonIgnore
    private InsUnidadesFuncionales unidadFuncional;

    public Long getIdScsProyecto() {
        return idScsProyecto;
    }

    public void setIdScsProyecto(Long idScsProyecto) {
        this.idScsProyecto = idScsProyecto;
    }

    public String getScsProyecto() {
        return scsProyecto;
    }

    public void setScsProyecto(String scsProyecto) {
        this.scsProyecto = scsProyecto;
    }

    public String getScsCodigoProyecto() {
        return scsCodigoProyecto;
    }

    public void setScsCodigoProyecto(String scsCodigoProyecto) {
        this.scsCodigoProyecto = scsCodigoProyecto;
    }

    public Integer getScsNroProyecto() {
        return scsNroProyecto;
    }

    public void setScsNroProyecto(Integer scsNroProyecto) {
        this.scsNroProyecto = scsNroProyecto;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public InsUnidadesFuncionales getUnidadFuncional() {
        return unidadFuncional;
    }

    public void setUnidadFuncional(InsUnidadesFuncionales unidadFuncional) {
        this.unidadFuncional = unidadFuncional;
    }
}
