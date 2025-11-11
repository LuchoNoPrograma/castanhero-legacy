package uap.usic.siga.domain.poais;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.SigaUsicRevisiones;

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
@Table(name = "poais_detalles_actividades")
public class PoaisDetallesActividades extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_actividad")
    private Long idDetalleActividad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisActividades poaisActividades;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mes", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisMeses poaisMeses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_semana", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisSemanas poaisSemanas;

    public Long getIdDetalleActividad() {
        return idDetalleActividad;
    }

    public void setIdDetalleActividad(Long idDetalleActividad) {
        this.idDetalleActividad = idDetalleActividad;
    }

    public PoaisActividades getPoaisActividades() {
        return poaisActividades;
    }

    public void setPoaisActividades(PoaisActividades poaisActividades) {
        this.poaisActividades = poaisActividades;
    }

    public PoaisMeses getPoaisMeses() {
        return poaisMeses;
    }

    public void setPoaisMeses(PoaisMeses poaisMeses) {
        this.poaisMeses = poaisMeses;
    }

    public PoaisSemanas getPoaisSemanas() {
        return poaisSemanas;
    }

    public void setPoaisSemanas(PoaisSemanas poaisSemanas) {
        this.poaisSemanas = poaisSemanas;
    }
}
