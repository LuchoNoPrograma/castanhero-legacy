package uap.usic.siga.entidades;

import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "poais_resultados")
public class PoaisResultados extends SigaUsicRevisiones {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poaisResultados")
	private List<PoaisActividades> poaisActividades;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_objetivo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisObjetivos  poaisObjetivos;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resultado")
    private Long idResultado;
	
	@Column(name = "resultado")
    private String resultado;
	
	@Column(name = "indicador")
    private String indicador;
	
	@Column(name = "ponderacion")
    private Double ponderacion;
	
	@Column(name = "tipo")
	private String tipo;

	public PoaisObjetivos getPoaisObjetivos() {
		return poaisObjetivos;
	}

	public void setPoaisObjetivos(PoaisObjetivos poaisObjetivos) {
		this.poaisObjetivos = poaisObjetivos;
	}

	public Long getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public Double getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(Double ponderacion) {
		this.ponderacion = ponderacion;
	}

	public List<PoaisActividades> getPoaisActividades() {
		return poaisActividades;
	}

	public void setPoaisActividades(List<PoaisActividades> poaisActividades) {
		this.poaisActividades = poaisActividades;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
