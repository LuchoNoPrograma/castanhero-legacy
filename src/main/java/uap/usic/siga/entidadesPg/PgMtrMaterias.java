package uap.usic.siga.entidadesPg;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pg_mtr_materias")
public class PgMtrMaterias extends SigaUsicRevisiones{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_materia", referencedColumnName = "id_tipo_materia")
    private PgMtrTiposMaterias tipomateria;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long idMateria;

    private String materia;

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

    public PgMtrTiposMaterias getTipomateria() {
        return tipomateria;
    }

    public void setTipomateria(PgMtrTiposMaterias tipomateria) {
        this.tipomateria = tipomateria;
    }
}
