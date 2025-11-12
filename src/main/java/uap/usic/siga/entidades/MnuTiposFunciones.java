package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "mnu_tipos_funciones")
public class MnuTiposFunciones extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_tipo_funcion")
    private Long idMnuTipoFuncion;

    @NotNull
    @Column(name = "tipo_funcion")
    private String tipoFuncion;

     @Size(max = 20)
    @Column(name = "sigla")
    private String sigla;
     
    public MnuTiposFunciones() {
    }

    public MnuTiposFunciones(@NotNull String tipoFuncion, @Size(max = 20) String sigla) {
        this.tipoFuncion = tipoFuncion;
        this.sigla = sigla;
    }

    public Long getIdMnuTipoFuncion() {
        return idMnuTipoFuncion;
    }

    public void setIdMnuTipoFuncion(Long idMnuTipoFuncion) {
        this.idMnuTipoFuncion = idMnuTipoFuncion;
    }

    public String getTipoFuncion() {
        return tipoFuncion;
    }

    public void setTipoFuncion(String tipoFuncion) {
        this.tipoFuncion = tipoFuncion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
   
}
