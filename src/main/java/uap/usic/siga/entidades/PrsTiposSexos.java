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
 * Rectorado - USIC 
 * Fecha: 2019-05-28
 * @author Freddy Morales
 */
@Entity
@Table(name = "prs_tipos_sexos")
public class PrsTiposSexos extends SigaUsicRevisiones{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prs_tipo_sexo")
    private Long idPrsTipoSexo;
    
    @Size(max = 50)
    @NotNull
    @Column(name = "prs_tipo_sexo")
    private String prsTipoSexo;


    public PrsTiposSexos() {
    }

    public PrsTiposSexos(@Size(max = 50) @NotNull String prsTipoSexo) {
        this.prsTipoSexo = prsTipoSexo;
    }

    public Long getIdPrsTipoSexo() {
        return idPrsTipoSexo;
    }

    public void setIdPrsTipoSexo(Long idPrsTipoSexo) {
        this.idPrsTipoSexo = idPrsTipoSexo;
    }

    public String getPrsTipoSexo() {
        return prsTipoSexo;
    }

    public void setPrsTipoSexo(String prsTipoSexo) {
        this.prsTipoSexo = prsTipoSexo;
    }

    
}
