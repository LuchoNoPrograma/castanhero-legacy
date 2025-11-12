package uap.usic.siga.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "poais_requisitos_formaciones")
public class PoaisRequisitosFormaciones extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_requisito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisRequisitos  poaisRequisitos;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_requisito_formacion")
    private Long idRequisitoFormacion;
	
	@Column(name = "requisito_formacion")
	private String requisitoFormacion;

	public PoaisRequisitos getPoaisRequisitos() {
		return poaisRequisitos;
	}

	public void setPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
		this.poaisRequisitos = poaisRequisitos;
	}

	public Long getIdRequisitoFormacion() {
		return idRequisitoFormacion;
	}

	public void setIdRequisitoFormacion(Long idRequisitoFormacion) {
		this.idRequisitoFormacion = idRequisitoFormacion;
	}

	public String getRequisitoFormacion() {
		return requisitoFormacion;
	}

	public void setRequisitoFormacion(String requisitoFormacion) {
		this.requisitoFormacion = requisitoFormacion;
	}
	
	
}
