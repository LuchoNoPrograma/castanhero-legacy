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
@Table(name = "poais_detalles_actividades")
public class PoaisDetallesActividades extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisActividades  poaisActividades;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mes", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisMeses  poaisMeses;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_semana", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisSemanas  poaisSemanas;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_actividad")
    private Long idDetalleActividad;

	public PoaisActividades getPoaisActividades() {
		return poaisActividades;
	}

	public void setPoaisActividades(PoaisActividades poaisActividades) {
		this.poaisActividades = poaisActividades;
	}

	public PoaisMeses getPoaisMeses() {
		return poaisMeses;
	}

	public void setPoaisMeses(PoaisMeses poaisMeses) {
		this.poaisMeses = poaisMeses;
	}

	public PoaisSemanas getPoaisSemanas() {
		return poaisSemanas;
	}

	public void setPoaisSemanas(PoaisSemanas poaisSemanas) {
		this.poaisSemanas = poaisSemanas;
	}

	public Long getIdDetalleActividad() {
		return idDetalleActividad;
	}

	public void setIdDetalleActividad(Long idDetalleActividad) {
		this.idDetalleActividad = idDetalleActividad;
	}
	
	
}
