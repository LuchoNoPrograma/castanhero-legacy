package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad que representa estados civiles de personas.
 */
@Entity
@Table(name = "prs_estados_civiles")
public class PrsEstadoCivil extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prs_estado_civil")
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "prs_estado_civil", nullable = false, length = 50)
    private String estadoCivil;

    public PrsEstadoCivil() {
    }

    public PrsEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}
