package uap.usic.siga.domain.poais;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
