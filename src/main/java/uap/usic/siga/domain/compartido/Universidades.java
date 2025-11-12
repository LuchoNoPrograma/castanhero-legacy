package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

/**
 * Entidad que representa universidades asociadas a instituciones.
 */
@Entity
@Table(name = "universidades")
public class Universidades extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_universidad")
    private Long id;

    @Column(name = "universidad")
    private String universidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_institucion", referencedColumnName = "id_institucion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Instituciones instituciones;

    public Universidades() {
    }

    public Universidades(String universidad, Instituciones instituciones) {
        this.universidad = universidad;
        this.instituciones = instituciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public Instituciones getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(Instituciones instituciones) {
        this.instituciones = instituciones;
    }
}
