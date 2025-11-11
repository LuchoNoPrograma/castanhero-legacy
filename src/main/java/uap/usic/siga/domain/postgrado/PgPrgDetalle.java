package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pg_prg_detalles")
public class PgPrgDetalle extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalle;

    @Column(name = "gestion")
    private Short gestion;

    @Column(name = "periodo")
    private Short periodo;

    @Column(name = "max_niveles")
    private Short maxNiveles;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @Column(name = "max_materias_teoricas")
    private Short maxMateriasTeo ricas;

    @Column(name = "max_materias_laboratorios")
    private Short maxMateriasLaboratorios;

    @Column(name = "costo_materia_teorica")
    private Double costoMateriaTeorica = 0.0;

    @Column(name = "costo_materia_laboratorio")
    private Double costoMateriaLaboratorio = 0.0;

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
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

    public Short getMaxNiveles() {
        return maxNiveles;
    }

    public void setMaxNiveles(Short maxNiveles) {
        this.maxNiveles = maxNiveles;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Short getMaxMateriasTeo ricas() {
        return maxMateriasTeo ricas;
    }

    public void setMaxMateriasTeo ricas(Short maxMateriasTeo ricas) {
        this.maxMateriasTeo ricas = maxMateriasTeo ricas;
    }

    public Short getMaxMateriasLaboratorios() {
        return maxMateriasLaboratorios;
    }

    public void setMaxMateriasLaboratorios(Short maxMateriasLaboratorios) {
        this.maxMateriasLaboratorios = maxMateriasLaboratorios;
    }

    public Double getCostoMateriaTeorica() {
        return costoMateriaTeorica;
    }

    public void setCostoMateriaTeorica(Double costoMateriaTeorica) {
        this.costoMateriaTeorica = costoMateriaTeorica;
    }

    public Double getCostoMateriaLaboratorio() {
        return costoMateriaLaboratorio;
    }

    public void setCostoMateriaLaboratorio(Double costoMateriaLaboratorio) {
        this.costoMateriaLaboratorio = costoMateriaLaboratorio;
    }
}
