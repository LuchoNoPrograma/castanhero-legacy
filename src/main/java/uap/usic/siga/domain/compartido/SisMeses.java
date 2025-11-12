package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entidad que representa meses del sistema.
 */
@Entity
@Table(name = "sis_meses")
public class SisMeses extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mes")
    private Long id;

    @NotNull
    @Column(name = "mes", nullable = false)
    private String mes;

    public SisMeses() {
    }

    public SisMeses(String mes) {
        this.mes = mes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
