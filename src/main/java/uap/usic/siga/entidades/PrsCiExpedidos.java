package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


/**
 * Rectorado - USIC
 * Fecha: 2019-06-01
 * @author Freddy Morales
 */
@Entity
@Table(name = "prs_ci_expedidos")
public class PrsCiExpedidos extends  SigaUsicRevisiones  {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prs_ci_expedido")
    private Long idPrsCiExpedido;

    @Size(max = 50)
    @NotNull
    @Column(name = "prs_ci_expedido")
    private String prsCiExpedido;
    
    @Size(max = 50)
    @NotNull
    @Column(name = "prs_ci_expedido_sigla")
    private String prsCiExpedidoSigla;

    

    public PrsCiExpedidos() {
    }

    public PrsCiExpedidos(@Size(max = 50) @NotNull String prsCiExpedido,
            @Size(max = 50) @NotNull String prsCiExpedidoSigla) {
        this.prsCiExpedido = prsCiExpedido;
        this.prsCiExpedidoSigla = prsCiExpedidoSigla;
    }

    public Long getIdPrsCiExpedido() {
        return idPrsCiExpedido;
    }

    public void setIdPrsCiExpedido(Long idPrsCiExpedido) {
        this.idPrsCiExpedido = idPrsCiExpedido;
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
