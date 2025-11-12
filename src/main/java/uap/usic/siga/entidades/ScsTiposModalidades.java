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
@Table(name = "scs_tipos_modalidades")
public class ScsTiposModalidades extends SigaUsicRevisiones{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_tipo_modalidad")
    private Long idScsTipoModalidad;
    
    @NotNull
    @Column(name = "scs_tipo_modalidad")
    private String scsTipoModalidad;

    public Long getIdScsTipoModalidad() {
        return idScsTipoModalidad;
    }

    public void setIdScsTipoModalidad(Long idScsTipoModalidad) {
        this.idScsTipoModalidad = idScsTipoModalidad;
    }

    public String getScsTipoModalidad() {
        return scsTipoModalidad;
    }

    public void setScsTipoModalidad(String scsTipoModalidad) {
        this.scsTipoModalidad = scsTipoModalidad;
    }
  
    
}
