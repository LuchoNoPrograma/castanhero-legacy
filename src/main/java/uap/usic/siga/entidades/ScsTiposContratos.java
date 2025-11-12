package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


/**
 * Rectorado - USIC
 * Fecha: 2019-12-26
 * @author Freddy Morales
 */
@Entity
@Table(name = "scs_tipos_contratos")
public class ScsTiposContratos extends SigaUsicRevisiones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_tipo_contrato")
    private Long idScsTipoContrato;
    
    @NotNull
    @Column(name = "scs_tipo_contrato")
    private String scsTipoContrato;

    public Long getIdScsTipoContrato() {
        return idScsTipoContrato;
    }

    public void setIdScsTipoContrato(Long idScsTipoContrato) {
        this.idScsTipoContrato = idScsTipoContrato;
    }

    public String getScsTipoContrato() {
        return scsTipoContrato;
    }

    public void setScsTipoContrato(String scsTipoContrato) {
        this.scsTipoContrato = scsTipoContrato;
    }
   
    
    
}
