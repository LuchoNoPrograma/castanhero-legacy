package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import javax.persistence.*;

@Entity
@Table(name = "pg_ins_campus")
public class PgInsCampus extends EntidadAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campus")
    private Long idCampus;

    @Column(name = "campus")
    private String campus;

    public Long getIdCampus() { return idCampus; }
    public void setIdCampus(Long idCampus) { this.idCampus = idCampus; }
    public String getCampus() { return campus; }
    public void setCampus(String campus) { this.campus = campus; }
}
