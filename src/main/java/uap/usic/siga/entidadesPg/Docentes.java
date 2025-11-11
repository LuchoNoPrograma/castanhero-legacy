package uap.usic.siga.entidadesPg;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uap.usic.siga.entidades.PrsCiExpedidos;
import uap.usic.siga.entidades.PrsEstadoCivil;
import uap.usic.siga.entidades.PrsGradosAcademicos;
import uap.usic.siga.entidades.PrsPaises;
import uap.usic.siga.entidades.PrsTiposSexos;

@Entity
@Table(name = "docentes")
public class Docentes extends SigaUsicRevisiones {
    //==============LLAVES FORANEAS===================

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "docente")
    @JsonIgnore
    private List<EjecucionesModulos> ejecucionModulo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_pais")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsPaises prsPaises;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_prs_tipo_sexo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsTiposSexos prsTiposSexos;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_prs_ci_expedido")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsCiExpedidos prsCiExpedidos;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_prs_estado_civil")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsEstadoCivil prsEstadoCivil;    

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_grado_academico")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsGradosAcademicos prsGradosAcademicos;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private Long idDocente;

    private String nombres;

    private String paterno;

    private String materno;

    @NotNull
    @Column(unique = true)
    private String ci;
        
    private String email;

    @Column(name = "ciudad_residencia")
    private String ciudadResidencia;

    private String direccion;

    private String profesion;
    
    private String celular;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_nacimiento")
    private Date fecNacimiento;

    @Column(name = "anio_titulacion")
    private Integer anioTitulacion;

    public Docentes(PrsPaises prsPaises, PrsTiposSexos prsTiposSexos, PrsCiExpedidos prsCiExpedidos,
            PrsEstadoCivil prsEstadoCivil, PrsGradosAcademicos prsGradosAcademicos, String nombres, String paterno,
            String materno, @NotNull String ci, String email, String ciudadResidencia, String direccion) {
        this.prsPaises = prsPaises;
        this.prsTiposSexos = prsTiposSexos;
        this.prsCiExpedidos = prsCiExpedidos;
        this.prsEstadoCivil = prsEstadoCivil;
        this.prsGradosAcademicos = prsGradosAcademicos;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.ci = ci;
        this.email = email;
        this.ciudadResidencia = ciudadResidencia;
        this.direccion = direccion;
    }

    public Docentes() {
    }

    public Long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Long idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public Integer getAnioTitulacion() {
        return anioTitulacion;
    }

    public void setAnioTitulacion(Integer anioTitulacion) {
        this.anioTitulacion = anioTitulacion;
    }

    public PrsPaises getPrsPaises() {
        return prsPaises;
    }

    public void setPrsPaises(PrsPaises prsPaises) {
        this.prsPaises = prsPaises;
    }

    public PrsTiposSexos getPrsTiposSexos() {
        return prsTiposSexos;
    }

    public void setPrsTiposSexos(PrsTiposSexos prsTiposSexos) {
        this.prsTiposSexos = prsTiposSexos;
    }

    public PrsCiExpedidos getPrsCiExpedidos() {
        return prsCiExpedidos;
    }

    public void setPrsCiExpedidos(PrsCiExpedidos prsCiExpedidos) {
        this.prsCiExpedidos = prsCiExpedidos;
    }

    public PrsEstadoCivil getPrsEstadoCivil() {
        return prsEstadoCivil;
    }

    public void setPrsEstadoCivil(PrsEstadoCivil prsEstadoCivil) {
        this.prsEstadoCivil = prsEstadoCivil;
    }

    public PrsGradosAcademicos getPrsGradosAcademicos() {
        return prsGradosAcademicos;
    }

    public void setPrsGradosAcademicos(PrsGradosAcademicos prsGradosAcademicos) {
        this.prsGradosAcademicos = prsGradosAcademicos;
    }

    public List<EjecucionesModulos> getEjecucionModulo() {
        return ejecucionModulo;
    }

    public void setEjecucionModulo(List<EjecucionesModulos> ejecucionModulo) {
        this.ejecucionModulo = ejecucionModulo;
    }
}
