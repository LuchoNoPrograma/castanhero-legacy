package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad que representa grados académicos de personas.
 */
@Entity
@Table(name = "prs_grados_academicos")
public class PrsGradosAcademicos extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_academico")
    private Long id;

    @NotNull
    @Column(name = "grado_academico", nullable = false)
    private String gradoAcademico;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public PrsGradosAcademicos() {
    }

    public PrsGradosAcademicos(String gradoAcademico, String descripcion) {
        this.gradoAcademico = gradoAcademico;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
