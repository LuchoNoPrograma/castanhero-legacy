package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* Rectorado - USIC
* Fecha: 2021-07-09
* @author Freddy Morales
*/
@Entity
@Table(name = "fcl_carreras")
public class FclCarreras extends SigaUsicRevisiones {
	
	@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "id_facultad", referencedColumnName = "id_facultad")
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private Facultades facultades;
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_carrera")
	    private Long idCarrera;

	    @NotNull
	    @Column(name = "carrera")
	    private String carrera;

	    @Column(name = "codigo")
	    private String codigo;

		public Facultades getFacultades() {
			return facultades;
		}

		public void setFacultades(Facultades facultades) {
			this.facultades = facultades;
		}

		public Long getIdCarrera() {
			return idCarrera;
		}

		public void setIdCarrera(Long idCarrera) {
			this.idCarrera = idCarrera;
		}

		public String getCarrera() {
			return carrera;
		}

		public void setCarrera(String carrera) {
			this.carrera = carrera;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
	    

}
