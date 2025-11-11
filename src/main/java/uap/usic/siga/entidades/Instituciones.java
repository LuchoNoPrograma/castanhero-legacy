package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "instituciones")
public class Instituciones extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long idInstitucion;

    @Size(max = 100)
    @NotNull
    private String institucion;

    @Size(max = 20)
    @NotNull
    private String sigla;

    public Long getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Long idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
