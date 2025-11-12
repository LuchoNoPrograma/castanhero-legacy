package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_pst_universidades")
public class PgPstUniversidad extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pst_universidad")
    private Long idPstUniversidad;

    @Column(name = "anio_egreso")
    private Short anioEgreso;

    public Long getIdPstUniversidad() {
        return idPstUniversidad;
    }

    public void setIdPstUniversidad(Long idPstUniversidad) {
        this.idPstUniversidad = idPstUniversidad;
    }

    public Short getAnioEgreso() {
        return anioEgreso;
    }

    public void setAnioEgreso(Short anioEgreso) {
        this.anioEgreso = anioEgreso;
    }
}
