package uap.usic.siga.domain.gdoc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.sun.istack.NotNull;

import uap.usic.siga.entidades.SigaUsicRevisiones;

@Entity
@Table(name = "gdoc_tipos_convenios")
public class GdocTiposConvenios extends SigaUsicRevisiones {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gdoc_tipo_convenio")
    private Long idGdocTipoConvenio;

    @NotNull
    @Column(name = "tipo_convenio")
    private String tipoConvenio;

    @NotNull
    @Column(name = "sigla_convenio")
    private String siglaConvenio;

    public Long getIdGdocTipoConvenio() {
		return idGdocTipoConvenio;
	}

	public void setIdGdocTipoConvenio(Long idGdocTipoConvenio) {
		this.idGdocTipoConvenio = idGdocTipoConvenio;
	}

	public String getTipoConvenio() {
		return tipoConvenio;
	}

	public void setTipoConvenio(String tipoConvenio) {
		this.tipoConvenio = tipoConvenio;
	}

	public String getSiglaConvenio() {
		return siglaConvenio;
	}

	public void setSiglaConvenio(String siglaConvenio) {
		this.siglaConvenio = siglaConvenio;
	}
}
