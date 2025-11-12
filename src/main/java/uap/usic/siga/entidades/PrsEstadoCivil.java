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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
