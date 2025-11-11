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
@Table(name = "pg_trn_perfiles_procesos")
public class PgTrnPerfilProceso extends SigaUsicRevisiones {
    /*=================RELACIONES=================
     * id_perfil
     * 
     * id_proceso
     * 
     * id_tipo_perfil
     * 
     * ult_usuario did_usuario
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_proceso")
    private Long id_perfil_proceso;
}
