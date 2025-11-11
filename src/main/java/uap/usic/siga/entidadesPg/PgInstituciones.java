package uap.usic.siga.entidadesPg;
/**
 * @author Luis Morales V
 */

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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "instituciones")
public class PgInstituciones extends SigaUsicRevisiones{
    /*===================RELACIONES====================
     * id_sede_central dentero
     * 
     * id_persona dentero
     * 
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long id_institucion;

    private String institucion;

    private String sigla;

    private String actividad;

    private String instrumento_apertura;

    private String telefono;

    private String fax;

    private String url;

    private String correo;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)    //Solo se grabara la fecha, mas no la hora
    private Date fec_creacion;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fec_inicio_actividades;    //Ver como anhadir las fechas desde los formularios

    private String representante_legal;

    private String plan_estrategico;

    private String estatuto_organico;

    private String reglamento_investigacion;

    private String centro_investigacion_central;

    private String logo;
}
