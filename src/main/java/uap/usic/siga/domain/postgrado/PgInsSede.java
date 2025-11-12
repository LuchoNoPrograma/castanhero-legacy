package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import jakarta.persistence.*;

@Entity
@Table(name = "pg_ins_sedes")
public class PgInsSede extends EntidadAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede")
    private Long idSede;

    @Column(name = "sede")
    private String sede;

    public Long getIdSede() { return idSede; }
    public void setIdSede(Long idSede) { this.idSede = idSede; }
    public String getSede() { return sede; }
    public void setSede(String sede) { this.sede = sede; }
}
