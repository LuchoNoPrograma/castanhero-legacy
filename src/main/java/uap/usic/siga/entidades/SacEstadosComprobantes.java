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
@Table(name = "sac_estados_comprobantes")
public class SacEstadosComprobantes extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_estado_comprobante")
    private Long idSacEstadoComprobante;

    @NotNull
    @Column(name = "sac_nombre_estado")
    private String sacNombreEstado;

    public Long getIdSacEstadoComprobante() {
        return idSacEstadoComprobante;
    }

    public void setIdSacEstadoComprobante(Long idSacEstadoComprobante) {
        this.idSacEstadoComprobante = idSacEstadoComprobante;
    }

    public String getSacNombreEstado() {
        return sacNombreEstado;
    }

    public void setSacNombreEstado(String sacNombreEstado) {
        this.sacNombreEstado = sacNombreEstado;
    }

}
