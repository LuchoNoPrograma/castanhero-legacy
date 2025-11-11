package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "pg_ins_tipos_estudiantes")
public class PgTipoEstudiante extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_estudiante")
    private Long idTipoEstudiante;

    @Column(name = "tipo_estudiante")
    private String tipoEstudiante;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fk_id_institucion", referencedColumnName = "id_institucion")
    @JsonIgnore
    private PgInstitucion institucion;

    public Long getIdTipoEstudiante() {
        return idTipoEstudiante;
    }

    public void setIdTipoEstudiante(Long idTipoEstudiante) {
        this.idTipoEstudiante = idTipoEstudiante;
    }

    public String getTipoEstudiante() {
        return tipoEstudiante;
    }

    public void setTipoEstudiante(String tipoEstudiante) {
        this.tipoEstudiante = tipoEstudiante;
    }

    public PgInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(PgInstitucion institucion) {
        this.institucion = institucion;
    }
}
