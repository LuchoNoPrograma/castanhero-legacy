package uap.usic.siga.entidadesPg;
/**
 * 
 * @author Luis Morales V.
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "facultades")
public class PgFacultades extends SigaUsicRevisiones{
    /*===============RELACIONES===================
    id_sede dentero

    id_sede

    id_rol integer DEFAULT 0

    //ult_usuario did_usuario
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facultad")
    private Long idFacultad;

    private String facultad;

    private String codigo;

    private String decano;

    private String direccion;

    private String telefono;

    private String fax;

    private String correo;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fec_creacion")
    private Date fecCreacion;

    public Long getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Long idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDecano() {
        return decano;
    }

    public void setDecano(String decano) {
        this.decano = decano;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    
}
