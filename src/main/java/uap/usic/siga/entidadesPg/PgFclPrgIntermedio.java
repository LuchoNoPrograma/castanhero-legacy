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
