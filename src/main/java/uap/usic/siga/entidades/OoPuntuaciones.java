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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "oo_puntuaciones")
public class OoPuntuaciones extends SigaUsicGestiones{
    
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
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_participante", referencedColumnName = "id_participante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OoParticipantes ooParticipantes; 
      
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_puntuacion")
    private Long idPuntuacion;

     @Column(name = "puntaje")
     private Integer puntaje;

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

    public OoParticipantes getOoParticipantes() {
        return ooParticipantes;
    }

    public void setOoParticipantes(OoParticipantes ooParticipantes) {
        this.ooParticipantes = ooParticipantes;
    }

    public Long getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(Long idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
 

}
