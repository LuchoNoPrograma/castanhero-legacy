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
