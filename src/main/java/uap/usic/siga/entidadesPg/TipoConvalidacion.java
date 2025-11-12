package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
