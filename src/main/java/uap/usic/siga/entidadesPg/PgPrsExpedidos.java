package uap.usic.siga.entidadesPg;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "pg_prs_expedidos")
public class PgPrsExpedidos extends SigaUsicRevisiones{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expedido")
    private Long idExpedido;

    @Column(name = "dip_expedido")
    private String dipExpedido;

    @Column(name = "dip_expedido_sigla")
    private String dipExpedidoSigla;

    public Long getIdExpedido() {
        return idExpedido;
    }

    public void setIdExpedido(Long idExpedido) {
        this.idExpedido = idExpedido;
    }

    public String getDipExpedido() {
        return dipExpedido;
    }

    public void setDipExpedido(String dipExpedido) {
        this.dipExpedido = dipExpedido;
    }

    public String getDipExpedidoSigla() {
        return dipExpedidoSigla;
    }

    public void setDipExpedidoSigla(String dipExpedidoSigla) {
        this.dipExpedidoSigla = dipExpedidoSigla;
    }
    
    
}
