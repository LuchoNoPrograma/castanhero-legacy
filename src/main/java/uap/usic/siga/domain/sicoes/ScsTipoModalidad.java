package uap.usic.siga.domain.sicoes;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "scs_tipos_modalidades")
public class ScsTipoModalidad extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_tipo_modalidad")
    private Long idScsTipoModalidad;

    @NotNull
    @Column(name = "scs_tipo_modalidad")
    private String scsTipoModalidad;

    public Long getIdScsTipoModalidad() {
        return idScsTipoModalidad;
    }

    public void setIdScsTipoModalidad(Long idScsTipoModalidad) {
        this.idScsTipoModalidad = idScsTipoModalidad;
    }

    public String getScsTipoModalidad() {
        return scsTipoModalidad;
    }

    public void setScsTipoModalidad(String scsTipoModalidad) {
        this.scsTipoModalidad = scsTipoModalidad;
    }
}
