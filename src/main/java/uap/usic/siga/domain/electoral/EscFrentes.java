package uap.usic.siga.domain.electoral;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.Facultades;
import uap.usic.siga.entidades.FclCarreras;
import uap.usic.siga.entidades.FclEstamentos;
import uap.usic.siga.entidades.SigaUsicGestiones;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "esc_frentes")
public class EscFrentes extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_frente")
    private Long idFrente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_facultad")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Facultades facultades;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_carrera")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FclCarreras fclCarreras;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estamento")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FclEstamentos fclEstamentos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_eleccion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscElecciones escElecciones;

    @NotNull
    @Column(name = "frente", nullable = false)
    private String frente;

    @NotNull
    @Column(name = "sigla", nullable = false)
    private String sigla;

    @Column(name = "nro_integrantes")
    private Integer nroIntegrantes;

    public Long getIdFrente() {
        return idFrente;
    }

    public void setIdFrente(Long idFrente) {
        this.idFrente = idFrente;
    }

    public Facultades getFacultades() {
        return facultades;
    }

    public void setFacultades(Facultades facultades) {
        this.facultades = facultades;
    }

    public FclCarreras getFclCarreras() {
        return fclCarreras;
    }

    public void setFclCarreras(FclCarreras fclCarreras) {
        this.fclCarreras = fclCarreras;
    }

    public FclEstamentos getFclEstamentos() {
        return fclEstamentos;
    }

    public void setFclEstamentos(FclEstamentos fclEstamentos) {
        this.fclEstamentos = fclEstamentos;
    }

    public EscElecciones getEscElecciones() {
        return escElecciones;
    }

    public void setEscElecciones(EscElecciones escElecciones) {
        this.escElecciones = escElecciones;
    }

    public String getFrente() {
        return frente;
    }

    public void setFrente(String frente) {
        this.frente = frente;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getNroIntegrantes() {
        return nroIntegrantes;
    }

    public void setNroIntegrantes(Integer nroIntegrantes) {
        this.nroIntegrantes = nroIntegrantes;
    }
}
