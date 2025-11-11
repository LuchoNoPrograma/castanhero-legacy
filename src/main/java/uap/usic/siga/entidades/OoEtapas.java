package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oo_etapas")
public class OoEtapas extends SigaUsicRevisiones {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_etapa")
	private Long idEtapa;
	
	@Column(name = "etapa")
	private String etapa;
	
	public Long getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(Long idEtapa) {
		this.idEtapa = idEtapa;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "descripcion")
	private String descripcion;



}
