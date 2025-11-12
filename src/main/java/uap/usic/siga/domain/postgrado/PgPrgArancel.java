package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_prg_arancel")
public class PgPrgArancel extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arancel")
    private Long idArancel;

    @Column(name = "matricula")
    private Float matricula;

    @Column(name = "modulos")
    private Float modulos;

    @Column(name = "costo_programa")
    private Float costoPrograma;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPrograma programa;

    public Long getIdArancel() {
        return idArancel;
    }

    public void setIdArancel(Long idArancel) {
        this.idArancel = idArancel;
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

    public Float getCostoPrograma() {
        return costoPrograma;
    }

    public void setCostoPrograma(Float costoPrograma) {
        this.costoPrograma = costoPrograma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PgPrograma getPrograma() {
        return programa;
    }

    public void setPrograma(PgPrograma programa) {
        this.programa = programa;
    }
}
