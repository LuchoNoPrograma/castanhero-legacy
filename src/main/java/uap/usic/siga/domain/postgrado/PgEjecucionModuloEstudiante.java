package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ejecucion_modulo_estudiante")
public class PgEjecucionModuloEstudiante extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejecucion_modulo_estudiante")
    private Long idEjecucionModuloEstudiante;

    @Column(name = "nota_final")
    private Short notaFinal;

    @Column(name = "estado_calificacion")
    private String estadoCalificacion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_ejecucion_modulo")
    private PgEjecucionModulo ejecucionModulo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_matricula")
    private PgPstMatricula matricula;

    public PgEjecucionModuloEstudiante() {
        this.estadoCalificacion = "Sin calificar";
    }

    public PgEjecucionModuloEstudiante(PgPstMatricula matricula) {
        this.matricula = matricula;
        this.estadoCalificacion = "Sin calificar";
    }

    public PgEjecucionModuloEstudiante(PgPstMatricula matricula, PgEjecucionModulo ejecucionModulo) {
        this.ejecucionModulo = ejecucionModulo;
        this.matricula = matricula;
        this.estadoCalificacion = "Sin calificar";
    }

    public Long getIdEjecucionModuloEstudiante() {
        return idEjecucionModuloEstudiante;
    }

    public void setIdEjecucionModuloEstudiante(Long idEjecucionModuloEstudiante) {
        this.idEjecucionModuloEstudiante = idEjecucionModuloEstudiante;
    }

    public Short getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Short notaFinal) {
        this.notaFinal = notaFinal;
        actualizarEstadoCalificacion();
    }

    public String getEstadoCalificacion() {
        return estadoCalificacion;
    }

    public void setEstadoCalificacion(String estadoCalificacion) {
        this.estadoCalificacion = estadoCalificacion;
    }

    public PgEjecucionModulo getEjecucionModulo() {
        return ejecucionModulo;
    }

    public void setEjecucionModulo(PgEjecucionModulo ejecucionModulo) {
        this.ejecucionModulo = ejecucionModulo;
    }

    public PgPstMatricula getMatricula() {
        return matricula;
    }

    public void setMatricula(PgPstMatricula matricula) {
        this.matricula = matricula;
    }

    private void actualizarEstadoCalificacion() {
        if (notaFinal == null) {
            this.estadoCalificacion = "Sin calificar";
        } else if (notaFinal >= 65) {
            this.estadoCalificacion = "Aprobado";
        } else if (notaFinal != 0) {
            this.estadoCalificacion = "Reprobado";
        } else {
            this.estadoCalificacion = "Abandono";
        }
    }

    public boolean estaAprobado() {
        return "Aprobado".equals(estadoCalificacion);
    }

    public boolean estaReprobado() {
        return "Reprobado".equals(estadoCalificacion);
    }
}
