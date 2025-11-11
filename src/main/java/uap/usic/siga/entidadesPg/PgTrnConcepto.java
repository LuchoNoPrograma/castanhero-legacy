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
@Table(name = "pg_trn_conceptos")
public class PgTrnConcepto extends SigaUsicRevisiones{
    /*===============RELACIONES================= 
     * ult_usuario did_usuario
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepto")
    private Long id_concepto;

    private String concepto;
}
