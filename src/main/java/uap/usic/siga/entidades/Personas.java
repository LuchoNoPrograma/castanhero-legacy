package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "personas")
public class Personas extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pais", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsPaises prsPaises;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prs_tipo_sexo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsTiposSexos prsTiposSexos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prs_ci_expedido", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsCiExpedidos prsCiExpedidos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grado_academico", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsGradosAcademicos prsGradosAcademicos;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prs_estado_civil", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsEstadoCivil prsEstadoCivil;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @NotNull
    @Column(name = "nombres")
    private String nombres;

    @Column(name = "paterno")
    private String paterno;

    @Column(name = "materno")
    private String materno;

    @NotNull
    @Column(name = "ci")
    private String ci;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_nacimiento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecNacimiento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;

    @NotNull
    @Column(name = "email")
    private String email;

	public Personas(PrsPaises prsPaises, PrsTiposSexos prsTiposSexos, PrsCiExpedidos prsCiExpedidos,
			PrsGradosAcademicos prsGradosAcademicos, PrsEstadoCivil prsEstadoCivil,
			@NotNull String nombres, String paterno, String materno, @NotNull String ci, Date fecNacimiento,
			String direccion, String telefono, String tipoSanguineo) {
		this.prsPaises = prsPaises;
		this.prsTiposSexos = prsTiposSexos;
		this.prsCiExpedidos = prsCiExpedidos;
		this.prsGradosAcademicos = prsGradosAcademicos;
		this.prsEstadoCivil = prsEstadoCivil;
		this.nombres = nombres;
		this.paterno = paterno;
		this.materno = materno;
		this.ci = ci;
		this.fecNacimiento = fecNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tipoSanguineo = tipoSanguineo;
	}	
	
	public Personas(@NotNull String nombres, String paterno, String materno, @NotNull String ci, Date fecNacimiento,
			String direccion, String telefono, String tipoSanguineo) {
		this.nombres = nombres;
		this.paterno = paterno;
		this.materno = materno;
		this.ci = ci;
		this.fecNacimiento = fecNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tipoSanguineo = tipoSanguineo;
	}

	public Personas() {
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

	public PrsGradosAcademicos getPrsGradosAcademicos() {
		return prsGradosAcademicos;
	}

	public void setPrsGradosAcademicos(PrsGradosAcademicos prsGradosAcademicos) {
		this.prsGradosAcademicos = prsGradosAcademicos;
	}

	public PrsEstadoCivil getPrsEstadoCivil() {
		return prsEstadoCivil;
	}

	public void setPrsEstadoCivil(PrsEstadoCivil prsEstadoCivil) {
		this.prsEstadoCivil = prsEstadoCivil;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
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

	public Date getFecNacimiento() {
		return fecNacimiento;
	}

	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
