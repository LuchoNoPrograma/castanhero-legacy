package uap.usic.siga.domain.electoral;

import uap.usic.siga.entidades.SigaUsicGestiones;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "esc_elecciones")
public class EscElecciones extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eleccion")
    private Long idEleccion;

    @NotNull
    @Column(name = "eleccion", nullable = false)
    private String eleccion;

    @Column(name = "codigo")
    private Integer codigo;

    public Long getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(Long idEleccion) {
        this.idEleccion = idEleccion;
    }

    public String getEleccion() {
        return eleccion;
    }

    public void setEleccion(String eleccion) {
        this.eleccion = eleccion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
