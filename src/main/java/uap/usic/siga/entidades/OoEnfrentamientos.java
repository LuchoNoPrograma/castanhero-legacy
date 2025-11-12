package uap.usic.siga.entidades;


import java.util.Date;
import java.util.List;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "oo_enfrentamientos")
public class OoEnfrentamientos  extends SigaUsicRevisiones {
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_encuentro", referencedColumnName = "id_encuentro")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OoEncuentros ooEncuentros;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etapa", referencedColumnName = "id_etapa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OoEtapas ooEtapas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ooEnfrentamientos")
	private List<OoDetalleEnfrentamientos> ooDetalleEnfrentamientos;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ooEnfrentamientos")
	private List<OoPuntuaciones> ooPuntuaciones;
 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_enfrentamientos")
	private Long idEnfrentamientos;
	
	@Column(name = "encuentro")
	private Integer encuentro;

	@NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_encuentro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecEncuentro;

	//@Transient
	private Long idEquipoParticipante;

	//@Transient
	private Long idEquipoParticipanteR;

	@Transient
	private Long idEtapa;

	public OoEncuentros getOoEncuentros() {
		return ooEncuentros;
	}

	public void setOoEncuentros(OoEncuentros ooEncuentros) {
		this.ooEncuentros = ooEncuentros;
	}

	public OoEtapas getOoEtapas() {
		return ooEtapas;
	}

	public void setOoEtapas(OoEtapas ooEtapas) {
		this.ooEtapas = ooEtapas;
	}

	public List<OoDetalleEnfrentamientos> getOoDetalleEnfrentamientos() {
		return ooDetalleEnfrentamientos;
	}

	public void setOoDetalleEnfrentamientos(List<OoDetalleEnfrentamientos> ooDetalleEnfrentamientos) {
		this.ooDetalleEnfrentamientos = ooDetalleEnfrentamientos;
	}

	public Long getIdEnfrentamientos() {
		return idEnfrentamientos;
	}

	public void setIdEnfrentamientos(Long idEnfrentamientos) {
		this.idEnfrentamientos = idEnfrentamientos;
	}

	public Integer getEncuentro() {
		return encuentro;
	}

	public void setEncuentro(Integer encuentro) {
		this.encuentro = encuentro;
	}

	public Date getFecEncuentro() {
		return fecEncuentro;
	}

	public void setFecEncuentro(Date fecEncuentro) {
		this.fecEncuentro = fecEncuentro;
	}

	public Long getIdEquipoParticipante() {
		return idEquipoParticipante;
	}

	public void setIdEquipoParticipante(Long idEquipoParticipante) {
		this.idEquipoParticipante = idEquipoParticipante;
	}

	public Long getIdEquipoParticipanteR() {
		return idEquipoParticipanteR;
	}

	public void setIdEquipoParticipanteR(Long idEquipoParticipanteR) {
		this.idEquipoParticipanteR = idEquipoParticipanteR;
	}

	public Long getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(Long idEtapa) {
		this.idEtapa = idEtapa;
	}

	public List<OoPuntuaciones> getOoPuntuaciones() {
		return ooPuntuaciones;
	}

	public void setOoPuntuaciones(List<OoPuntuaciones> ooPuntuaciones) {
		this.ooPuntuaciones = ooPuntuaciones;
	}

					
}
