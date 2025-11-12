package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "programas")
public class PgPrograma extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa")
    private Long idPrograma;

    @NotEmpty(message = "El nombre del programa es obligatorio")
    @Column(name = "programa")
    private String programa;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "horas_virtuales")
    private Short horasVirtuales;

    @Column(name = "horas_investigacion")
    private Short horasInvestigacion;

    @Column(name = "creditos")
    private Short creditos;

    @Column(name = "cantidad_modulos")
    private Short cantidadModulos;

    @Column(name = "disponibilidad")
    private Short disponibilidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modalidad", referencedColumnName = "id_modalidad")
    private PgPrgModalidad modalidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grado_academico", referencedColumnName = "id_grado_academico")
    private PgGradoAcademico gradoAcademico;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "programa")
    private List<PgPstPrograma> postulantesAdmitidos;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "programasPostulados")
    private List<PgPostulante> postulantes;

    public Long getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Short getHorasVirtuales() {
        return horasVirtuales;
    }

    public void setHorasVirtuales(Short horasVirtuales) {
        this.horasVirtuales = horasVirtuales;
    }

    public Short getHorasInvestigacion() {
        return horasInvestigacion;
    }

    public void setHorasInvestigacion(Short horasInvestigacion) {
        this.horasInvestigacion = horasInvestigacion;
    }

    public Short getCreditos() {
        return creditos;
    }

    public void setCreditos(Short creditos) {
        this.creditos = creditos;
    }

    public Short getCantidadModulos() {
        return cantidadModulos;
    }

    public void setCantidadModulos(Short cantidadModulos) {
        this.cantidadModulos = cantidadModulos;
    }

    public Short getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Short disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public PgPrgModalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(PgPrgModalidad modalidad) {
        this.modalidad = modalidad;
    }

    public PgGradoAcademico getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(PgGradoAcademico gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public List<PgPstPrograma> getPostulantesAdmitidos() {
        return postulantesAdmitidos;
    }

    public void setPostulantesAdmitidos(List<PgPstPrograma> postulantesAdmitidos) {
        this.postulantesAdmitidos = postulantesAdmitidos;
    }

    public List<PgPostulante> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(List<PgPostulante> postulantes) {
        this.postulantes = postulantes;
    }

    public boolean estaDisponible() {
        return disponibilidad != null && disponibilidad > 0 && estaActivo();
    }

    public String obtenerNombreCompleto() {
        return programa + (sigla != null ? " (" + sigla + ")" : "");
    }

    public Integer obtenerTotalHoras() {
        int total = 0;
        if (horasVirtuales != null) total += horasVirtuales;
        if (horasInvestigacion != null) total += horasInvestigacion;
        return total;
    }
}
