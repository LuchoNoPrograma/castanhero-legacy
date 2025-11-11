package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "instituciones")
public class PgInstitucion extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long idInstitucion;

    @Column(name = "institucion")
    private String institucion;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "actividad")
    private String actividad;

    @Column(name = "instrumento_apertura")
    private String instrumentoApertura;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fax")
    private String fax;

    @Column(name = "url")
    private String url;

    @Column(name = "correo")
    private String correo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fec_creacion")
    private Date fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fec_inicio_actividades")
    private Date fechaInicioActividades;

    @Column(name = "representante_legal")
    private String representanteLegal;

    @Column(name = "plan_estrategico")
    private String planEstrategico;

    @Column(name = "estatuto_organico")
    private String estatutoOrganico;

    @Column(name = "reglamento_investigacion")
    private String reglamentoInvestigacion;

    @Column(name = "centro_investigacion_central")
    private String centroInvestigacionCentral;

    @Column(name = "logo")
    private String logo;

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

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getInstrumentoApertura() {
        return instrumentoApertura;
    }

    public void setInstrumentoApertura(String instrumentoApertura) {
        this.instrumentoApertura = instrumentoApertura;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaInicioActividades() {
        return fechaInicioActividades;
    }

    public void setFechaInicioActividades(Date fechaInicioActividades) {
        this.fechaInicioActividades = fechaInicioActividades;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getPlanEstrategico() {
        return planEstrategico;
    }

    public void setPlanEstrategico(String planEstrategico) {
        this.planEstrategico = planEstrategico;
    }

    public String getEstatutoOrganico() {
        return estatutoOrganico;
    }

    public void setEstatutoOrganico(String estatutoOrganico) {
        this.estatutoOrganico = estatutoOrganico;
    }

    public String getReglamentoInvestigacion() {
        return reglamentoInvestigacion;
    }

    public void setReglamentoInvestigacion(String reglamentoInvestigacion) {
        this.reglamentoInvestigacion = reglamentoInvestigacion;
    }

    public String getCentroInvestigacionCentral() {
        return centroInvestigacionCentral;
    }

    public void setCentroInvestigacionCentral(String centroInvestigacionCentral) {
        this.centroInvestigacionCentral = centroInvestigacionCentral;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String obtenerNombreCompleto() {
        return institucion + (sigla != null ? " (" + sigla + ")" : "");
    }
}
