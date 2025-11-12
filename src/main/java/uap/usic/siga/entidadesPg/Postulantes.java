package uap.usic.siga.entidadesPg;
/**
 * 
 * @author Luis Morales V.
 */

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import uap.usic.siga.entidades.PrsCiExpedidos;
import uap.usic.siga.entidades.PrsEstadoCivil;
import uap.usic.siga.entidades.PrsGradosAcademicos;
import uap.usic.siga.entidades.PrsPaises;
import uap.usic.siga.entidades.PrsTiposSexos;


//POSTULANTE BASICO
@Entity
@Table(name = "postulantes")
public class Postulantes extends SigaUsicRevisiones{
    
    /*==================RELACIONES====================
     * ult_usuario did_usuario
     * 
     * id_grado_academico
     * 
     * ult_usuario did_usuario
     * 
     * _id_persona dentero2 DEFAULT 0
     * 
     * _id_estado character(1)
    //  */
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pais")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsPaises prsPaises;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prs_tipo_sexo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsTiposSexos prsTiposSexos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prs_ci_expedido")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsCiExpedidos prsCiExpedidos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prs_estado_civil")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsEstadoCivil prsEstadoCivil;    

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grado_academico")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsGradosAcademicos prsGradosAcademicos;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "programas")
    private List<PgPstProgramas> programasAdmitidos;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "postulantes_programas",
    joinColumns = @JoinColumn(name = "id_postulante"),
    inverseJoinColumns = @JoinColumn(name = "id_programa"))
    @JsonIgnore
    private List<Programas> programasPostulados;
    /* 
     * 
     * CascadeType.ALL contempla todas las operaciones en cascada,
     * Es necesario para persistir las entidades hijas sin necesidad
     * de estar persistiendo cada entidad en controlador
    */
    //========================================================================
    //-------------------Llave persona-------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postulante")// id_pst_persona
    private Long idPostulante;
    
    //-----------------Atributos persona------------------------
    @NotNull
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

    public Postulantes() {
    }
    
    public Postulantes(@NotNull String nombres, String paterno, String materno, @NotNull String ci, String email,
            String ciudadResidencia, String direccion, String profesion, String celular, Date fecNacimiento,
            Integer anioTitulacion) {
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.ci = ci;
        this.email = email;
        this.ciudadResidencia = ciudadResidencia;
        this.direccion = direccion;
        this.profesion = profesion;
        this.celular = celular;
        this.fecNacimiento = fecNacimiento;
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

    public List<PgPstProgramas> getProgramasAdmitidos() {
        return programasAdmitidos;
    }

    public void setProgramasAdmitidos(List<PgPstProgramas> programasAdmitidos) {
        this.programasAdmitidos = programasAdmitidos;
    }

    public List<Programas> getProgramasPostulados() {
        return programasPostulados;
    }

    public void setProgramasPostulados(List<Programas> programasPostulados) {
        this.programasPostulados = programasPostulados;
    }

    public Long getIdPostulante() {
        return idPostulante;
    }

    public void setIdPostulante(Long idPostulante) {
        this.idPostulante = idPostulante;
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


    @Override
    public String toString() {
        return "Postulantes [nombres=" + nombres + ", paterno=" + paterno + ", materno=" + materno + ", ci=" + ci
                + ", email=" + email + ", ciudadResidencia=" + ciudadResidencia + ", direccion=" + direccion
                + ", profesion=" + profesion + ", celular=" + celular + ", fecNacimiento=" + fecNacimiento
                + ", anioTitulacion=" + anioTitulacion + "]";
    }
    
}
