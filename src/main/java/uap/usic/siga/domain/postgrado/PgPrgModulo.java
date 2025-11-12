package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pg_prog_modulos")
public class PgPrgModulo extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo")
    private Long idModulo;

    @Column(name = "modulo")
    private String modulo;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "horasTeoricas")
    private Short horasTeoricas;

    @Column(name = "horasColavorativas")
    private Short horasColaborativas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "modulo")
    @JsonIgnore
    private List<PgEjecucionModulo> ejecucionesModulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_planes")
    @JsonIgnore
    private PgPrgPlan plan;

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Short getHorasTeoricas() {
        return horasTeoricas;
    }

    public void setHorasTeoricas(Short horasTeoricas) {
        this.horasTeoricas = horasTeoricas;
    }

    public Short getHorasColaborativas() {
        return horasColaborativas;
    }

    public void setHorasColaborativas(Short horasColaborativas) {
        this.horasColaborativas = horasColaborativas;
    }

    public List<PgEjecucionModulo> getEjecucionesModulo() {
        return ejecucionesModulo;
    }

    public void setEjecucionesModulo(List<PgEjecucionModulo> ejecucionesModulo) {
        this.ejecucionesModulo = ejecucionesModulo;
    }

    public PgPrgPlan getPlan() {
        return plan;
    }

    public void setPlan(PgPrgPlan plan) {
        this.plan = plan;
    }

    public String obtenerNombreCompleto() {
        return modulo + (sigla != null ? " (" + sigla + ")" : "");
    }

    public Integer obtenerTotalHoras() {
        int total = 0;
        if (horasTeoricas != null) total += horasTeoricas;
        if (horasColaborativas != null) total += horasColaborativas;
        return total;
    }
}
