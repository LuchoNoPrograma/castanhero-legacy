package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadConGestion;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pg_trn_perfiles_materias")
public class PgTrnPerfilMateria extends EntidadConGestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_materia")
    private Long idPerfilMateria;

    @Column(name = "periodo")
    private Short periodo;

    @Column(name = "costo")
    private BigDecimal costo;

    public Long getIdPerfilMateria() {
        return idPerfilMateria;
    }

    public void setIdPerfilMateria(Long idPerfilMateria) {
        this.idPerfilMateria = idPerfilMateria;
    }

    public Short getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Short periodo) {
        this.periodo = periodo;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}
