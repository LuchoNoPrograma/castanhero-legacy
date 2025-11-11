package uap.usic.siga.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
@Table(name = "oo_equipos_participantes")
public class OoEquiposParticipantes extends SigaUsicGestiones {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_equipo_participante")
	private Long idEquipoParticipante;

	private String nombre;
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competiciones", referencedColumnName = "id_competiciones")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OoCompeticiones ooCompeticiones;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_educativa", referencedColumnName = "id_unidad_educativa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OoUnidadesEducativas ooUnidadesEducativas;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "ooEquiposParticipantes")
	private List<OoPuntuaciones> puntuaciones;

	public OoEquiposParticipantes() {
	}
	
	public OoEquiposParticipantes(Long idEquipoParticipante, String nombre, String descripcion,
			OoCompeticiones ooCompeticiones, OoUnidadesEducativas ooUnidadesEducativas) {
		super();
		this.idEquipoParticipante = idEquipoParticipante;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ooCompeticiones = ooCompeticiones;
		this.ooUnidadesEducativas = ooUnidadesEducativas;
	}

	public Long getIdEquipoParticipante() {
		return idEquipoParticipante;
	}

	public void setIdEquipoParticipante(Long idEquipoParticipante) {
		this.idEquipoParticipante = idEquipoParticipante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public OoCompeticiones getOoCompeticiones() {
		return ooCompeticiones;
	}

	public void setOoCompeticiones(OoCompeticiones ooCompeticiones) {
		this.ooCompeticiones = ooCompeticiones;
	}

	public OoUnidadesEducativas getOoUnidadesEducativas() {
		return ooUnidadesEducativas;
	}

	public void setOoUnidadesEducativas(OoUnidadesEducativas ooUnidadesEducativas) {
		this.ooUnidadesEducativas = ooUnidadesEducativas;
	}

	public List<OoPuntuaciones> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(List<OoPuntuaciones> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}



}
