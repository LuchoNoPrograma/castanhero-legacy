package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ejecucion_modulo")
public class PgEjecucionModulo extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejecucion_modulo")
    private Long idEjecucionModulo;

    @Column(name = "gestion")
    private Short gestion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_conclusion")
    private Date fechaConclusion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_modulo")
    private PgPrgModulo modulo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ejecucionModulo")
    private List<PgEjecucionModuloEstudiante> estudiantes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_docente")
    @JsonIgnore
    private PgDocente docente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_grupo")
    @JsonIgnore
    private PgPrgPlnGrupo grupo;

    public Long getIdEjecucionModulo() {
        return idEjecucionModulo;
    }

    public void setIdEjecucionModulo(Long idEjecucionModulo) {
        this.idEjecucionModulo = idEjecucionModulo;
    }

    public Short getGestion() {
        return gestion;
    }

    public void setGestion(Short gestion) {
        this.gestion = gestion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaConclusion() {
        return fechaConclusion;
    }

    public void setFechaConclusion(Date fechaConclusion) {
        this.fechaConclusion = fechaConclusion;
    }

    public PgPrgModulo getModulo() {
        return modulo;
    }

    public void setModulo(PgPrgModulo modulo) {
        this.modulo = modulo;
    }

    public List<PgEjecucionModuloEstudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<PgEjecucionModuloEstudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public PgDocente getDocente() {
        return docente;
    }

    public void setDocente(PgDocente docente) {
        this.docente = docente;
    }

    public PgPrgPlnGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(PgPrgPlnGrupo grupo) {
        this.grupo = grupo;
    }

    public boolean estaEnCurso() {
        Date hoy = new Date();
        return fechaInicio != null && fechaConclusion != null &&
               hoy.after(fechaInicio) && hoy.before(fechaConclusion);
    }

    public boolean estaConcluido() {
        Date hoy = new Date();
        return fechaConclusion != null && hoy.after(fechaConclusion);
    }
}
