package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "facultades")
public class Facultades  extends SigaUsicRevisiones{

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_universidad", referencedColumnName = "id_universidad")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Universidades universidades;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsSedes sedes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facultad")
    private Long idFacultad;

    @NotNull
    @Column(name = "facultad")
    private String facultad;

    @Column(name = "codigo")
    private String codigo;

    public Universidades getUniversidades() {
        return universidades;
    }

    public void setUniversidades(Universidades universidades) {
        this.universidades = universidades;
    }

    public InsSedes getSedes() {
        return sedes;
    }

    public void setSedes(InsSedes sedes) {
        this.sedes = sedes;
    }

    public Long getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Long idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
