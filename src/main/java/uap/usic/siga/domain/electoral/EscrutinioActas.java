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
@Table(name = "escrutinio_actas")
public class EscrutinioActas extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escrutinio_acta")
    private Long idEscrutinioActa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mesa_habilitada")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscMesasHabilitadas escMesasHabilitadas;

    @Column(name = "nro_acta")
    private Double nroActa;

    @Column(name = "votos_validos")
    private Double votosValidos;

    @Column(name = "votos_nulos")
    private Double votosNulos;

    @Column(name = "votos_blancos")
    private Double votosBlancos;

    @Column(name = "votos_emitidos")
    private Double votosEmitidos;

    public Long getIdEscrutinioActa() {
        return idEscrutinioActa;
    }

    public void setIdEscrutinioActa(Long idEscrutinioActa) {
        this.idEscrutinioActa = idEscrutinioActa;
    }

    public EscMesasHabilitadas getEscMesasHabilitadas() {
        return escMesasHabilitadas;
    }

    public void setEscMesasHabilitadas(EscMesasHabilitadas escMesasHabilitadas) {
        this.escMesasHabilitadas = escMesasHabilitadas;
    }

    public Double getNroActa() {
        return nroActa;
    }

    public void setNroActa(Double nroActa) {
        this.nroActa = nroActa;
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

    public Double getVotosEmitidos() {
        return votosEmitidos;
    }

    public void setVotosEmitidos(Double votosEmitidos) {
        this.votosEmitidos = votosEmitidos;
    }
}
