package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "pg_pst_universidades")
public class PgPstUniversidades extends SigaUsicRevisiones{
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
