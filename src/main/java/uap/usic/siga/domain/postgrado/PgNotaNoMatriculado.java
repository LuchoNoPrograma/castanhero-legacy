package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

@Entity
@Table(name = "notas_no_matriculados")
public class PgNotaNoMatriculado extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota_no_matriculado")
    private Long idNotaNoMatriculado;

    @Column(name = "gestion")
    private Short gestion;

    @Column(name = "periodo")
    private Short periodo;

    @Column(name = "nota")
    private Short nota;

    @Column(name = "folio")
    private String folio;

    @Column(name = "libro")
    private String libro;

    @Column(name = "observacion")
    private String observacion;

    public Long getIdNotaNoMatriculado() {
        return idNotaNoMatriculado;
    }

    public void setIdNotaNoMatriculado(Long idNotaNoMatriculado) {
        this.idNotaNoMatriculado = idNotaNoMatriculado;
    }

    public Short getGestion() {
        return gestion;
    }

    public void setGestion(Short gestion) {
        this.gestion = gestion;
    }

    public Short getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Short periodo) {
        this.periodo = periodo;
    }

    public Short getNota() {
        return nota;
    }

    public void setNota(Short nota) {
        this.nota = nota;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
