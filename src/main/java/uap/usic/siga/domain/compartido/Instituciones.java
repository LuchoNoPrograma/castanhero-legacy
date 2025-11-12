package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa instituciones.
 */
@Entity
@Table(name = "instituciones")
public class Instituciones extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(nullable = false, length = 100)
    private String institucion;

    @Size(max = 20)
    @NotNull
    @Column(nullable = false, length = 20)
    private String sigla;

    public Instituciones() {
    }

    public Instituciones(String institucion, String sigla) {
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
