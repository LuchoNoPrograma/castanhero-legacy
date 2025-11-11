package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_convalidaciones")
public class TipoConvalidacion extends SigaUsicRevisiones{
    /*==================RELACIONES====================
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_convalidacion")
    private Long idTipoConvalidacion;

    @Column(name = "tipo_convalidacion")
    private String tipoConvalidacion;

    public Long getIdTipoConvalidacion() {
        return idTipoConvalidacion;
    }

    public void setIdTipoConvalidacion(Long idTipoConvalidacion) {
        this.idTipoConvalidacion = idTipoConvalidacion;
    }

    public String getTipoConvalidacion() {
        return tipoConvalidacion;
    }

    public void setTipoConvalidacion(String tipoConvalidacion) {
        this.tipoConvalidacion = tipoConvalidacion;
    }

    
}
