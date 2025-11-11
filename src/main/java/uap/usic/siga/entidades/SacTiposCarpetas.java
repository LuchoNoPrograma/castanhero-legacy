package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 *
 * @author Yessenia
 */
@Entity
@Table(name = "sac_tipos_carpetas")
public class SacTiposCarpetas extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_tipo_carpeta")
    private Long idSacTipoCarpeta;
    
    @NotNull
    @Column(name = "sac_tipo_carpeta")
    private String sacTipoCarpeta;

    public Long getIdSacTipoCarpeta() {
        return idSacTipoCarpeta;
    }

    public void setIdSacTipoCarpeta(Long idSacTipoCarpeta) {
        this.idSacTipoCarpeta = idSacTipoCarpeta;
    }

    public String getSacTipoCarpeta() {
        return sacTipoCarpeta;
    }

    public void setSacTipoCarpeta(String sacTipoCarpeta) {
        this.sacTipoCarpeta = sacTipoCarpeta;
    }

}
