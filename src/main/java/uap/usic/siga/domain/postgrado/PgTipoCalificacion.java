package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import jakarta.persistence.*;

@Entity
@Table(name = "tipos_calificaciones")
public class PgTipoCalificacion extends EntidadAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_calificacion")
    private Long idTipoCalificacion;

    @Column(name = "tipo_calificacion")
    private String tipoCalificacion;

    public Long getIdTipoCalificacion() { return idTipoCalificacion; }
    public void setIdTipoCalificacion(Long idTipoCalificacion) { this.idTipoCalificacion = idTipoCalificacion; }
    public String getTipoCalificacion() { return tipoCalificacion; }
    public void setTipoCalificacion(String tipoCalificacion) { this.tipoCalificacion = tipoCalificacion; }
}
