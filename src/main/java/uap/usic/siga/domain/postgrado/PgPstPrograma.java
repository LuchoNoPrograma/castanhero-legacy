package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pg_pst_programas")
public class PgPstPrograma extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pst_programas")
    private Long idPstProgramas;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_admision")
    @JsonIgnore
    private PgAdmTipoAdmision tipoAdmision;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_postulante")
    private PgPostulante postulante;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa")
    private PgPrograma programa;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pstPrograma")
    private List<PgPstDocumento> documentos;

    public PgPstPrograma() {
    }

    public PgPstPrograma(PgPostulante postulante, PgPrograma programa, PgAdmTipoAdmision tipoAdmision) {
        this.postulante = postulante;
        this.programa = programa;
        this.tipoAdmision = tipoAdmision;
    }

    public Long getIdPstProgramas() {
        return idPstProgramas;
    }

    public void setIdPstProgramas(Long idPstProgramas) {
        this.idPstProgramas = idPstProgramas;
    }

    public PgAdmTipoAdmision getTipoAdmision() {
        return tipoAdmision;
    }

    public void setTipoAdmision(PgAdmTipoAdmision tipoAdmision) {
        this.tipoAdmision = tipoAdmision;
    }

    public PgPostulante getPostulante() {
        return postulante;
    }

    public void setPostulante(PgPostulante postulante) {
        this.postulante = postulante;
    }

    public PgPrograma getPrograma() {
        return programa;
    }

    public void setPrograma(PgPrograma programa) {
        this.programa = programa;
    }

    public List<PgPstDocumento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<PgPstDocumento> documentos) {
        this.documentos = documentos;
    }
}
