package uap.usic.siga.entidadesPg;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ejecucion_modulo_estudiante")
public class EjecucionModuloEstudiante extends SigaUsicRevisiones{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejecucion_modulo_estudiante")
    private Long idEjecucionModuloEstudiante;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_ejecucion_modulo")
    private EjecucionesModulos ejecucionModulo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_matricula")
    private PgPstMatriculas matriculas;

    @Column(name = "nota_final")
    private Short notaFinal;

    @Column(name = "estado_calificacion")
    private String estadoCalificacion;

    public EjecucionModuloEstudiante() {
        this.estadoCalificacion = "Sin calificar";
    }

    public EjecucionModuloEstudiante(PgPstMatriculas matriculas) {
        this.matriculas = matriculas;
        this.estadoCalificacion = "Sin calificar";
    }

    public EjecucionModuloEstudiante(PgPstMatriculas matriculas, EjecucionesModulos ejecucionModulo) {
        this.ejecucionModulo = ejecucionModulo;
        this.matriculas = matriculas;
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
        if(notaFinal==null){
            this.setEstadoCalificacion("Sin calificar");
        }else if(notaFinal>= 65){
            this.setEstadoCalificacion("Aprobado");
        }else if(notaFinal != 0){
            this.setEstadoCalificacion("Reprobado");
        }else{
            this.setEstadoCalificacion("Abandono");
        }
    }

    public EjecucionesModulos getEjecucionModulo() {
        return ejecucionModulo;
    }

    public void setEjecucionModulo(EjecucionesModulos ejecucionModulo) {
        this.ejecucionModulo = ejecucionModulo;
    }
    
    public String getEstadoCalificacion() {
        return estadoCalificacion;
    }

    public void setEstadoCalificacion(String estadoCalificacion) {
        this.estadoCalificacion = estadoCalificacion;
    }

    public PgPstMatriculas getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(PgPstMatriculas matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        Postulantes pst = this.getMatriculas().getPstProgramas().getPostulantes();

        String estudiante = pst.getNombres()+" "+pst.getPaterno()+" "+pst.getMaterno()+
        "\nTiene la nota de= "+this.getNotaFinal()+", estado= "+this.getEstadoCalificacion();
        txt.append(estudiante).append("\n");

        return txt.toString();
    }
}
