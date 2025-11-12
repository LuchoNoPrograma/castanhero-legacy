package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "docentes")
public class PgDocente extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private Long idDocente;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "paterno")
    private String paterno;

    @Column(name = "materno")
    private String materno;

    @NotNull
    @Column(name = "ci", unique = true)
    private String ci;

    @Column(name = "email")
    private String email;

    @Column(name = "ciudad_residencia")
    private String ciudadResidencia;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "profesion")
    private String profesion;

    @Column(name = "celular")
    private String celular;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "anio_titulacion")
    private Integer anioTitulacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "docente")
    @JsonIgnore
    private List<PgEjecucionModulo> ejecucionesModulo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_pais")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPrsPais pais;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_prs_tipo_sexo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPrsTipoSexo tipoSexo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_prs_ci_expedido")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPrsExpedido ciExpedido;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_prs_estado_civil")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPrsEstadoCivil estadoCivil;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_grado_academico")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgPrsGradoAcademico gradoAcademico;

    public PgDocente() {
    }

    public PgDocente(PgPrsPais pais, PgPrsTipoSexo tipoSexo, PgPrsExpedido ciExpedido,
                     PgPrsEstadoCivil estadoCivil, PgPrsGradoAcademico gradoAcademico,
                     String nombres, String paterno, String materno, @NotNull String ci,
                     String email, String ciudadResidencia, String direccion) {
        this.pais = pais;
        this.tipoSexo = tipoSexo;
        this.ciExpedido = ciExpedido;
        this.estadoCivil = estadoCivil;
        this.gradoAcademico = gradoAcademico;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.ci = ci;
        this.email = email;
        this.ciudadResidencia = ciudadResidencia;
        this.direccion = direccion;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getAnioTitulacion() {
        return anioTitulacion;
    }

    public void setAnioTitulacion(Integer anioTitulacion) {
        this.anioTitulacion = anioTitulacion;
    }

    public List<PgEjecucionModulo> getEjecucionesModulo() {
        return ejecucionesModulo;
    }

    public void setEjecucionesModulo(List<PgEjecucionModulo> ejecucionesModulo) {
        this.ejecucionesModulo = ejecucionesModulo;
    }

    public PgPrsPais getPais() {
        return pais;
    }

    public void setPais(PgPrsPais pais) {
        this.pais = pais;
    }

    public PgPrsTipoSexo getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(PgPrsTipoSexo tipoSexo) {
        this.tipoSexo = tipoSexo;
    }

    public PgPrsExpedido getCiExpedido() {
        return ciExpedido;
    }

    public void setCiExpedido(PgPrsExpedido ciExpedido) {
        this.ciExpedido = ciExpedido;
    }

    public PgPrsEstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(PgPrsEstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public PgPrsGradoAcademico getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(PgPrsGradoAcademico gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String obtenerNombreCompleto() {
        StringBuilder nombre = new StringBuilder();
        if (nombres != null) nombre.append(nombres);
        if (paterno != null && !paterno.isEmpty()) {
            nombre.append(" ").append(paterno);
        }
        if (materno != null && !materno.isEmpty()) {
            nombre.append(" ").append(materno);
        }
        return nombre.toString();
    }

    public String obtenerCiCompleto() {
        return ci + (ciExpedido != null ? " " + ciExpedido.getExpedido() : "");
    }

    public boolean tieneEmail() {
        return email != null && !email.isEmpty();
    }

    public Integer calcularEdad() {
        if (fechaNacimiento == null) return null;
        Date hoy = new Date();
        long diff = hoy.getTime() - fechaNacimiento.getTime();
        return (int) (diff / (1000L * 60 * 60 * 24 * 365));
    }
}
