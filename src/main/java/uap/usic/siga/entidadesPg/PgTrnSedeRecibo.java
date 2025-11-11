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
@Table(name = "pg_trn_sedes_recibos")
public class PgTrnSedeRecibo extends SigaUsicRevisiones{
    /*=============RELACIONES===============
     * id_sede
     * 
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede_recibo")
    private Long id_sede_recibo;

    private Integer nro_recibo = 0; //DEFAULT 0

    private Integer gestion;
}
