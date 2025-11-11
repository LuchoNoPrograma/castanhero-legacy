package uap.usic.siga.entidadesPg;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pg_prog_modulos")
public class PgPrgModulos extends SigaUsicRevisiones{

    //-------------------- Llaves Foraneas ------------
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "modulo")
    @JsonIgnore
    private List<EjecucionesModulos> ejecucionModulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_planes")
    @JsonIgnore
    private PgPrgPlanes plan;
    
    ///------------------Atributos--------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo")
    private Long idModulo;

    private String modulo;

    private Short horasTeoricas;

    private Short horasColavorativas;

    private String sigla;
    //------------------------------------------------
    //-------------Getters and Setters----------------
    public PgPrgPlanes getPlan() {
        return plan;
    }
    public void setPlan(PgPrgPlanes plan) {
        this.plan = plan;
    }
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
    public Short getHorasTeoricas() {
        return horasTeoricas;
    }
    public void setHorasTeoricas(Short horasTeoricas) {
        this.horasTeoricas = horasTeoricas;
    }
    public Short getHorasColavorativas() {
        return horasColavorativas;
    }
    public void setHorasColavorativas(Short horasColavorativas) {
        this.horasColavorativas = horasColavorativas;
    }
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public List<EjecucionesModulos> getEjecucionModulo() {
        return ejecucionModulo;
    }
    public void setEjecucionModulo(List<EjecucionesModulos> ejecucionModulo) {
        this.ejecucionModulo = ejecucionModulo;
    }
}
