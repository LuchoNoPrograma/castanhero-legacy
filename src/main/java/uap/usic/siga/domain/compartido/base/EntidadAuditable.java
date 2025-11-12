package uap.usic.siga.domain.compartido.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class EntidadAuditable implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "_registro", updatable = false)
    @CreatedDate
    private Date registro = new Timestamp(System.currentTimeMillis());

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "_modificacion")
    @LastModifiedDate
    private Date modificacion = new Timestamp(System.currentTimeMillis());

    @Column(name = "id_estado", length = 2)
    private String idEstado = "A";

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public Date getModificacion() {
        return modificacion;
    }

    public void setModificacion(Date modificacion) {
        this.modificacion = modificacion;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public boolean estaActivo() {
        return "A".equals(this.idEstado);
    }

    public void activar() {
        this.idEstado = "A";
    }

    public void desactivar() {
        this.idEstado = "I";
    }
}
