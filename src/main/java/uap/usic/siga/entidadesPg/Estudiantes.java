package uap.usic.siga.entidadesPg;

import java.util.Date;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.Personas;

@Entity
@Table(name = "estudiantes")
public class Estudiantes extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Personas personas;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Programas programas;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_estudiante", referencedColumnName = "id_tipo_estudiante")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private PgTipoEstudiante pgTipoEstudiante;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estudiante")
	private Long idEstudiante;

	@DateTimeFormat(pattern = "yyy-MM-dd")
	@Column(name = "fec_inscripcion ")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fecInscripcion;

	@DateTimeFormat(pattern = "yyy-MM-dd")
	@Column(name = "fec_egreso")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fecEgreso;

	@Column(name = "observacion")
	private String observacion;

	public Personas getPersonas() {
		return personas;
	}

	public void setPersonas(Personas personas) {
		this.personas = personas;
	}

	public Programas getProgramas() {
		return programas;
	}

	public void setProgramas(Programas programas) {
		this.programas = programas;
	}

	public PgTipoEstudiante getPgTipoEstudiante() {
		return pgTipoEstudiante;
	}

	public void setPgTipoEstudiante(PgTipoEstudiante pgTipoEstudiante) {
		this.pgTipoEstudiante = pgTipoEstudiante;
	}

	public Long getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public Date getFecInscripcion() {
		return fecInscripcion;
	}

	public void setFecInscripcion(Date fecInscripcion) {
		this.fecInscripcion = fecInscripcion;
	}

	public Date getFecEgreso() {
		return fecEgreso;
	}

	public void setFecEgreso(Date fecEgreso) {
		this.fecEgreso = fecEgreso;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
