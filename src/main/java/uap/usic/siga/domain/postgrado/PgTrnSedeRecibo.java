package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

@Entity
@Table(name = "pg_trn_sedes_recibos")
public class PgTrnSedeRecibo extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede_recibo")
    private Long idSedeRecibo;

    @Column(name = "nro_recibo")
    private Integer nroRecibo = 0;

    @Column(name = "gestion")
    private Integer gestion;

    public Long getIdSedeRecibo() {
        return idSedeRecibo;
    }

    public void setIdSedeRecibo(Long idSedeRecibo) {
        this.idSedeRecibo = idSedeRecibo;
    }

    public Integer getNroRecibo() {
        return nroRecibo;
    }

    public void setNroRecibo(Integer nroRecibo) {
        this.nroRecibo = nroRecibo;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }
}
