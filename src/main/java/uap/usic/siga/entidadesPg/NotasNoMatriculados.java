package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notas_no_matriculados")
public class NotasNoMatriculados extends SigaUsicRevisiones{
    /*===================RELACIONES====================
     * id_matricula
     * 
     * id_estudiante???
     * 
     * id_materia???
     * 
     * id_grupo
     * 
     * id_convalidacion DEFAULT 0
     * 
     * ult_usuario did_usuario
     * 
     * id_rol integer DEFAULT 0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota_no_matriculado")
    private Long idNotaNoMatriculada;

    private Short gestion;

    private Short periodo;

    private short nota;

    private String folio;

    private String libro;

    private String observacion;

    public Long getIdNotaNoMatriculada() {
        return idNotaNoMatriculada;
    }

    public void setIdNotaNoMatriculada(Long idNotaNoMatriculada) {
        this.idNotaNoMatriculada = idNotaNoMatriculada;
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

    public short getNota() {
        return nota;
    }

    public void setNota(short nota) {
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
