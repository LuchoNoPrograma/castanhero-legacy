package uap.usic.siga.domain.congreso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import uap.usic.siga.domain.electoral.EscFrentes;
import uap.usic.siga.entidades.FclCarreras;
import uap.usic.siga.entidades.FclEstamentos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SigaUsicGestiones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "cng_cogresistas")
public class CngCongresistas extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cng_congresista")
    private Long idCngCongresista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_congresista")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngTiposCongresistas cngTiposCongresistas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_congreso_uap")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CongresoUap congresoUap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_carrera")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FclCarreras fclCarreras;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_frente")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscFrentes escFrentes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estamento")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FclEstamentos fclEstamentos;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "foto")
    private String foto;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecInicio;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_final", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecFinal;

    public Long getIdCngCongresista() {
        return idCngCongresista;
    }

    public void setIdCngCongresista(Long idCngCongresista) {
        this.idCngCongresista = idCngCongresista;
    }

    public CngTiposCongresistas getCngTiposCongresistas() {
        return cngTiposCongresistas;
    }

    public void setCngTiposCongresistas(CngTiposCongresistas cngTiposCongresistas) {
        this.cngTiposCongresistas = cngTiposCongresistas;
    }

    public CongresoUap getCongresoUap() {
        return congresoUap;
    }

    public void setCongresoUap(CongresoUap congresoUap) {
        this.congresoUap = congresoUap;
    }

    public FclCarreras getFclCarreras() {
        return fclCarreras;
    }

    public void setFclCarreras(FclCarreras fclCarreras) {
        this.fclCarreras = fclCarreras;
    }

    public EscFrentes getEscFrentes() {
        return escFrentes;
    }

    public void setEscFrentes(EscFrentes escFrentes) {
        this.escFrentes = escFrentes;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public FclEstamentos getFclEstamentos() {
        return fclEstamentos;
    }

    public void setFclEstamentos(FclEstamentos fclEstamentos) {
        this.fclEstamentos = fclEstamentos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecFinal() {
        return fecFinal;
    }

    public void setFecFinal(Date fecFinal) {
        this.fecFinal = fecFinal;
    }
}
