package uap.usic.siga.domain.congreso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.entidades.SigaUsicGestiones;
import uap.usic.siga.entidades.Universidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "congreso_uap")
public class CongresoUap extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_congreso_uap")
    private Long idCongresoUap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_universidad")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Universidades universidades;

    @NotNull
    @Column(name = "congreso", nullable = false)
    private String congreso;

    @Column(name = "codigo")
    private String codigo;

    public Long getIdCongresoUap() {
        return idCongresoUap;
    }

    public void setIdCongresoUap(Long idCongresoUap) {
        this.idCongresoUap = idCongresoUap;
    }

    public Universidades getUniversidades() {
        return universidades;
    }

    public void setUniversidades(Universidades universidades) {
        this.universidades = universidades;
    }

    public String getCongreso() {
        return congreso;
    }

    public void setCongreso(String congreso) {
        this.congreso = congreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
