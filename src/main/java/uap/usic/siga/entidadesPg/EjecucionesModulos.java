package uap.usic.siga.entidadesPg;

import java.util.Date;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ejecucion_modulo")
public class EjecucionesModulos extends SigaUsicRevisiones{

    @ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_modulo")
    private PgPrgModulos modulo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ejecucionModulo")
    private List<EjecucionModuloEstudiante> estudiantes;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "id_docente")
    @JsonIgnore
    private Docentes docente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_grupo")
    @JsonIgnore
    private PgPrgPlnGrupo grupo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejecucion_modulo")
    private Long idEjecucionModulo;
    
    private Short gestion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_conclusion")
    private Date fechaConclusion;

    public PgPrgModulos getModulo() {
        return modulo;
    }

    public void setModulo(PgPrgModulos modulo) {
        this.modulo = modulo;
    }

    public Docentes getDocente() {
        return docente;
    }

    public void setDocente(Docentes docente) {
        this.docente = docente;
    }

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

    public List<EjecucionModuloEstudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<EjecucionModuloEstudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public PgPrgPlnGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(PgPrgPlnGrupo grupo) {
        this.grupo = grupo;
    }    

    @Override
    public String toString() {
        String str = "EjecucionModulo= "+idEjecucionModulo+
        ",\nDocente= "+docente.getNombres()+" "+docente.getPaterno()+" "+docente.getMaterno()+
        ",\nModulo=" + modulo.getModulo() +
        ",\nGrupo=" + grupo.getGrupo() +
        ",\nEstudiantes={\n";

        StringBuilder txt = new StringBuilder(str);
        for(EjecucionModuloEstudiante a : estudiantes){
            Postulantes pst = a.getMatriculas().getPstProgramas().getPostulantes();
            String estudiante = pst.getNombres()+" "+pst.getPaterno()+" "+pst.getMaterno()+
            "\nTiene la nota de: "+a.getNotaFinal()+
            ", estado= "+(a.getNotaFinal()>= 65?"aprobado\n\n":"reprobado\n\n");
            txt.append(estudiante);
        }
        txt.append("}");
        return txt.toString();
    }
}
