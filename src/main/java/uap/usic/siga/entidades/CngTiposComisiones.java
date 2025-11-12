package uap.usic.siga.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
*
* @author Freddy Morales
*/
@Entity
@Table(name = "cng_tipos_comisiones")
public class CngTiposComisiones extends SigaUsicRevisiones{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_comision")
    private Long idTipoComision;

    @NotNull
	@Column(name = "tipo_comision")
    private String tipoComision;
    
    @Column(name = "codigo")
    private String codigo;

	public Long getIdTipoComision() {
		return idTipoComision;
	}

	public void setIdTipoComision(Long idTipoComision) {
		this.idTipoComision = idTipoComision;
	}

	public String getTipoComision() {
		return tipoComision;
	}

	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

    
}
