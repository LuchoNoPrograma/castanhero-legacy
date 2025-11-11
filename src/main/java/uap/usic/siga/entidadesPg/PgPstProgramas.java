package uap.usic.siga.entidadesPg;

import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "pg_pst_programas")
public class PgPstProgramas extends SigaUsicRevisiones{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pst_programas")
    private Long idPstProgramas;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_admision")
    @JsonIgnore
    private PgAdmTiposAdmisiones tipoAdmision;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_postulante")
    private Postulantes postulantes;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_programa")
    private Programas programas;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pgPstProgramas")
    private List<PgPstDocumentos> pgPstDocumentos;

    public PgPstProgramas() {
    }

    public Long getIdPstProgramas() {
        return idPstProgramas;
    }

    public void setIdPstProgramas(Long idPstProgramas) {
        this.idPstProgramas = idPstProgramas;
    }

    public PgAdmTiposAdmisiones getTipoAdmision() {
        return tipoAdmision;
    }

    public void setTipoAdmision(PgAdmTiposAdmisiones tipoAdmision) {
        this.tipoAdmision = tipoAdmision;
    }

    public PgPstProgramas(Postulantes postulantes, Programas programas, PgAdmTiposAdmisiones tipoAdmision) {
        this.tipoAdmision = tipoAdmision;
        this.postulantes = postulantes;
        this.programas = programas;
    }

    public Postulantes getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(Postulantes postulantes) {
        this.postulantes = postulantes;
    }

    public Programas getProgramas() {
        return programas;
    }

    public void setProgramas(Programas programas) {
        this.programas = programas;
    }

    public List<PgPstDocumentos> getPgPstDocumentos() {
        return pgPstDocumentos;
    }

    public void setPgPstDocumentos(List<PgPstDocumentos> pgPstDocumentos) {
        this.pgPstDocumentos = pgPstDocumentos;
    }    

}

