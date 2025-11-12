package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.compartido.base.EntidadGestionable;

import javax.persistence.*;

/**
 * Entidad que representa ubicaciones orgánicas institucionales.
 */
@Entity
@Table(name = "ins_ubicaciones_organicas")
public class InsUbicacionesOrganicas extends EntidadGestionable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion_organica")
    private Long id;

    @Column(name = "ubicacion_organica")
    private String ubicacionOrganica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsSedes sedes;

    public InsUbicacionesOrganicas() {
    }

    public InsUbicacionesOrganicas(String ubicacionOrganica, InsSedes sedes) {
        this.ubicacionOrganica = ubicacionOrganica;
        this.sedes = sedes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacionOrganica() {
        return ubicacionOrganica;
    }

    public void setUbicacionOrganica(String ubicacionOrganica) {
        this.ubicacionOrganica = ubicacionOrganica;
    }

    public InsSedes getSedes() {
        return sedes;
    }

    public void setSedes(InsSedes sedes) {
        this.sedes = sedes;
    }
}
