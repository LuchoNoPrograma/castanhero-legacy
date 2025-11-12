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
