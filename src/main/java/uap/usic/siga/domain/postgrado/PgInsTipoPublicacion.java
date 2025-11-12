package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_ins_tipos_publicaciones")
public class PgInsTipoPublicacion extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_publicacion")
    private Long idTipoPublicacion;

    @Column(name = "tipo_publicacion")
    private String tipoPublicacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_institucion", referencedColumnName = "id_institucion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgInstitucion institucion;

    public Long getIdTipoPublicacion() {
        return idTipoPublicacion;
    }

    public void setIdTipoPublicacion(Long idTipoPublicacion) {
        this.idTipoPublicacion = idTipoPublicacion;
    }

    public String getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(String tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public PgInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(PgInstitucion institucion) {
        this.institucion = institucion;
    }
}
