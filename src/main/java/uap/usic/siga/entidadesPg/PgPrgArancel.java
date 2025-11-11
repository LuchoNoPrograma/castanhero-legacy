package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pg_prg_arancel")
public class PgPrgArancel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    private Programas programa;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arancel")
    private Long idPgPrgArancel;

    private Float matricula;
    private Float modulos;
    private Float constoPrograma;
    private String descripcion;
    
    public Long getIdPgPrgArancel() {
        return idPgPrgArancel;
    }
    public void setIdPgPrgArancel(Long idPgPrgArancel) {
        this.idPgPrgArancel = idPgPrgArancel;
    }
    public Float getMatricula() {
        return matricula;
    }
    public void setMatricula(Float matricula) {
        this.matricula = matricula;
    }
    public Float getModulos() {
        return modulos;
    }
    public void setModulos(Float modulos) {
        this.modulos = modulos;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getConstoPrograma() {
        return constoPrograma;
    }
    public void setConstoPrograma(Float constoPrograma) {
        this.constoPrograma = constoPrograma;
    }
    public Programas getPrograma() {
        return programa;
    }
    public void setPrograma(Programas programa) {
        this.programa = programa;
    }

    
    
}
