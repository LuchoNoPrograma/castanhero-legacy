package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pg_prg_pln_resoluciones")
public class PgPrgPlnResoluciones extends SigaUsicGestiones{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resoluciones")
    private Long idPgPrgPlnResoluciones;


    
}
