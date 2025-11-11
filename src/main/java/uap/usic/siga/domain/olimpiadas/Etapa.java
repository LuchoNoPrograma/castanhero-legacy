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
 * Entidad que representa una etapa de las olimpiadas
 * (por ejemplo: clasificatorias, eliminatorias, etc.)
 */
@Entity
@Table(name = "oo_etapas")
public class Etapa extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etapa")
    private Long idEtapa;

    @NotBlank(message = "El nombre de la etapa es obligatorio")
    @Column(name = "etapa")
    private String etapa;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    // Constructores
    public Etapa() {
    }

    public Etapa(String etapa, String descripcion) {
        this.etapa = etapa;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
