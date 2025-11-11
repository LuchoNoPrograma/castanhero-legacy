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
@Table(name = "oo_participantes")
public class OoParticipantes extends SigaUsicGestiones {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_participante")
	private Long idParticipante;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas; 

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_participante", referencedColumnName = "id_equipo_participante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OoEquiposParticipantes ooEquiposParticipantes;
    
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "ooParticipantes")
	private List<OoPuntuaciones> puntuaciones;
	
	private String edad;
	
	private String imagen;
	
	
	
	
	public OoParticipantes() {

	}




	public Long getIdParticipante() {
		return idParticipante;
	}




	public void setIdParticipante(Long idParticipante) {
		this.idParticipante = idParticipante;
	}




	public Personas getPersonas() {
		return personas;
	}




	public void setPersonas(Personas personas) {
		this.personas = personas;
	}




	



	public String getEdad() {
		return edad;
	}




	public void setEdad(String edad) {
		this.edad = edad;
	}







	public String getImagen() {
		return imagen;
	}




	public void setImagen(String imagen) {
		this.imagen = imagen;
	}




	public OoEquiposParticipantes getOoEquiposParticipantes() {
		return ooEquiposParticipantes;
	}




	public void setOoEquiposParticipantes(OoEquiposParticipantes ooEquiposParticipantes) {
		this.ooEquiposParticipantes = ooEquiposParticipantes;
	}




	public List<OoPuntuaciones> getPuntuaciones() {
		return puntuaciones;
	}




	public void setPuntuaciones(List<OoPuntuaciones> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}
	
	
	
}
	
	
