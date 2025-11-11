package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pg_titulaciones_academicas")
public class PgTitulacionAcademica extends SigaUsicRevisiones{
    /*=======================RELACIONES====================== 
     * id_persona
     * 
     * id_tipo_titulo
     * 
     * id_institucion
     * 
     * id_grado_academico
     * 
     * id_modalidad_graduacion
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titulacion_academica")
    private Long id_titulacion_academica;

    private String desc_titulo;

    private String proceso;

    private Integer anio_emision;

    private String nro_serie;

    private String ip_registro;

    private String ip_modificacion;

    private String observaciones;

    public Long getId_titulacion_academica() {
        return id_titulacion_academica;
    }

    public void setId_titulacion_academica(Long id_titulacion_academica) {
        this.id_titulacion_academica = id_titulacion_academica;
    }

    public String getDesc_titulo() {
        return desc_titulo;
    }

    public void setDesc_titulo(String desc_titulo) {
        this.desc_titulo = desc_titulo;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public Integer getAnio_emision() {
        return anio_emision;
    }

    public void setAnio_emision(Integer anio_emision) {
        this.anio_emision = anio_emision;
    }

    public String getNro_serie() {
        return nro_serie;
    }

    public void setNro_serie(String nro_serie) {
        this.nro_serie = nro_serie;
    }

    public String getIp_registro() {
        return ip_registro;
    }

    public void setIp_registro(String ip_registro) {
        this.ip_registro = ip_registro;
    }

    public String getIp_modificacion() {
        return ip_modificacion;
    }

    public void setIp_modificacion(String ip_modificacion) {
        this.ip_modificacion = ip_modificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
}
