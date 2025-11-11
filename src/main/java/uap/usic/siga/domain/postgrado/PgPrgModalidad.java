package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

@Entity
@Table(name = "pg_prg_modalidades")
public class PgPrgModalidad extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modalidad")
    private Long idModalidad;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "nivel")
    private Short nivel;

    public PgPrgModalidad() {
    }

    public PgPrgModalidad(String modalidad, Short nivel) {
        this.modalidad = modalidad;
        this.nivel = nivel;
    }

    public Long getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(Long idModalidad) {
        this.idModalidad = idModalidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Short getNivel() {
        return nivel;
    }

    public void setNivel(Short nivel) {
        this.nivel = nivel;
    }
}
