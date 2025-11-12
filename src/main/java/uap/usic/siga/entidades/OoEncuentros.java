package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
