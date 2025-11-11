package uap.usic.siga.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "oo_competiciones")
public class OoCompeticiones extends SigaUsicGestiones{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_competiciones")
	private Long idCompeticiones;
	
	@Column(name = "competicion")
	private String competicion;
	
	@NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_encuentro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecEncuentro;

	public OoCompeticiones() {
	}

	public Long getIdCompeticiones() {
		return idCompeticiones;
	}

	public void setIdCompeticiones(Long idCompeticiones) {
		this.idCompeticiones = idCompeticiones;
	}

	public String getCompeticion() {
		return competicion;
	}

	public void setCompeticion(String competicion) {
		this.competicion = competicion;
	}

	public Date getFecEncuentro() {
		return fecEncuentro;
	}

	public void setFecEncuentro(Date fecEncuentro) {
		this.fecEncuentro = fecEncuentro;
	}
	
	
	}
