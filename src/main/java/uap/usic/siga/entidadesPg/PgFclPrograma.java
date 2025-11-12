package uap.usic.siga.entidadesPg;
/**
 * 
 * @author Luis Morales V.
 */

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_fcl_programas")
public class PgFclPrograma extends SigaUsicRevisiones{
    /*==================RELACIONES==================
     * id_institucion
     * 
     * id_sede
     * 
     * id_campus
     * 
     * id_area_conocimiento
     * 
     * id_tipo_ensenyanza
     * 
     * id_periodo
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     * 
     * _id_programa
     * 
     * id_tipo_nivel
     * 
     * _id_estado character(1)
     * 
     * id_persona
     */
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private PgFacultades facultad;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa")
    private Long id_programa;

    private String programa;

    private Short nro_estudiante;//DEFAULT 0

    private Short nota_aprobacion;//DEFAULT 51

    private Short nota_admision;//DEFAULT 51

    private String direccion;

    private String telefono;

    private String correo;

    private String resolucion_hcu;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fec_creacion;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fec_inicio;
    
    private String mision;

    private String objetivos;

    private Short duracion;

    private String turno;
    
    private String codigo_programa;
}
