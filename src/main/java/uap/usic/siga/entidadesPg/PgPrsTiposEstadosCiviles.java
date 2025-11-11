package uap.usic.siga.entidadesPg;
/**
 * 
 * @author Luis Morales V.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pg_prs_tipos_estados_civiles")
public class PgPrsTiposEstadosCiviles extends SigaUsicRevisiones{
    /*====================RELACIONES=================
     * ult_usuario did_usuario
     */
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
