package uap.usic.siga.entidadesPg;
/**
 * 
 * @author Luis Morales V.
 */

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
