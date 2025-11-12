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
@Table(name = "pg_trn_perfiles")
public class PgTrnPerfil extends SigaUsicRevisiones{
    /*=============RELACIONES================
     * id_tipo_grado
     * 
     * ult_usuario did_usuario
     * 
     * id_tipo_cuenta dentero DEFAULT 1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long id_perfil;

    private String perfil;

    private String codigo;

    private Integer correlativo;
}
