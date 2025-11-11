package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_ins_titulaciones_academicas")
public class PgInsTitulacionAcademica extends SigaUsicRevisiones{
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fk_id_institucion", referencedColumnName = "id_institucion")
    @JsonIgnore
    private PgInstituciones id_institucion;
    /*==================RELACIONES===================
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
     * ult_usuario did_usuario
     * 
     * id_rol integer DEFAULT 0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "titulacion_academica")
    private Long titulacion_academica;

    private String desc_titulo;

    private String proceso;

    private Short anio_emision;

    private String nro_serie;

    private String ip_registro;

    private String ip_modificacion;

    private String observaciones;
}
