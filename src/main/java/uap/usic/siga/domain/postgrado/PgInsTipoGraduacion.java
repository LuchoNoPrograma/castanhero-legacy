package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_ins_tipos_graduaciones")
public class PgInsTipoGraduacion extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_graduacion")
    private Long idTipoGraduacion;

    @Column(name = "tipo_graduacion")
    private String tipoGraduacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_institucion", referencedColumnName = "id_institucion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgInstitucion institucion;

    public Long getIdTipoGraduacion() {
        return idTipoGraduacion;
    }

    public void setIdTipoGraduacion(Long idTipoGraduacion) {
        this.idTipoGraduacion = idTipoGraduacion;
    }

    public String getTipoGraduacion() {
        return tipoGraduacion;
    }

    public void setTipoGraduacion(String tipoGraduacion) {
        this.tipoGraduacion = tipoGraduacion;
    }

    public PgInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(PgInstitucion institucion) {
        this.institucion = institucion;
    }
}
