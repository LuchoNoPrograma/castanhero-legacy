package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_prs_tipos_estados_civiles")
public class PgPrsEstadoCivil extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_estado_civil")
    private Long idTipoEstadoCivil;

    @Column(name = "tipo_estado_civil")
    private String tipoEstadoCivil;

    public Long getIdTipoEstadoCivil() {
        return idTipoEstadoCivil;
    }

    public void setIdTipoEstadoCivil(Long idTipoEstadoCivil) {
        this.idTipoEstadoCivil = idTipoEstadoCivil;
    }

    public String getTipoEstadoCivil() {
        return tipoEstadoCivil;
    }

    public void setTipoEstadoCivil(String tipoEstadoCivil) {
        this.tipoEstadoCivil = tipoEstadoCivil;
    }
}
