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
