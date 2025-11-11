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
@Table(name = "pg_ins_tipos_estudiantes")
public class PgTipoEstudiante extends SigaUsicRevisiones{
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fk_id_institucion", referencedColumnName = "id_institucion")
    @JsonIgnore
    private PgInstituciones pgInstituciones;
    /*==================RELACIONES=================== 
     * id_institucion
     * 
     * id_rol integer DEFAULT -
     * 
     * ult_usuario did_usuario
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_estudiante")
    private Long idTipoEstudiante;
    
    @Column(name = "tipo_estudiante")
    private String tipoEstudiante;
}
