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
@Table(name = "fcl_estamentos")
public class FclEstamentos extends SigaUsicRevisiones {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estamento")
    private Long idEstamento;

    @NotNull
    @Column(name = "estamento")
    private String estamento;

    @Column(name = "codigo")
    private String codigo;

	public Long getIdEstamento() {
		return idEstamento;
	}

	public void setIdEstamento(Long idEstamento) {
		this.idEstamento = idEstamento;
	}

	public String getEstamento() {
		return estamento;
	}

	public void setEstamento(String estamento) {
		this.estamento = estamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
    
}
