package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "ins_ubicaciones_organicas")
public class InsUbicacionesOrganicas extends SigaUsicGestiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsSedes sedes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion_organica")
    private Long idUbicacionOrganica;

    @Column(name = "ubicacion_organica")
    private String ubicacionOrganica;

    public InsSedes getSedes() {
        return sedes;
    }

    public void setSedes(InsSedes sedes) {
        this.sedes = sedes;
    }

    public Long getIdUbicacionOrganica() {
        return idUbicacionOrganica;
    }

    public void setIdUbicacionOrganica(Long idUbicacionOrganica) {
        this.idUbicacionOrganica = idUbicacionOrganica;
    }

    public String getUbicacionOrganica() {
        return ubicacionOrganica;
    }

    public void setUbicacionOrganica(String ubicacionOrganica) {
        this.ubicacionOrganica = ubicacionOrganica;
    }

}
