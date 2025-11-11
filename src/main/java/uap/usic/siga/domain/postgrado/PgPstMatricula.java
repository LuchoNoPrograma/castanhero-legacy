package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadConGestion;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pg_pst_matriculas")
public class PgPstMatricula extends EntidadConGestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;

    @Column(name = "numMatricula")
    private String numeroMatricula;

    @Column(name = "clave")
    private String clave = "123456";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    private PgPrgPlnGrupo grupo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pst_programas", referencedColumnName = "id_pst_programas")
    private PgPstPrograma pstPrograma;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matricula")
    private List<PgEjecucionModuloEstudiante> ejecucionesModulos;

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public PgPrgPlnGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(PgPrgPlnGrupo grupo) {
        this.grupo = grupo;
    }

    public PgPstPrograma getPstPrograma() {
        return pstPrograma;
    }

    public void setPstPrograma(PgPstPrograma pstPrograma) {
        this.pstPrograma = pstPrograma;
    }

    public List<PgEjecucionModuloEstudiante> getEjecucionesModulos() {
        return ejecucionesModulos;
    }

    public void setEjecucionesModulos(List<PgEjecucionModuloEstudiante> ejecucionesModulos) {
        this.ejecucionesModulos = ejecucionesModulos;
    }

    public boolean tieneCredenciales() {
        return numeroMatricula != null && clave != null;
    }
}
