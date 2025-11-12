package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad que representa estamentos de facultades.
 * Rectorado - USIC
 * Fecha: 2021-07-09
 */
@Entity
@Table(name = "fcl_estamentos")
public class FclEstamentos extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estamento")
    private Long id;

    @NotNull
    @Column(name = "estamento", nullable = false)
    private String estamento;

    @Column(name = "codigo")
    private String codigo;

    public FclEstamentos() {
    }

    public FclEstamentos(String estamento, String codigo) {
        this.estamento = estamento;
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstamento() {
        return estamento;
    }

    public void setEstamento(String estamento) {
        this.estamento = estamento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
