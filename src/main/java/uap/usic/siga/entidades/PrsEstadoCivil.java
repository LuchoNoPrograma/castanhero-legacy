package uap.usic.siga.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import uap.usic.siga.entidadesPg.Postulantes;

@Entity
@Table(name = "prs_estados_civiles")
public class PrsEstadoCivil extends SigaUsicRevisiones {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prs_estado_civil")
	private Long idPrsEstadoCivil;

	@Size(max = 50)
	@NotNull
	@Column(name = "prs_estado_civil")
	private String estadoCivil;

	public PrsEstadoCivil(@Size(max = 50) @NotNull String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public PrsEstadoCivil() {
	}

	public Long getIdPrsEstadoCivil() {
		return idPrsEstadoCivil;
	}

	public void setIdPrsEstadoCivil(Long idPrsEstadoCivil) {
		this.idPrsEstadoCivil = idPrsEstadoCivil;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
}
