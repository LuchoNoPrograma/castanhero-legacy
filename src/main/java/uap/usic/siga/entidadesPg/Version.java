package uap.usic.siga.entidadesPg;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "versiones")
public class Version extends SigaUsicRevisiones{
    /*==============RELACIONES==============
     * id_programa
     * 
     * id_plan
     * 
     * ult_usuario did_usuario
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_version")
    private Long id_version;

    private String version;

    private String descripcion;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Temporal(TemporalType.DATE)
    private Date fecha_ini;
}
