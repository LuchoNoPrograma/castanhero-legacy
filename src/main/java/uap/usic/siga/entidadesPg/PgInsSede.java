package uap.usic.siga.entidadesPg;
/**
 * 
 * @author Luis Morales V.
 */

import java.util.Date;

import jakarta.persistence.CascadeType;
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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_ins_sedes")
public class PgInsSede extends SigaUsicRevisiones{
    /*=================RELACIONES=================
     * id_pais
     * 
     * id_departamento
     * 
     * id_provincia
     * 
     * ult_usuario did_usuario
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_institucion", referencedColumnName = "id_institucion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PgInstituciones id_institucion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sede;

    private String sede;

    private String codigo;
    
    private String resolucion_apertura;

    private String direccion;

    private String telefono;

    private String fax;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fec_creacion;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fec_inicio_actividades;

    private String autoridad_sede;

    private String correo;
}
