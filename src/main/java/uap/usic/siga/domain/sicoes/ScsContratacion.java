package uap.usic.siga.domain.sicoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import uap.usic.siga.entidades.CjaTiposGastos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SigaUsicGestiones;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "scs_contrataciones")
public class ScsContratacion extends SigaUsicGestiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_contratacion")
    private Long idScsContratacion;

    @NotNull
    @Column(name = "scs_codigo_contratacion")
    private String scsCodigoContratacion;

    @NotNull
    @Column(name = "scs_cargo_servicio")
    private String scsCargoServicio;

    @NotNull
    @Column(name = "monto_mensual")
    private Double montoMensual;

    @NotNull
    @Column(name = "monto_total")
    private Double montoTotal;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_inicio")
    @Temporal(TemporalType.DATE)
    private Date fecInicio;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_final")
    @Temporal(TemporalType.DATE)
    private Date fecFinal;

    @NotNull
    @Column(name = "fojas")
    private Integer fojas;

    @NotNull
    @Column(name = "dias")
    private Integer dias;

    @Column(name = "modal")
    private Integer modal;

    @NotNull
    @Column(name = "nro_contratacion")
    private Integer nroContratacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_scs_archivo_adjunto", nullable = false)
    @JsonIgnore
    private ScsArchivoAdjunto archivoAdjunto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_scs_formulario", nullable = false)
    @JsonIgnore
    private ScsFormulario formulario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_scs_proyecto", nullable = false)
    @JsonIgnore
    private ScsProyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_scs_modalidad", nullable = false)
    @JsonIgnore
    private ScsModalidad modalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cja_tipo_gasto", nullable = false)
    @JsonIgnore
    private CjaTiposGastos tipoGasto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_scs_prs_contratado", nullable = false)
    @JsonIgnore
    private ScsPrsContratado prsContratado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    @JsonIgnore
    private Personas persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_scs_tipo_contrato", nullable = false)
    @JsonIgnore
    private ScsTipoContrato tipoContrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_scs_boleta_respaldatoria", nullable = false)
    @JsonIgnore
    private ScsBoletaRespaldatoria boletaRespaldatoria;

    @Transient
    private MultipartFile file;

    @Transient
    private String nombreArchivo;

    public Long getIdScsContratacion() {
        return idScsContratacion;
    }

    public void setIdScsContratacion(Long idScsContratacion) {
        this.idScsContratacion = idScsContratacion;
    }

    public String getScsCodigoContratacion() {
        return scsCodigoContratacion;
    }

    public void setScsCodigoContratacion(String scsCodigoContratacion) {
        this.scsCodigoContratacion = scsCodigoContratacion;
    }

    public String getScsCargoServicio() {
        return scsCargoServicio;
    }

    public void setScsCargoServicio(String scsCargoServicio) {
        this.scsCargoServicio = scsCargoServicio;
    }

    public Double getMontoMensual() {
        return montoMensual;
    }

    public void setMontoMensual(Double montoMensual) {
        this.montoMensual = montoMensual;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
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

    public Integer getFojas() {
        return fojas;
    }

    public void setFojas(Integer fojas) {
        this.fojas = fojas;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getModal() {
        return modal;
    }

    public void setModal(Integer modal) {
        this.modal = modal;
    }

    public Integer getNroContratacion() {
        return nroContratacion;
    }

    public void setNroContratacion(Integer nroContratacion) {
        this.nroContratacion = nroContratacion;
    }

    public ScsArchivoAdjunto getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(ScsArchivoAdjunto archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public ScsFormulario getFormulario() {
        return formulario;
    }

    public void setFormulario(ScsFormulario formulario) {
        this.formulario = formulario;
    }

    public ScsProyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(ScsProyecto proyecto) {
        this.proyecto = proyecto;
    }

    public ScsModalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(ScsModalidad modalidad) {
        this.modalidad = modalidad;
    }

    public CjaTiposGastos getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(CjaTiposGastos tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public ScsPrsContratado getPrsContratado() {
        return prsContratado;
    }

    public void setPrsContratado(ScsPrsContratado prsContratado) {
        this.prsContratado = prsContratado;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public ScsTipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(ScsTipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public ScsBoletaRespaldatoria getBoletaRespaldatoria() {
        return boletaRespaldatoria;
    }

    public void setBoletaRespaldatoria(ScsBoletaRespaldatoria boletaRespaldatoria) {
        this.boletaRespaldatoria = boletaRespaldatoria;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
