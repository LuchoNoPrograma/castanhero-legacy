package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pg_trn_tipos_perfiles")
public class PgTrnTiposPerfiles {
    /*===============RELACIONES 
     * 
     * ult_usuario did_usuario
     * 
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_perfil")
    private Long idTipoPerfil;

    @Column(name = "tipo_perfil")
    private String tipoPerfil;

    public Long getIdTipoPerfil() {
        return idTipoPerfil;
    }

    public void setIdTipoPerfil(Long idTipoPerfil) {
        this.idTipoPerfil = idTipoPerfil;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    
}
