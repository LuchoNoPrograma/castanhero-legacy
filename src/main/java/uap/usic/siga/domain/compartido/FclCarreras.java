package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad que representa carreras de facultades.
 * Rectorado - USIC
 * Fecha: 2021-07-09
 */
@Entity
@Table(name = "fcl_carreras")
public class FclCarreras extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Long id;

    @NotNull
    @Column(name = "carrera", nullable = false)
    private String carrera;

    @Column(name = "codigo")
    private String codigo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_facultad", referencedColumnName = "id_facultad")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Facultades facultades;

    public FclCarreras() {
    }

    public FclCarreras(String carrera, String codigo, Facultades facultades) {
        this.carrera = carrera;
        this.codigo = codigo;
        this.facultades = facultades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Facultades getFacultades() {
        return facultades;
    }

    public void setFacultades(Facultades facultades) {
        this.facultades = facultades;
    }
}
