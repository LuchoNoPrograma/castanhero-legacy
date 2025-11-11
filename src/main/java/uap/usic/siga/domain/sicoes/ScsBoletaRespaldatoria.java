package uap.usic.siga.domain.sicoes;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "scs_boletas_respaldatorias")
public class ScsBoletaRespaldatoria extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_boleta_respaldatoria")
    private Long idScsBoletaRespaldatoria;

    @NotNull
    @Column(name = "scs_boleta_respaldatorioa")
    private String scsBoletaRespaldatoria;

    @NotNull
    @Column(name = "scs_codigo_boleta_respaldatoria")
    private String scsCodigoBoletaRespaldatoria;

    public Long getIdScsBoletaRespaldatoria() {
        return idScsBoletaRespaldatoria;
    }

    public void setIdScsBoletaRespaldatoria(Long idScsBoletaRespaldatoria) {
        this.idScsBoletaRespaldatoria = idScsBoletaRespaldatoria;
    }

    public String getScsBoletaRespaldatoria() {
        return scsBoletaRespaldatoria;
    }

    public void setScsBoletaRespaldatoria(String scsBoletaRespaldatoria) {
        this.scsBoletaRespaldatoria = scsBoletaRespaldatoria;
    }

    public String getScsCodigoBoletaRespaldatoria() {
        return scsCodigoBoletaRespaldatoria;
    }

    public void setScsCodigoBoletaRespaldatoria(String scsCodigoBoletaRespaldatoria) {
        this.scsCodigoBoletaRespaldatoria = scsCodigoBoletaRespaldatoria;
    }
}
