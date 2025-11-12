package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "ubicaciones_organicas")
public class PgUbicacionOrganica extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion_organica")
    private Long idUbicacionOrganica;

    @Column(name = "codigo_ubicacion_organica")
    private String codigoUbicacionOrganica;

    @Column(name = "ubicacion_organica")
    private String ubicacionOrganica;

    @Column(name = "nivel")
    private Integer nivel;

    @Column(name = "correlativo")
    private Integer correlativo;

    public Long getIdUbicacionOrganica() {
        return idUbicacionOrganica;
    }

    public void setIdUbicacionOrganica(Long idUbicacionOrganica) {
        this.idUbicacionOrganica = idUbicacionOrganica;
    }

    public String getCodigoUbicacionOrganica() {
        return codigoUbicacionOrganica;
    }

    public void setCodigoUbicacionOrganica(String codigoUbicacionOrganica) {
        this.codigoUbicacionOrganica = codigoUbicacionOrganica;
    }

    public String getUbicacionOrganica() {
        return ubicacionOrganica;
    }

    public void setUbicacionOrganica(String ubicacionOrganica) {
        this.ubicacionOrganica = ubicacionOrganica;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }
}
