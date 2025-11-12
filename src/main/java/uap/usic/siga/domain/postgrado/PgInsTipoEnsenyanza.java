package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_ins_tipos_ensenyanzas")
public class PgInsTipoEnsenyanza extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_ensenyanza")
    private Long idTipoEnsenyanza;

    @Column(name = "tipo_ensenyanza")
    private String tipoEnsenyanza;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_institucion", referencedColumnName = "id_institucion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgInstitucion institucion;

    public Long getIdTipoEnsenyanza() {
        return idTipoEnsenyanza;
    }

    public void setIdTipoEnsenyanza(Long idTipoEnsenyanza) {
        this.idTipoEnsenyanza = idTipoEnsenyanza;
    }

    public String getTipoEnsenyanza() {
        return tipoEnsenyanza;
    }

    public void setTipoEnsenyanza(String tipoEnsenyanza) {
        this.tipoEnsenyanza = tipoEnsenyanza;
    }

    public PgInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(PgInstitucion institucion) {
        this.institucion = institucion;
    }
}
