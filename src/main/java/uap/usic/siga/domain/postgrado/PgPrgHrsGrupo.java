package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadConGestion;

import javax.persistence.*;

@Entity
@Table(name = "pg_prg_hrs_grupos")
public class PgPrgHrsGrupo extends EntidadConGestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hrs_grupo")
    private Long idHrsGrupo;

    public Long getIdHrsGrupo() {
        return idHrsGrupo;
    }

    public void setIdHrsGrupo(Long idHrsGrupo) {
        this.idHrsGrupo = idHrsGrupo;
    }
}
