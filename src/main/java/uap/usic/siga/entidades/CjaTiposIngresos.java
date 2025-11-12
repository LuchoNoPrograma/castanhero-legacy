package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "cja_tipos_ingresos")
public class CjaTiposIngresos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_tipo_ingreso")
    private Long idCjaTipoIngreso;

    @Size(max = 100)
    @Column(name = "tipo_ingreso")
    private String tipoIngreso;

    public Long getIdCjaTipoIngreso() {
        return idCjaTipoIngreso;
    }

    public void setIdCjaTipoIngreso(Long idCjaTipoIngreso) {
        this.idCjaTipoIngreso = idCjaTipoIngreso;
    }

    public String getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

}
