package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pg_prg_hrs_grupos")
public class PgPrgHrsGrupo extends SigaUsicGestiones{
    /*==============RELACIONES===================
     * id_programa
     * 
     * id_plan dtexto2
     * 
     * id_tipo_grado
     * 
     * id_tipo_evaluacion
     * 
     * id_materia
     * 
     * id_grupo
     * 
     * id_dia
     * 
     * id_hora
     * 
     * id_aula???
     * 
     * id_modelo_ahorro
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hrs_grupo")
    private Long idHrsGrupo;

    private Short gestion;

    public Long getIdHrsGrupo() {
        return idHrsGrupo;
    }

    public void setIdHrsGrupo(Long idHrsGrupo) {
        this.idHrsGrupo = idHrsGrupo;
    }

    public Short getGestion() {
        return gestion;
    }

    public void setGestion(Short gestion) {
        this.gestion = gestion;
    }

    
}
