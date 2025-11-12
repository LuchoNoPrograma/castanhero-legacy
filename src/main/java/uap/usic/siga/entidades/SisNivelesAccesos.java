package uap.usic.siga.entidades;

import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "sis_niveles_accesos")
public class SisNivelesAccesos extends SigaUsicRevisiones{
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_acceso")   
    private Integer idNivelAcceso;
    
    
    @Column(name = "nivel_acceso")
    private String nivelAcceso;

    public SisNivelesAccesos(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public SisNivelesAccesos() {
    }

    public Integer getIdNivelAcceso() {
        return idNivelAcceso;
    }

    public void setIdNivelAcceso(Integer idNivelAcceso) {
        this.idNivelAcceso = idNivelAcceso;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

   
}
