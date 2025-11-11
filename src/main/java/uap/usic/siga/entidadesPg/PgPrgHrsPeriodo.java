package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_prg_hrs_periodo")
public class PgPrgHrsPeriodo extends SigaUsicRevisiones{
    /*======================RELACIONES=====================
     * id_programa
     * 
     * id_tipo_materia??
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hrs_periodo")
    private Long idHrsPeriodo;

    private Short periodo;

    private Short indice;
}
