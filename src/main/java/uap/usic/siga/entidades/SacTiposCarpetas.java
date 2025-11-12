package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


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
