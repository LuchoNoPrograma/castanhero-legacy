package uap.usic.siga.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "USR_IPS")
@SequenceGenerator(name = "SEQ_USR_IPS", allocationSize = 1)
public class UsrIps {
    
     @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuarios;
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usr_ip")   
    private Long idUsrIp;
  
   
    @Column(name = "id_estado", length = 60, nullable = false)
    private String idEstado;

   
    @Column(name = "ip", length = 60, nullable = false)
    private String ip;

}
