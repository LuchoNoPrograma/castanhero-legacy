package uap.usic.siga.entidadesPg;

import jakarta.persistence.FetchType;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "programas")
public class Programas extends SigaUsicRevisiones{

    //----------------Llaves foraneas-----------------

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modalidad", referencedColumnName = "id_modalidad")
    private PgPrgModalidad modalidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grado_academico", referencedColumnName = "id_grado_academico")
    private GradoAcademico grado;
    //===========MUCHOS PROGRAMAS A MUCHAS POSTULANTES=============

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "programas")
    private List<PgPstProgramas> postulantesAdmitidos;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL , mappedBy = "programasPostulados")
    private List<Postulantes> postulantes;
    //------------------------------------------------
    ///------------------Atributos--------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa")
    private Long idPrograma;

    @NotEmpty(message = "EL Nombre del Programa es  Obligatorio")
    private String programa;
    private Short horasVirtuales;
    private Short horasInvestigacion;
    private Short creditos;
    private Short cantidadModulos;
    private Short disponibilidad;
    private String sigla;
    //------------------------------------------------
    //-------------Getters and Setters----------------
    public PgPrgModalidad getModalidad() {
        return modalidad;
    }
    public void setModalidad(PgPrgModalidad modalidad) {
        this.modalidad = modalidad;
    }
    public GradoAcademico getGrado() {
        return grado;
    }
    public void setGrado(GradoAcademico grado) {
        this.grado = grado;
    }
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
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public Short getDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponibilidad(Short disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public List<PgPstProgramas> getPostulantesAdmitidos() {
        return postulantesAdmitidos;
    }
    public void setPostulantesAdmitidos(List<PgPstProgramas> postulantesAdmitidos) {
        this.postulantesAdmitidos = postulantesAdmitidos;
    }
    public List<Postulantes> getPostulantes() {
        return postulantes;
    }
    public void setPostulantes(List<Postulantes> postulantes) {
        this.postulantes = postulantes;
    }
    
  
}