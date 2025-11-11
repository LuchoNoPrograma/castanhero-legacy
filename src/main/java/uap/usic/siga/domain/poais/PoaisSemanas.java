package uap.usic.siga.domain.poais;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "poais_semanas")
public class PoaisSemanas extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_semana")
    private Long idSemana;

    @Column(name = "semana")
    private String semana;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poaisSemanas")
    private List<PoaisDetallesActividades> poaisDetallesActividades;

    public Long getIdSemana() {
        return idSemana;
    }

    public void setIdSemana(Long idSemana) {
        this.idSemana = idSemana;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public List<PoaisDetallesActividades> getPoaisDetallesActividades() {
        return poaisDetallesActividades;
    }

    public void setPoaisDetallesActividades(List<PoaisDetallesActividades> poaisDetallesActividades) {
        this.poaisDetallesActividades = poaisDetallesActividades;
    }
}
