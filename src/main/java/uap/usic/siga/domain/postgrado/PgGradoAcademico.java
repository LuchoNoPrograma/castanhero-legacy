package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

@Entity
@Table(name = "grados_academicos")
public class PgGradoAcademico extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_academico")
    private Long idGrado;

    @Column(name = "gradoacademico")
    private String gradoAcademico;

    public PgGradoAcademico() {
    }

    public PgGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public Long getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Long idGrado) {
        this.idGrado = idGrado;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }
}
