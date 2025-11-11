package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

@Entity
@Table(name = "pg_prs_tipos_sexos")
public class PgPrsTipoSexo extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_sexo")
    private Long idTipoSexo;

    @Column(name = "tipo_sexo")
    private String tipoSexo;

    public Long getIdTipoSexo() {
        return idTipoSexo;
    }

    public void setIdTipoSexo(Long idTipoSexo) {
        this.idTipoSexo = idTipoSexo;
    }

    public String getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(String tipoSexo) {
        this.tipoSexo = tipoSexo;
    }
}
