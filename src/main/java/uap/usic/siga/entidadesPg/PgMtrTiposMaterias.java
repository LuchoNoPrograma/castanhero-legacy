package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "pg_mtr_tipos_materias")
public class PgMtrTiposMaterias extends SigaUsicRevisiones{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_materia")
    private Long idtipomateria;

    private String tipomateria;

    public Long getIdtipomateria() {
        return idtipomateria;
    }

    public void setIdtipomateria(Long idtipomateria) {
        this.idtipomateria = idtipomateria;
    }

    public String getTipomateria() {
        return tipomateria;
    }

    public void setTipomateria(String tipomateria) {
        this.tipomateria = tipomateria;
    }


}
