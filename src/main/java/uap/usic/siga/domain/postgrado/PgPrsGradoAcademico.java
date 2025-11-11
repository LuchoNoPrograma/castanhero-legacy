package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

@Entity
@Table(name = "pg_prs_grados_academicos")
public class PgPrsGradoAcademico extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_academico")
    private Long idGradoAcademico;

    @Column(name = "grado_academico")
    private String gradoAcademico;

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
}
