package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadGestionable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa una institución educativa.
 */
@Entity
@Table(name = "institucion")
public class Institucion extends EntidadGestionable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(nullable = false, length = 100)
    private String institucion;

    @NotNull
    @Column(nullable = false)
    private String sigla;

    public Institucion() {
    }

    public Institucion(String institucion, String sigla) {
        this.institucion = institucion;
        this.sigla = sigla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
