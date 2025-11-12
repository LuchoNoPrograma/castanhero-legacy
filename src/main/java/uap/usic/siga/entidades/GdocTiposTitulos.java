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

import com.sun.istack.NotNull;

@Entity
@Table(name = "gdoc_tipos_titulos")
public class GdocTiposTitulos extends SigaUsicRevisiones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gdoc_tipo_titulo")
	private Long idGdocTipoTitulo;

	@NotNull
	@Column(name = "gdoc_tipo_titulo")
	private String gdocTipoTitulo;

	@NotNull
	@Column(name = "gdoc_sigla")
	private String gdocSigla;

	

	public Long getIdGdocTipoTitulo() {
		return idGdocTipoTitulo;
	}

	public void setIdGdocTipoTitulo(Long idGdocTipoTitulo) {
		this.idGdocTipoTitulo = idGdocTipoTitulo;
	}

	public String getGdocTipoTitulo() {
		return gdocTipoTitulo;
	}

	public void setGdocTipoTitulo(String gdocTipoTitulo) {
		this.gdocTipoTitulo = gdocTipoTitulo;
	}

	public String getGdocSigla() {
		return gdocSigla;
	}

	public void setGdocSigla(String gdocSigla) {
		this.gdocSigla = gdocSigla;
	}

	

}
