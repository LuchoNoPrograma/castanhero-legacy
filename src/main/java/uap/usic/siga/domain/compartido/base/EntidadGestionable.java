package uap.usic.siga.domain.compartido.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Clase base para entidades que requieren seguimiento de gestión y periodo académico.
 * Incluye auditoría automática de registro y modificación.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class EntidadGestionable implements Serializable {

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

    @Column(name = "gestion")
    private Integer gestion;

    @Column(name = "periodo")
    private Integer periodo;

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

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
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
