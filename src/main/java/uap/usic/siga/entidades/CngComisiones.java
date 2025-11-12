package uap.usic.siga.entidades;

import java.util.Date;

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
import jakarta.persistence.Temporal;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
*
* @author Freddy Morales
*/
@Entity
@Table(name = "cng_comisiones")
public class CngComisiones extends SigaUsicGestiones{
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cng_congresista", referencedColumnName = "id_cng_congresista")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngCongresistas cngCongresistas;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_comision", referencedColumnName = "id_tipo_comision")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngTiposComisiones  cngTiposComisiones;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cng_comision")
    private Long idCngComision;

    @Column(name = "obs-")
    private String obs;

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecInicio;
    
    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_final")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecFinal;

	public CngCongresistas getCngCongresistas() {
		return cngCongresistas;
	}

	public void setCngCongresistas(CngCongresistas cngCongresistas) {
		this.cngCongresistas = cngCongresistas;
	}

	public CngTiposComisiones getCngTiposComisiones() {
		return cngTiposComisiones;
	}

	public void setCngTiposComisiones(CngTiposComisiones cngTiposComisiones) {
		this.cngTiposComisiones = cngTiposComisiones;
	}

	public Long getIdCngComision() {
		return idCngComision;
	}

	public void setIdCngComision(Long idCngComision) {
		this.idCngComision = idCngComision;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getFecInicio() {
		return fecInicio;
	}

	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}

	public Date getFecFinal() {
		return fecFinal;
	}

	public void setFecFinal(Date fecFinal) {
		this.fecFinal = fecFinal;
	}
    
    
}
