package uap.usic.siga.entidadesPg;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
