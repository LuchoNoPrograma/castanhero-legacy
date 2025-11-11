package uap.usic.siga.domain.sicoes;

import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "scs_modalidades")
public class ScsModalidad extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_modalidad")
    private Long idScsModalidad;

    @NotNull
    @Column(name = "scs_modalidad")
    private String scsModalidad;

    public Long getIdScsModalidad() {
        return idScsModalidad;
    }

    public void setIdScsModalidad(Long idScsModalidad) {
        this.idScsModalidad = idScsModalidad;
    }

    public String getScsModalidad() {
        return scsModalidad;
    }

    public void setScsModalidad(String scsModalidad) {
        this.scsModalidad = scsModalidad;
    }
}
