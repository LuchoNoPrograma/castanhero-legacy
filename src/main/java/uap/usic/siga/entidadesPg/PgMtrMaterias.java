package uap.usic.siga.entidadesPg;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
