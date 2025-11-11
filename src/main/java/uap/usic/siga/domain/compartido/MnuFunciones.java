package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entidad que representa funciones de menú asignadas.
 */
@Entity
@Table(name = "mnu_funciones")
public class MnuFunciones extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_funcion")
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_expiracion")
    @Temporal(TemporalType.DATE)
    private Date fecExpiracion;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Double valor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_tipo_funcion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuTiposFunciones mnuTiposFunciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sis_administrador", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SisAdministrador sisAdministrador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nivel_acceso", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SisNivelesAccesos sisNivelesAccesos;

    public MnuFunciones() {
    }

    public MnuFunciones(Date fecExpiracion, Double valor, Personas personas, MnuTiposFunciones mnuTiposFunciones, SisAdministrador sisAdministrador, SisNivelesAccesos sisNivelesAccesos) {
        this.fecExpiracion = fecExpiracion;
        this.valor = valor;
        this.personas = personas;
        this.mnuTiposFunciones = mnuTiposFunciones;
        this.sisAdministrador = sisAdministrador;
        this.sisNivelesAccesos = sisNivelesAccesos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecExpiracion() {
        return fecExpiracion;
    }

    public void setFecExpiracion(Date fecExpiracion) {
        this.fecExpiracion = fecExpiracion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public MnuTiposFunciones getMnuTiposFunciones() {
        return mnuTiposFunciones;
    }

    public void setMnuTiposFunciones(MnuTiposFunciones mnuTiposFunciones) {
        this.mnuTiposFunciones = mnuTiposFunciones;
    }

    public SisAdministrador getSisAdministrador() {
        return sisAdministrador;
    }

    public void setSisAdministrador(SisAdministrador sisAdministrador) {
        this.sisAdministrador = sisAdministrador;
    }

    public SisNivelesAccesos getSisNivelesAccesos() {
        return sisNivelesAccesos;
    }

    public void setSisNivelesAccesos(SisNivelesAccesos sisNivelesAccesos) {
        this.sisNivelesAccesos = sisNivelesAccesos;
    }
}
