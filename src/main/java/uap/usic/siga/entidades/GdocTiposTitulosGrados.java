package uap.usic.siga.entidades;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "gdoc_tipos_titulos_grados")
public class GdocTiposTitulosGrados extends SigaUsicRevisiones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_titulo_grado")
	private Long idTipoTituloGrado;

	@Column(name = "tipo_titulo_grado")
	private String tipoTituloGrado;

	

	public Long getIdTipoTituloGrado() {
		return idTipoTituloGrado;
	}

	public void setIdTipoTituloGrado(Long idTipoTituloGrado) {
		this.idTipoTituloGrado = idTipoTituloGrado;
	}

	public String getTipoTituloGrado() {
		return tipoTituloGrado;
	}

	public void setTipoTituloGrado(String tipoTituloGrado) {
		this.tipoTituloGrado = tipoTituloGrado;
	}

	
	

}
