package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad que representa lugares de expedición de CI.
 * Rectorado - USIC
 * Fecha: 2019-06-01
 */
@Entity
@Table(name = "prs_ci_expedidos")
public class PrsCiExpedidos extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prs_ci_expedido")
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "prs_ci_expedido", nullable = false, length = 50)
    private String prsCiExpedido;

    @Size(max = 50)
    @NotNull
    @Column(name = "prs_ci_expedido_sigla", nullable = false, length = 50)
    private String prsCiExpedidoSigla;

    public PrsCiExpedidos() {
    }

    public PrsCiExpedidos(String prsCiExpedido, String prsCiExpedidoSigla) {
        this.prsCiExpedido = prsCiExpedido;
        this.prsCiExpedidoSigla = prsCiExpedidoSigla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrsCiExpedido() {
        return prsCiExpedido;
    }

    public void setPrsCiExpedido(String prsCiExpedido) {
        this.prsCiExpedido = prsCiExpedido;
    }

    public String getPrsCiExpedidoSigla() {
        return prsCiExpedidoSigla;
    }

    public void setPrsCiExpedidoSigla(String prsCiExpedidoSigla) {
        this.prsCiExpedidoSigla = prsCiExpedidoSigla;
    }
}
