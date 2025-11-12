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
@Table(name = "scs_modalidades")
public class ScsModalidades extends SigaUsicRevisiones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_modalidad")
    private Long idScsModalidad;
    
    @NotNull
    @Column(name = "scs_modalidad")
    private String scsModalidad;

    public Long getIdScsModalidad() {
        return idScsModalidad;
    }

    public void setIdScsModalidad(Long idScsModalidad) {
        this.idScsModalidad = idScsModalidad;
    }

    public String getScsModalidad() {
        return scsModalidad;
    }

    public void setScsModalidad(String scsModalidad) {
        this.scsModalidad = scsModalidad;
    }
  
    
}
