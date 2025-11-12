package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import jakarta.persistence.*;

@Entity
@Table(name = "pg_facultades")
public class PgFacultad extends EntidadAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facultad")
    private Long idFacultad;

    @Column(name = "facultad")
    private String facultad;

    public Long getIdFacultad() { return idFacultad; }
    public void setIdFacultad(Long idFacultad) { this.idFacultad = idFacultad; }
    public String getFacultad() { return facultad; }
    public void setFacultad(String facultad) { this.facultad = facultad; }
}
