package uap.usic.siga.entidades;

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
