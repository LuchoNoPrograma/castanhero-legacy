package uap.usic.siga.entidades;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Rectorado - USIC
 * Fecha: 2019-09-27
 * @author Freddy Morales
 */

@Entity
@Table(name = "cja_pedidos_certificados")
public class CjaPedidosCertificados extends SigaUsicGestiones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_pedido_certificado")
    private Long idCjaPedidoCertificado;

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_certificado")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecCertificado;

}
