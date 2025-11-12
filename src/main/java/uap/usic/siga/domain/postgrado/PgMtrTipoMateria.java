package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_mtr_tipos_materias")
public class PgMtrTipoMateria extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_materia")
    private Long idTipoMateria;

    @Column(name = "tipomateria")
    private String tipoMateria;

    public Long getIdTipoMateria() {
        return idTipoMateria;
    }

    public void setIdTipoMateria(Long idTipoMateria) {
        this.idTipoMateria = idTipoMateria;
    }

    public String getTipoMateria() {
        return tipoMateria;
    }

    public void setTipoMateria(String tipoMateria) {
        this.tipoMateria = tipoMateria;
    }
}
