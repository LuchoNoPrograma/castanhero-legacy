package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_prg_intermedios")
public class PgPrgIntermedio extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_intermedio")
    private Long idIntermedio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    private PgPrograma programa;

    public Long getIdIntermedio() {
        return idIntermedio;
    }

    public void setIdIntermedio(Long idIntermedio) {
        this.idIntermedio = idIntermedio;
    }

    public PgPrograma getPrograma() {
        return programa;
    }

    public void setPrograma(PgPrograma programa) {
        this.programa = programa;
    }
}
