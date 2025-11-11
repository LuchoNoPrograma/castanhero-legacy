package uap.usic.siga.entidadesPg;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pg_prg_planes")
public class PgPrgPlanes extends SigaUsicRevisiones{
    /*==================RELACIONES==================
     * id_facultad
     * 
     * id_programa
     * 
     * id_tipo_grado
     * 
     * ult_usuario did_usuario
     * 
     * _id_estado character(1)
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_intermedio", referencedColumnName = "id_intermedio")
    private PgPrgIntermedios intermedio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
    private List<PgPrgModulos> modulos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    private Programas programa;

    //------------------Atributos--------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planes")
    private Long idPgPrgPlanes;

    private String plan;

    private String horasAcademicas;

    private String gestion;
    
    private String validacion;

    //------------------------------------------------
    //-------------Getters and Setters----------------
    public PgPrgIntermedios getIntermedio() {
        return intermedio;
    }
    public void setIntermedio(PgPrgIntermedios intermedio) {
        this.intermedio = intermedio;
    }
    public Programas getPrograma() {
        return programa;
    }
    public void setPrograma(Programas programa) {
        this.programa = programa;
    }
    public Long getIdPgPrgPlanes() {
        return idPgPrgPlanes;
    }
    public void setIdPgPrgPlanes(Long idPgPrgPlanes) {
        this.idPgPrgPlanes = idPgPrgPlanes;
    }
    public String getPlan() {
        return plan;
    }
    public void setPlan(String plan) {
        this.plan = plan;
    }
    public String getHorasAcademicas() {
        return horasAcademicas;
    }
    public void setHorasAcademicas(String horasAcademicas) {
        this.horasAcademicas = horasAcademicas;
    }
    public String getGestion() {
        return gestion;
    }
    public void setGestion(String gestion) {
        this.gestion = gestion;
    }
    public String getValidacion() {
        return validacion;
    }
    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }
    public List<PgPrgModulos> getModulos() {
        return modulos;
    }
    public void setModulos(List<PgPrgModulos> modulos) {
        this.modulos = modulos;
    }
    
}
