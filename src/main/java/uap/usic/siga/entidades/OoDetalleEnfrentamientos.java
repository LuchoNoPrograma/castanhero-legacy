package uap.usic.siga.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "oo_detalle_enfrentamiento")
public class OoDetalleEnfrentamientos extends SigaUsicRevisiones {
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enfrentamientos", referencedColumnName = "id_enfrentamientos")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OoEnfrentamientos ooEnfrentamientos;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_participante", referencedColumnName = "id_equipo_participante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OoEquiposParticipantes ooEquiposParticipantes;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_eenfrentamiento")
    private Long idDetalleEnfrentamiento;

	public OoEnfrentamientos getOoEnfrentamientos() {
		return ooEnfrentamientos;
	}

	public void setOoEnfrentamientos(OoEnfrentamientos ooEnfrentamientos) {
		this.ooEnfrentamientos = ooEnfrentamientos;
	}

	public OoEquiposParticipantes getOoEquiposParticipantes() {
		return ooEquiposParticipantes;
	}

	public void setOoEquiposParticipantes(OoEquiposParticipantes ooEquiposParticipantes) {
		this.ooEquiposParticipantes = ooEquiposParticipantes;
	}

	public Long getIdDetalleEnfrentamiento() {
		return idDetalleEnfrentamiento;
	}

	public void setIdDetalleEnfrentamiento(Long idDetalleEnfrentamiento) {
		this.idDetalleEnfrentamiento = idDetalleEnfrentamiento;
	}
	

}
