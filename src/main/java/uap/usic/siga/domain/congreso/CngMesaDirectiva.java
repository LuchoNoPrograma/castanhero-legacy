package uap.usic.siga.domain.congreso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import uap.usic.siga.entidades.SigaUsicRevisiones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "cng_mesa_directiva")
public class CngMesaDirectiva extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa_directiva")
    private Long idMesaDirectiva;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cng_congresista")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngCongresistas cngCongresistas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_cargo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngTiposCargos cngTiposCargos;

    @Column(name = "codigo")
    private String codigo;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecInicio;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_final", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecFinal;

    public Long getIdMesaDirectiva() {
        return idMesaDirectiva;
    }

    public void setIdMesaDirectiva(Long idMesaDirectiva) {
        this.idMesaDirectiva = idMesaDirectiva;
    }

    public CngCongresistas getCngCongresistas() {
        return cngCongresistas;
    }

    public void setCngCongresistas(CngCongresistas cngCongresistas) {
        this.cngCongresistas = cngCongresistas;
    }

    public CngTiposCargos getCngTiposCargos() {
        return cngTiposCargos;
    }

    public void setCngTiposCargos(CngTiposCargos cngTiposCargos) {
        this.cngTiposCargos = cngTiposCargos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecFinal() {
        return fecFinal;
    }

    public void setFecFinal(Date fecFinal) {
        this.fecFinal = fecFinal;
    }
}
