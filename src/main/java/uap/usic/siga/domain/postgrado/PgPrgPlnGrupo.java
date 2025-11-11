package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadConGestion;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pg_prg_pln_grupo")
public class PgPrgPlnGrupo extends EntidadConGestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long idGrupo;

    @Column(name = "grupo")
    private String grupo;

    @Column(name = "capacidadGrupo")
    private Integer capacidadGrupo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_versiones", referencedColumnName = "id_versiones")
    private PgPrgPlnVersion version;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
    @JsonIgnore
    private List<PgEjecucionModulo> ejecucionesModulos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
    @JsonIgnore
    private List<PgPstMatricula> matriculas;

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getCapacidadGrupo() {
        return capacidadGrupo;
    }

    public void setCapacidadGrupo(Integer capacidadGrupo) {
        this.capacidadGrupo = capacidadGrupo;
    }

    public PgPrgPlnVersion getVersion() {
        return version;
    }

    public void setVersion(PgPrgPlnVersion version) {
        this.version = version;
    }

    public List<PgEjecucionModulo> getEjecucionesModulos() {
        return ejecucionesModulos;
    }

    public void setEjecucionesModulos(List<PgEjecucionModulo> ejecucionesModulos) {
        this.ejecucionesModulos = ejecucionesModulos;
    }

    public List<PgPstMatricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<PgPstMatricula> matriculas) {
        this.matriculas = matriculas;
    }

    public boolean tieneCapacidadDisponible() {
        if (capacidadGrupo == null || matriculas == null) return true;
        return matriculas.size() < capacidadGrupo;
    }
}
