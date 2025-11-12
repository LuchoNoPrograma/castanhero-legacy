package uap.usic.siga.entidadesPg;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_prg_detalles")
public class PgPrgDetalle extends SigaUsicRevisiones{
    /*==================RELACIONES====================
     * id_programa
     * 
     * id_plan
     * 
     * id_tipo_grado
     * 
     * id_tipo_programacion
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     * 
     * id_tipo_plan DEFAULT 0
     * 
     * _id_estado dtexto2
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id_detalle;    

    private Short gestion;  //Por el momento heredan de SigaUsicRevisiones

    private Short periodo;  //Por el momento heredan de SigaUsicRevisiones

    private Short max_niveles;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fec_inicio;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fec_final;

    private Short max_materias_teoricas;

    private Short max_materias_laboratorios;

    private double costo_materia_teorica = 0.0; //numeric(6,2) DEFAULT 0.0

    private double costo_materia_laboratorio = 0.0; //numeric(6,2) DEFAULT 0.0
}
