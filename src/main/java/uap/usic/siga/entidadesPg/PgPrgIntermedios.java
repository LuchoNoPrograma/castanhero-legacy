package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pg_prg_intermedios")
public class PgPrgIntermedios {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    private Programas programa;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_intermedio")
    private Long idPgPrgintermedio;

    //analisis 08/11/2022

}
