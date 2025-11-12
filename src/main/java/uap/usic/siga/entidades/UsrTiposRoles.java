package uap.usic.siga.entidades;

import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "usr_tipos_roles")
public class UsrTiposRoles {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_rol")   
    private Integer idUsrTipoRol;
  
    @Column(name = "tipo_rol")
    private String tipoRol;

    public Integer getIdUsrTipoRol() {
        return idUsrTipoRol;
    }

    public void setIdUsrTipoRol(Integer idUsrTipoRol) {
        this.idUsrTipoRol = idUsrTipoRol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
  
    
}
