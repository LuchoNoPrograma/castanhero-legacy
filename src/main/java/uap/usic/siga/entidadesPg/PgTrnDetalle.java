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
@Table(name = "pg_trn_detalles")
public class PgTrnDetalle extends SigaUsicRevisiones{
    /*=================RELACIONES==================
     * id_transaccion
     * 
     * id_perfil
     * 
     * id_concepto
     * 
     * id_tipo_perfil
     * 
     * id_tipo_clasificacion
     * 
     * ult_usuario did_usuario
     * 
     * id_tipo_cuenta dentero DEFAULT 1
     * 
     * _id_estado dtexto2
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id_detalle;

    private Integer cantidad = 1;//DEFAULT 1

    //costo dmoneda2 <- Observar

    //pagado dmoneda2 <- Observar

    //descuento dmoneda2 <- Observar

    private String ulp_ip;

    private Integer mpst;
}
