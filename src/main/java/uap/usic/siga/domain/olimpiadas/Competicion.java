package uap.usic.siga.domain.olimpiadas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import uap.usic.siga.entidades.SigaUsicGestiones;

/**
 * Entidad que representa una competición de las olimpiadas
 * Extiende de SigaUsicGestiones para heredar gestión y periodo
 */
@Entity
@Table(name = "oo_competiciones")
public class Competicion extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competiciones")
    private Long idCompeticion;

    @Column(name = "competicion")
    private String competicion;

    @NotNull(message = "La fecha del encuentro es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_encuentro")
    @Temporal(TemporalType.DATE)
    private Date fechaEncuentro;

    // Constructores
    public Competicion() {
    }

    public Competicion(String competicion, Date fechaEncuentro) {
        this.competicion = competicion;
        this.fechaEncuentro = fechaEncuentro;
    }

    // Getters y Setters
    public Long getIdCompeticion() {
        return idCompeticion;
    }

    public void setIdCompeticion(Long idCompeticion) {
        this.idCompeticion = idCompeticion;
    }

    public String getCompeticion() {
        return competicion;
    }

    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    public Date getFechaEncuentro() {
        return fechaEncuentro;
    }

    public void setFechaEncuentro(Date fechaEncuentro) {
        this.fechaEncuentro = fechaEncuentro;
    }
}
