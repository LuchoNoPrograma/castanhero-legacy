package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadGestionable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad que representa el sistema administrador.
 */
@Entity
@Table(name = "sis_administrador")
public class SisAdministrador extends EntidadGestionable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sis_administrador")
    private Long id;

    @NotNull
    @Column(name = "nombre_sis", nullable = false)
    private String nombreSis;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "obs")
    private String obs;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Double valor;

    public SisAdministrador() {
    }

    public SisAdministrador(String nombreSis, String descripcion, String obs, Double valor) {
        this.nombreSis = nombreSis;
        this.descripcion = descripcion;
        this.obs = obs;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSis() {
        return nombreSis;
    }

    public void setNombreSis(String nombreSis) {
        this.nombreSis = nombreSis;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
