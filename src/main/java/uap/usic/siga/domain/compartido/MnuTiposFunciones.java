package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa tipos de funciones de menú.
 */
@Entity
@Table(name = "mnu_tipos_funciones")
public class MnuTiposFunciones extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_tipo_funcion")
    private Long id;

    @NotNull
    @Column(name = "tipo_funcion", nullable = false)
    private String tipoFuncion;

    @Size(max = 20)
    @Column(name = "sigla", length = 20)
    private String sigla;

    public MnuTiposFunciones() {
    }

    public MnuTiposFunciones(String tipoFuncion, String sigla) {
        this.tipoFuncion = tipoFuncion;
        this.sigla = sigla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoFuncion() {
        return tipoFuncion;
    }

    public void setTipoFuncion(String tipoFuncion) {
        this.tipoFuncion = tipoFuncion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
