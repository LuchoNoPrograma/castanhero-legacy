package uap.usic.siga.entidadesPg;
/**
 * 
 * @author Luis Morales V.
 */

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pg_prs_tipos_sexos")
public class PgPrsTiposSexos extends SigaUsicRevisiones{
    /*==================RELACIONES====================
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_sexo")
    private Long idTipoSexo;

    @Column(name = "tipo_sexo")
    private String tipoSexo;

    public Long getIdTipoSexo() {
        return idTipoSexo;
    }

    public void setIdTipoSexo(Long idTipoSexo) {
        this.idTipoSexo = idTipoSexo;
    }

    public String getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(String tipoSexo) {
        this.tipoSexo = tipoSexo;
    }

    
}
