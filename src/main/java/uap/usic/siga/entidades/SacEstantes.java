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
 * @author Yesseniua
 */
@Entity
@Table(name = "sac_estantes")
public class SacEstantes extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_estante")
    private Long idSacEstante;

    @NotNull
    @Column(name = "sac_nombre_estante")
    private String sacNombreEstante;

    @Column(name = "sac_codigo_estante")
    private String sacCodigoEstante;

    @Column(name = "sac_numero_estante")
    private Integer sacNumeroEstante;

    public Long getIdSacEstante() {
        return idSacEstante;
    }

    public void setIdSacEstante(Long idSacEstante) {
        this.idSacEstante = idSacEstante;
    }

    public String getSacNombreEstante() {
        return sacNombreEstante;
    }

    public void setSacNombreEstante(String sacNombreEstante) {
        this.sacNombreEstante = sacNombreEstante;
    }

    public String getSacCodigoEstante() {
        return sacCodigoEstante;
    }

    public void setSacCodigoEstante(String sacCodigoEstante) {
        this.sacCodigoEstante = sacCodigoEstante;
    }

    public Integer getSacNumeroEstante() {
        return sacNumeroEstante;
    }

    public void setSacNumeroEstante(Integer sacNumeroEstante) {
        this.sacNumeroEstante = sacNumeroEstante;
    }

}
