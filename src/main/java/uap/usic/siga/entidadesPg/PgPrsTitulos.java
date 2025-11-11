package uap.usic.siga.entidadesPg;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "titulos")
public class PgPrsTitulos extends SigaUsicRevisiones{
    /*======================RELACIONES=====================
     * id_encargada dentero DEFAULT 515
     * 
     * id_firma_rector_ dentero DEFAULT 4328
     * 
     * id_firma_vicerector dentero DEFAULT 7799
     * 
     * id_tipo_niveles
     * 
     * ult_usuario did_usuario DEFAULT 1
     */

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_programa", referencedColumnName = "id_programa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Programas programa;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_titulo")
    private Long idTitulo;

    @Column(name = "numero_titulo")
    private Integer numeroTitulo;

    @Column(name = "serie_titulo")
    private String serieTitulo;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "fecha_entrega")
    private Date fechaEntrega;


    public Programas getPrograma() {
        return programa;
    }

    public void setPrograma(Programas programa) {
        this.programa = programa;
    }

    public Long getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(Long idTitulo) {
        this.idTitulo = idTitulo;
    }

    public Integer getNumeroTitulo() {
        return numeroTitulo;
    }

    public void setNumeroTitulo(Integer numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }

    public String getSerieTitulo() {
        return serieTitulo;
    }

    public void setSerieTitulo(String serieTitulo) {
        this.serieTitulo = serieTitulo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    
    
}
