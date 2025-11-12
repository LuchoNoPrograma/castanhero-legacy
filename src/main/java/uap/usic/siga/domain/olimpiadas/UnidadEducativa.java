package uap.usic.siga.domain.olimpiadas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import uap.usic.siga.entidades.SigaUsicGestiones;

/**
 * Entidad que representa una unidad educativa participante en las olimpiadas
 */
@Entity
@Table(name = "oo_unidades_educativas")
public class UnidadEducativa extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad_educativa")
    private Long idUnidadEducativa;

    @NotBlank(message = "El nombre de la unidad educativa es obligatorio")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    // Constructores
    public UnidadEducativa() {
    }

    public UnidadEducativa(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getIdUnidadEducativa() {
        return idUnidadEducativa;
    }

    public void setIdUnidadEducativa(Long idUnidadEducativa) {
        this.idUnidadEducativa = idUnidadEducativa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
