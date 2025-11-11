package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* Rectorado - USIC
* Fecha: 2021-07-09
* @author Freddy Morales
*/
@Entity
@Table(name = "esc_elecciones")
public class EscElecciones extends SigaUsicGestiones {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eleccion")
    private Long idEleccion;

    @NotNull
    @Column(name = "eleccion")
    private String eleccion;

    @Column(name = "codigo")
    private Integer codigo;

	public Long getIdEleccion() {
		return idEleccion;
	}

	public void setIdEleccion(Long idEleccion) {
		this.idEleccion = idEleccion;
	}

	public String getEleccion() {
		return eleccion;
	}

	public void setEleccion(String eleccion) {
		this.eleccion = eleccion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	

     
}
