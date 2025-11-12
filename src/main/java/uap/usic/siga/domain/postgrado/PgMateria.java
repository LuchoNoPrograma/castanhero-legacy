package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "materias")
public class PgMateria extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long idMateria;

    @Column(name = "materia")
    @NotBlank(message = "Debe introducir el nombre de la materia")
    private String materia;

    @Column(name = "sigla")
    @NotBlank(message = "Debe introducir la sigla de la materia")
    private String sigla;

    @Column(name = "hrs_lectivas")
    private Integer horasLectivas;

    @Column(name = "hrs_no_lectivas")
    private Integer horasNoLectivas;

    @Column(name = "creditos")
    private Integer creditos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_materia", referencedColumnName = "id_tipo_materia")
    private PgMtrTipoMateria tipoMateria;

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getHorasLectivas() {
        return horasLectivas;
    }

    public void setHorasLectivas(Integer horasLectivas) {
        this.horasLectivas = horasLectivas;
    }

    public Integer getHorasNoLectivas() {
        return horasNoLectivas;
    }

    public void setHorasNoLectivas(Integer horasNoLectivas) {
        this.horasNoLectivas = horasNoLectivas;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public PgMtrTipoMateria getTipoMateria() {
        return tipoMateria;
    }

    public void setTipoMateria(PgMtrTipoMateria tipoMateria) {
        this.tipoMateria = tipoMateria;
    }

    public String obtenerNombreCompleto() {
        return materia + " (" + sigla + ")";
    }

    public Integer obtenerTotalHoras() {
        int total = 0;
        if (horasLectivas != null) total += horasLectivas;
        if (horasNoLectivas != null) total += horasNoLectivas;
        return total;
    }
}
