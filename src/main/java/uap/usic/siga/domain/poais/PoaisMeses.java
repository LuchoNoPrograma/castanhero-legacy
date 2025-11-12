package uap.usic.siga.domain.poais;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "poais_meses")
public class PoaisMeses extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mes")
    private Long idMes;

    @Column(name = "mes")
    private String mes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poaisMeses")
    private List<PoaisDetallesActividades> poaisDetallesActividades;

    public Long getIdMes() {
        return idMes;
    }

    public void setIdMes(Long idMes) {
        this.idMes = idMes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public List<PoaisDetallesActividades> getPoaisDetallesActividades() {
        return poaisDetallesActividades;
    }

    public void setPoaisDetallesActividades(List<PoaisDetallesActividades> poaisDetallesActividades) {
        this.poaisDetallesActividades = poaisDetallesActividades;
    }
}
