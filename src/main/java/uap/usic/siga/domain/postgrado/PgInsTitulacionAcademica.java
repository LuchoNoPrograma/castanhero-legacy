package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_ins_titulaciones_academicas")
public class PgInsTitulacionAcademica extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "titulacion_academica")
    private Long titulacionAcademica;

    @Column(name = "desc_titulo")
    private String descTitulo;

    @Column(name = "proceso")
    private String proceso;

    @Column(name = "anio_emision")
    private Short anioEmision;

    @Column(name = "nro_serie")
    private String nroSerie;

    @Column(name = "ip_registro")
    private String ipRegistro;

    @Column(name = "ip_modificacion")
    private String ipModificacion;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_institucion", referencedColumnName = "id_institucion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgInstitucion institucion;

    public Long getTitulacionAcademica() {
        return titulacionAcademica;
    }

    public void setTitulacionAcademica(Long titulacionAcademica) {
        this.titulacionAcademica = titulacionAcademica;
    }

    public String getDescTitulo() {
        return descTitulo;
    }

    public void setDescTitulo(String descTitulo) {
        this.descTitulo = descTitulo;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public Short getAnioEmision() {
        return anioEmision;
    }

    public void setAnioEmision(Short anioEmision) {
        this.anioEmision = anioEmision;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
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

    public PgInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(PgInstitucion institucion) {
        this.institucion = institucion;
    }
}
