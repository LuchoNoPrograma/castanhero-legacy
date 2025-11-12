package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadConGestion;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_prg_pln_resoluciones")
public class PgPrgPlnResolucion extends EntidadConGestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resoluciones")
    private Long idResoluciones;

    public Long getIdResoluciones() {
        return idResoluciones;
    }

    public void setIdResoluciones(Long idResoluciones) {
        this.idResoluciones = idResoluciones;
    }
}
