package uap.usic.siga.entidades;

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
