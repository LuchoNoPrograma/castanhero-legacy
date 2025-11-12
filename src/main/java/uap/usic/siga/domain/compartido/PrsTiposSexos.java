package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa tipos de sexo de personas.
 * Rectorado - USIC
 * Fecha: 2019-05-28
 */
@Entity
@Table(name = "prs_tipos_sexos")
public class PrsTiposSexos extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prs_tipo_sexo")
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "prs_tipo_sexo", nullable = false, length = 50)
    private String prsTipoSexo;

    public PrsTiposSexos() {
    }

    public PrsTiposSexos(String prsTipoSexo) {
        this.prsTipoSexo = prsTipoSexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrsTipoSexo() {
        return prsTipoSexo;
    }

    public void setPrsTipoSexo(String prsTipoSexo) {
        this.prsTipoSexo = prsTipoSexo;
    }
}
