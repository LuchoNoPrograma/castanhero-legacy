package uap.usic.siga.domain.gdoc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.sun.istack.NotNull;

import uap.usic.siga.entidades.SigaUsicGestiones;

@Entity
@Table(name = "gdoc_consejos")
public class GdocConsejos extends SigaUsicGestiones {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gdoc_consejo")
    private Long idGdocConsejo;

	 @NotNull
	 @Column(name = "consejo")
	  private String consejo;

	 @NotNull
	 @Column(name = "sigla")
	  private String sigla = "Ninguno";

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getIdGdocConsejo() {
		return idGdocConsejo;
	}

	public void setIdGdocConsejo(Long idGdocConsejo) {
		this.idGdocConsejo = idGdocConsejo;
	}

	public String getConsejo() {
		return consejo;
	}

	public void setConsejo(String consejo) {
		this.consejo = consejo;
	}
}
