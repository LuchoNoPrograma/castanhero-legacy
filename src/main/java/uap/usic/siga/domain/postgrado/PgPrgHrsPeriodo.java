package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

@Entity
@Table(name = "pg_prg_hrs_periodo")
public class PgPrgHrsPeriodo extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hrs_periodo")
    private Long idHrsPeriodo;

    @Column(name = "periodo")
    private Short periodo;

    @Column(name = "indice")
    private Short indice;

    public Long getIdHrsPeriodo() {
        return idHrsPeriodo;
    }

    public void setIdHrsPeriodo(Long idHrsPeriodo) {
        this.idHrsPeriodo = idHrsPeriodo;
    }

    public Short getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Short periodo) {
        this.periodo = periodo;
    }

    public Short getIndice() {
        return indice;
    }

    public void setIndice(Short indice) {
        this.indice = indice;
    }
}
