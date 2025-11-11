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
@Table(name = "pg_fcl_prg_intermedios")
public class PgFclPrgIntermedio extends SigaUsicRevisiones{
    /*================RELACIONES=================
     * id_programa
     * 
     * id_plan dtexto
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prg_intermedio")
    private Long id_prg_intermedio;
    
    private Short nivel_academico;
}
