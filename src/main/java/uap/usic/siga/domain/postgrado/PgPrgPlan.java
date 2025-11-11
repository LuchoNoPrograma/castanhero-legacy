package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pg_prg_planes")
public class PgPrgPlan extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planes")
    private Long idPlan;

    @Column(name = "plan")
    private String plan;

    @Column(name = "horasAcademicas")
    private String horasAcademicas;

    @Column(name = "gestion")
    private String gestion;

    @Column(name = "validacion")
    private String validacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_intermedio", referencedColumnName = "id_intermedio")
    private PgPrgIntermedio intermedio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
    private List<PgPrgModulo> modulos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    private PgPrograma programa;

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
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

    public PgPrgIntermedio getIntermedio() {
        return intermedio;
    }

    public void setIntermedio(PgPrgIntermedio intermedio) {
        this.intermedio = intermedio;
    }

    public List<PgPrgModulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<PgPrgModulo> modulos) {
        this.modulos = modulos;
    }

    public PgPrograma getPrograma() {
        return programa;
    }

    public void setPrograma(PgPrograma programa) {
        this.programa = programa;
    }

    public boolean estaValidado() {
        return "S".equalsIgnoreCase(validacion) || "SI".equalsIgnoreCase(validacion);
    }
}
