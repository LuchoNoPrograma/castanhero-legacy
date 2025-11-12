package uap.usic.siga.domain.electoral;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
@Table(name = "esc_detalles")
public class EscDetalles extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_esc_detalle")
    private Long idEscDetalle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_escrutinio_acta")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscrutinioActas escrutinioActas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_frente")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscFrentes escFrentes;

    @Column(name = "voto_frente")
    private Double votoFrente;

    public Long getIdEscDetalle() {
        return idEscDetalle;
    }

    public void setIdEscDetalle(Long idEscDetalle) {
        this.idEscDetalle = idEscDetalle;
    }

    public EscrutinioActas getEscrutinioActas() {
        return escrutinioActas;
    }

    public void setEscrutinioActas(EscrutinioActas escrutinioActas) {
        this.escrutinioActas = escrutinioActas;
    }

    public EscFrentes getEscFrentes() {
        return escFrentes;
    }

    public void setEscFrentes(EscFrentes escFrentes) {
        this.escFrentes = escFrentes;
    }

    public Double getVotoFrente() {
        return votoFrente;
    }

    public void setVotoFrente(Double votoFrente) {
        this.votoFrente = votoFrente;
    }
}
