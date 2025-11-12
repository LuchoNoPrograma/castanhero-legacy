package uap.usic.siga.entidadesPg;
import jakarta.persistence.Column;
/**
 * 
 * @author Luis Morales V.
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "grados_academicos")
public class GradoAcademico extends SigaUsicRevisiones{
    /*==================RELACIONES===================
    id_rol

    ult_usuario did_usuario
     */

    /**
     * Es correcta la relacion?? Muchos grados academicos a una institucion?
     * o
     * Muchos grados academicos a muchas instituciones??
     */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_academico")
    private Long id_grado;

    private String gradoacademico;

    public GradoAcademico(String gradoacademico) {
        this.gradoacademico = gradoacademico;
    }

    public GradoAcademico() {
    }

    public Long getId_grado() {
        return id_grado;
    }

    public void setId_grado(Long id_grado) {
        this.id_grado = id_grado;
    }

    public String getGradoacademico() {
        return gradoacademico;
    }

    public void setGradoacademico(String gradoacademico) {
        this.gradoacademico = gradoacademico;
    }


}