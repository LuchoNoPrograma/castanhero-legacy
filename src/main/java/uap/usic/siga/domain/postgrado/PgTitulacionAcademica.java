package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_titulaciones_academicas")
public class PgTitulacionAcademica extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titulacion_academica")
    private Long idTitulacionAcademica;

    @Column(name = "desc_titulo")
    private String descTitulo;

    @Column(name = "proceso")
    private String proceso;

    @Column(name = "anio_emision")
    private Integer anioEmision;

    @Column(name = "nro_serie")
    private String nroSerie;

    @Column(name = "ip_registro")
    private String ipRegistro;

    @Column(name = "ip_modificacion")
    private String ipModificacion;

    @Column(name = "observaciones")
    private String observaciones;

    public Long getIdTitulacionAcademica() {
        return idTitulacionAcademica;
    }

    public void setIdTitulacionAcademica(Long idTitulacionAcademica) {
        this.idTitulacionAcademica = idTitulacionAcademica;
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

    public Integer getAnioEmision() {
        return anioEmision;
    }

    public void setAnioEmision(Integer anioEmision) {
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
}
