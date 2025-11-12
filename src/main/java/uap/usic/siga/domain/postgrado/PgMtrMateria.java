package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_mtr_materias")
public class PgMtrMateria extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long idMateria;

    @Column(name = "materia")
    private String materia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_materia", referencedColumnName = "id_tipo_materia")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgMtrTipoMateria tipoMateria;

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public PgMtrTipoMateria getTipoMateria() {
        return tipoMateria;
    }

    public void setTipoMateria(PgMtrTipoMateria tipoMateria) {
        this.tipoMateria = tipoMateria;
    }
}
