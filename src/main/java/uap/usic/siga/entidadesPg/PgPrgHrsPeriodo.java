package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
