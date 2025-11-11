package uap.usic.siga.domain.poais;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "poais_objetivos")
public class PoaisObjetivos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objetivo")
    private Long idObjetivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_poai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Poais poais;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poaisObjetivos")
    private List<PoaisResultados> poaisResultados;

    public Long getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(Long idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public Poais getPoais() {
        return poais;
    }

    public void setPoais(Poais poais) {
        this.poais = poais;
    }

    public List<PoaisResultados> getPoaisResultados() {
        return poaisResultados;
    }

    public void setPoaisResultados(List<PoaisResultados> poaisResultados) {
        this.poaisResultados = poaisResultados;
    }
}
