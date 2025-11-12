package uap.usic.siga.entidadesPg;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pg_prg_pln_grupo")
public class PgPrgPlnGrupo extends SigaUsicGestiones{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_versiones", referencedColumnName = "id_versiones")
    private PgPrgPlnVersiones version;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
    @JsonIgnore
    private List<EjecucionesModulos> ejecucionesModulos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
    @JsonIgnore
    private List<PgPstMatriculas> matriculas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long idPgPrgPlnGrupos;

    private String grupo;
    
    private Integer capacidadGrupo;

    public PgPrgPlnVersiones getVersion() {
        return version;
    }

    public void setVersion(PgPrgPlnVersiones version) {
        this.version = version;
    }

    public Long getIdPgPrgPlnGrupos() {
        return idPgPrgPlnGrupos;
    }

    public void setIdPgPrgPlnGrupos(Long idPgPrgPlnGrupos) {
        this.idPgPrgPlnGrupos = idPgPrgPlnGrupos;
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

    public List<EjecucionesModulos> getEjecucionesModulos() {
        return ejecucionesModulos;
    }

    public void setEjecucionesModulos(List<EjecucionesModulos> ejecucionesModulos) {
        this.ejecucionesModulos = ejecucionesModulos;
    }

    public List<PgPstMatriculas> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<PgPstMatriculas> matriculas) {
        this.matriculas = matriculas;
    }
    
}
