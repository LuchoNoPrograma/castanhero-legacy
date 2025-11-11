package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oo_encuentros")
public class OoEncuentros extends SigaUsicRevisiones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_encuentro")
	private Long idEncuentro;
	
	@Column(name = "encuentro")
	private String encuentro;
	
	@Column(name = "descripcion")
	private String descripcion;

	public Long getIdEncuentro() {
		return idEncuentro;
	}

	public void setIdEncuentro(Long idEncuentro) {
		this.idEncuentro = idEncuentro;
	}

	public String getEncuentro() {
		return encuentro;
	}

	public void setEncuentro(String encuentro) {
		this.encuentro = encuentro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
