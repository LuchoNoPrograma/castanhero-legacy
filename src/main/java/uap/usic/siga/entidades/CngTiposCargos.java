package uap.usic.siga.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
*
* @author Freddy Morales
*/
@Entity
@Table(name = "cng_tipos_cargos")
public class CngTiposCargos extends SigaUsicRevisiones{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_cargo")
    private Long idTipoCargo;

    @Column(name = "cargo")
    private String cargo;
    
    @Column(name = "codigo")
    private String codigo;

	public Long getIdTipoCargo() {
		return idTipoCargo;
	}

	public void setIdTipoCargo(Long idTipoCargo) {
		this.idTipoCargo = idTipoCargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

    
}
