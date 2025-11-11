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
@Table(name = "pg_trn_perfiles_materias")
public class PgTrnPerfilMateria extends SigaUsicRevisiones{
    /*=================RELACIONES=================== 
     * id_perfil
     * 
     * id_programa
     * 
     * id_plan dtexto
     * 
     * id_materia
     * 
     * id_tipo_evaluacion dentero DEFAULT 3
     * 
     * ult_usuario did_usuario 
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_materia")
    private Long id_perfil_materia;

    private Short periodo;//Tendrian que extender de SigaUsicGestiones

    private Short gestion;//Tendrian que extender de SigaUsicGestiones  

    //costo dmoneda2 -> Observar
}
