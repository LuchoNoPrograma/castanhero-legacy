package uap.usic.siga.entidadesPg;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
