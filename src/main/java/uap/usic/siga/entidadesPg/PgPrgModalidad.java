package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pg_prg_modalidades")
public class PgPrgModalidad{
    /*===================RELACIONES======================
     * id_prg_grado_academico
     * 
     * id_programa
     * 
     * id_grado_academico
     * 
     * id_tipo_graduacion
     * 
     * id_plan
     * 
     * ult_usuario did_usuario
     * 
     * id_rol integer DEFAULT 0
     */
    //==================== Atributos ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modalidad")
    private Long id_modalidad;

    private String modalidad;
    
    private Short nivel;

    

    public PgPrgModalidad(String modalidad, Short nivel) {
        this.modalidad = modalidad;
        this.nivel = nivel;
    }
    public PgPrgModalidad() {
    }
    //====================Getters y Setters================
    public Long getId_modalidad() {
        return id_modalidad;
    }
    public void setId_modalidad(Long id_modalidad) {
        this.id_modalidad = id_modalidad;
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
