package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad que representa direcciones funcionales institucionales.
 * Rectorado - USIC
 * Fecha: 2019-07-30
 */
@Entity
@Table(name = "ins_direcciones_funcionales")
public class InsDireccionesFuncionales extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion_funcional")
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "direccion_funcional", length = 60, nullable = false)
    private String direccionFuncional;

    @Size(max = 10)
    @NotNull
    @Column(name = "sigla", length = 10, nullable = false)
    private String sigla;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sede", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsSedes insSedes;

    public InsDireccionesFuncionales() {
    }

    public InsDireccionesFuncionales(String direccionFuncional, String sigla, InsSedes insSedes) {
        this.direccionFuncional = direccionFuncional;
        this.sigla = sigla;
        this.insSedes = insSedes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccionFuncional() {
        return direccionFuncional;
    }

    public void setDireccionFuncional(String direccionFuncional) {
        this.direccionFuncional = direccionFuncional;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public InsSedes getInsSedes() {
        return insSedes;
    }

    public void setInsSedes(InsSedes insSedes) {
        this.insSedes = insSedes;
    }
}
