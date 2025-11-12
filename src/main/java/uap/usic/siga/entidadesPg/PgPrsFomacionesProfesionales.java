package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "pg_prs_formaciones_profesionales")
public class PgPrsFomacionesProfesionales extends SigaUsicRevisiones{
    /*=================RELACIONES================
     * id_titulacion_academica dentero
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_persona", referencedColumnName = "id_postulante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Postulantes postulante;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formacion_profesional")
    private Long idFormacionProfesional;

    private String profesion;

    @Column(name = "grado_profesion")
    private String gradoProfesion;

    @Column(name = "ip_registro")
    private String ipRegistro;

    @Column(name = "id_modificacion")
    private String ipModificacion;

    private String observaciones;

    public Long getIdFormacionProfesional() {
        return idFormacionProfesional;
    }

    public void setIdFormacionProfesional(Long idFormacionProfesional) {
        this.idFormacionProfesional = idFormacionProfesional;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getGradoProfesion() {
        return gradoProfesion;
    }

    public void setGradoProfesion(String gradoProfesion) {
        this.gradoProfesion = gradoProfesion;
    }

    public String getIpRegistro() {
        return ipRegistro;
    }

    public void setIpRegistro(String ipRegistro) {
        this.ipRegistro = ipRegistro;
    }

    public String getIpModificacion() {
        return ipModificacion;
    }

    public void setIpModificacion(String ipModificacion) {
        this.ipModificacion = ipModificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Postulantes getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulantes postulante) {
        this.postulante = postulante;
    }
    
        
}
