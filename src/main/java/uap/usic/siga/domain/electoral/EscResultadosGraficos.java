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

@Entity
@Table(name = "esc_resultados_graficos")
public class EscResultadosGraficos extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado_grafico")
    private Long idResultadoGrafico;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_frente")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscFrentes escFrentes;

    @Column(name = "votos_validos")
    private Double votosValidos;

    @Column(name = "votos_nulos")
    private Double votosNulos;

    @Column(name = "votos_blancos")
    private Double votosBlancos;

    @Column(name = "votos_frente")
    private Double votosFrente;

    public Long getIdResultadoGrafico() {
        return idResultadoGrafico;
    }

    public void setIdResultadoGrafico(Long idResultadoGrafico) {
        this.idResultadoGrafico = idResultadoGrafico;
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

    public EscFrentes getEscFrentes() {
        return escFrentes;
    }

    public void setEscFrentes(EscFrentes escFrentes) {
        this.escFrentes = escFrentes;
    }

    public Double getVotosValidos() {
        return votosValidos;
    }

    public void setVotosValidos(Double votosValidos) {
        this.votosValidos = votosValidos;
    }

    public Double getVotosNulos() {
        return votosNulos;
    }

    public void setVotosNulos(Double votosNulos) {
        this.votosNulos = votosNulos;
    }

    public Double getVotosBlancos() {
        return votosBlancos;
    }

    public void setVotosBlancos(Double votosBlancos) {
        this.votosBlancos = votosBlancos;
    }

    public Double getVotosFrente() {
        return votosFrente;
    }

    public void setVotosFrente(Double votosFrente) {
        this.votosFrente = votosFrente;
    }
}
