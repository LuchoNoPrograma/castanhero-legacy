package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad que representa unidades funcionales institucionales.
 * Rectorado - USIC
 * Fecha: 2019-03-26
 */
@Entity
@Table(name = "ins_unidades_funcionales")
public class InsUnidadesFuncionales extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad_funcional")
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "unidad_funcional", length = 60, nullable = false)
    private String unidadFuncional;

    @Size(max = 10)
    @NotNull
    @Column(name = "sigla", length = 10, nullable = false)
    private String sigla;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_direccion_funcional", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsDireccionesFuncionales insDireccionesFuncionales;

    public InsUnidadesFuncionales() {
    }

    public InsUnidadesFuncionales(String unidadFuncional, String sigla, InsDireccionesFuncionales insDireccionesFuncionales) {
        this.unidadFuncional = unidadFuncional;
        this.sigla = sigla;
        this.insDireccionesFuncionales = insDireccionesFuncionales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnidadFuncional() {
        return unidadFuncional;
    }

    public void setUnidadFuncional(String unidadFuncional) {
        this.unidadFuncional = unidadFuncional;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public InsDireccionesFuncionales getInsDireccionesFuncionales() {
        return insDireccionesFuncionales;
    }

    public void setInsDireccionesFuncionales(InsDireccionesFuncionales insDireccionesFuncionales) {
        this.insDireccionesFuncionales = insDireccionesFuncionales;
    }
}
