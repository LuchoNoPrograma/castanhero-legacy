package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_prs_expedidos")
public class PgPrsExpedido extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expedido")
    private Long idExpedido;

    @Column(name = "dip_expedido")
    private String expedido;

    @Column(name = "dip_expedido_sigla")
    private String expedidoSigla;

    public Long getIdExpedido() {
        return idExpedido;
    }

    public void setIdExpedido(Long idExpedido) {
        this.idExpedido = idExpedido;
    }

    public String getExpedido() {
        return expedido;
    }

    public void setExpedido(String expedido) {
        this.expedido = expedido;
    }

    public String getExpedidoSigla() {
        return expedidoSigla;
    }

    public void setExpedidoSigla(String expedidoSigla) {
        this.expedidoSigla = expedidoSigla;
    }
}
