package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class Materias extends SigaUsicRevisiones{


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_materia", referencedColumnName = "id_tipo_materia")
    private PgMtrTiposMaterias tipomateria;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long IdMateria;

    @Column
    @NotBlank(message = "Debe introducir el nombre de la materia")
    private String materia;

    @Column
    @NotBlank(message = "Debe introducir la sigla de la materia")
    private String sigla;
    
    @Column(name = "hrs_lectivas")
    private Integer hrslectivas;

    @Column(name = "hrs_no_lectivas")
    private Integer hrsnolectivas;

    @Column(name = "creditos")
    private Integer creditos;

    public PgMtrTiposMaterias getTipomateria() {
        return tipomateria;
    }

    public void setTipomateria(PgMtrTiposMaterias tipomateria) {
        this.tipomateria = tipomateria;
    }

    public Long getIdMateria() {
        return IdMateria;
    }

    public void setIdMateria(Long idMateria) {
        IdMateria = idMateria;
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

    public Integer getHrslectivas() {
        return hrslectivas;
    }

    public void setHrslectivas(Integer hrslectivas) {
        this.hrslectivas = hrslectivas;
    }

    public Integer getHrsnolectivas() {
        return hrsnolectivas;
    }

    public void setHrsnolectivas(Integer hrsnolectivas) {
        this.hrsnolectivas = hrsnolectivas;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }
    


}
