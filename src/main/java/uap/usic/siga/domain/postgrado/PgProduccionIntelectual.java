package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "producciones_intelectuales")
public class PgProduccionIntelectual extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produccion_intelectual")
    private Long idProduccionIntelectual;

    @Column(name = "produccion_intelectual")
    private String produccionIntelectual;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "editorial")
    private String editorial;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "ip_registro")
    private String ipRegistro;

    @Column(name = "ip_modificacion")
    private String ipModificacion;

    @Column(name = "observaciones")
    private String observaciones;

    public Long getIdProduccionIntelectual() {
        return idProduccionIntelectual;
    }

    public void setIdProduccionIntelectual(Long idProduccionIntelectual) {
        this.idProduccionIntelectual = idProduccionIntelectual;
    }

    public String getProduccionIntelectual() {
        return produccionIntelectual;
    }

    public void setProduccionIntelectual(String produccionIntelectual) {
        this.produccionIntelectual = produccionIntelectual;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
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
