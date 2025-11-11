package uap.usic.siga.domain.olimpiadas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import uap.usic.siga.entidades.SigaUsicRevisiones;

/**
 * Entidad que representa un tipo de encuentro en las olimpiadas
 * (por ejemplo: semifinal, final, cuartos de final, etc.)
 */
@Entity
@Table(name = "oo_encuentros")
public class Encuentro extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encuentro")
    private Long idEncuentro;

    @NotBlank(message = "El nombre del encuentro es obligatorio")
    @Column(name = "encuentro")
    private String encuentro;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    // Constructores
    public Encuentro() {
    }

    public Encuentro(String encuentro, String descripcion) {
        this.encuentro = encuentro;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getIdEncuentro() {
        return idEncuentro;
    }

    public void setIdEncuentro(Long idEncuentro) {
        this.idEncuentro = idEncuentro;
    }

    public String getEncuentro() {
        return encuentro;
    }

    public void setEncuentro(String encuentro) {
        this.encuentro = encuentro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
