package uap.usic.siga.entidadesPg;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class SigaUsicRevisiones implements Serializable{
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "fec_registro")
    private Date fecRegistro = new Timestamp(System.currentTimeMillis());

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "fec_modificacion")
    private Date fecModificacion = new Timestamp(System.currentTimeMillis());

    private Character estado = 'A';

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public Date getFecModificacion() {
        return fecModificacion;
    }

    public void setFecModificacion(Date fecModificacion) {
        this.fecModificacion = fecModificacion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }
    
}
