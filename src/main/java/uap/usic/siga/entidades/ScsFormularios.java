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
@Table(name = "scs_formularios")
public class ScsFormularios extends SigaUsicRevisiones{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_formulario")
    private Long idScsFormulario;
    
    @NotNull
    @Column(name = "scs_formulario")
    private String scsFormulario;
 
    @NotNull
    @Column(name = "scs_codigo_formulario")
    private String scsCodigoFormulario;

    public Long getIdScsFormulario() {
        return idScsFormulario;
    }

    public void setIdScsFormulario(Long idScsFormulario) {
        this.idScsFormulario = idScsFormulario;
    }

    public String getScsFormulario() {
        return scsFormulario;
    }

    public void setScsFormulario(String scsFormulario) {
        this.scsFormulario = scsFormulario;
    }

    public String getScsCodigoFormulario() {
        return scsCodigoFormulario;
    }

    public void setScsCodigoFormulario(String scsCodigoFormulario) {
        this.scsCodigoFormulario = scsCodigoFormulario;
    }
  
    
}
