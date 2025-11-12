package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


/**
 *
 * @author Yessenia
 */
@Entity
@Table(name = "prs_grados_academicos")
public class PrsGradosAcademicos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_academico")
    private Long idGradoAcademico;

    @NotNull
    @Column(name = "grado_academico")
    private String gradoAcademico;

    @NotNull
    @Column(name = "descripcion")
    private String descripcion;

    

    public PrsGradosAcademicos() {
    }

    public PrsGradosAcademicos(@NotNull String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public Long getIdGradoAcademico() {
        return idGradoAcademico;
    }

    public void setIdGradoAcademico(Long idGradoAcademico) {
        this.idGradoAcademico = idGradoAcademico;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
      
}
