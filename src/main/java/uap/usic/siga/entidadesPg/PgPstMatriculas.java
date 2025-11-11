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
@Table(name = "pg_pst_matriculas")
public class PgPstMatriculas extends SigaUsicGestiones{

    /*================RELACIONES=================
     * id_postulante
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    private PgPrgPlnGrupo grupo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pst_programas", referencedColumnName = "id_pst_programas")
    private PgPstProgramas pstProgramas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matriculas")
    private List<EjecucionModuloEstudiante> ejecucionesModulos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;

    //clave dtexto2 DEFAULT('123456::dtexto2')::dtexto2
    private String numMatricula;
    private String clave = "123456";

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(String numMatricula) {
        this.numMatricula = numMatricula;
    }

    public PgPrgPlnGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(PgPrgPlnGrupo grupo) {
        this.grupo = grupo;
    }

    public PgPstProgramas getPstProgramas() {
        return pstProgramas;
    }

    public void setPstProgramas(PgPstProgramas pstProgramas) {
        this.pstProgramas = pstProgramas;
    }

    public List<EjecucionModuloEstudiante> getEjecucionesModulos() {
        return ejecucionesModulos;
    }

    public void setEjecucionesModulos(List<EjecucionModuloEstudiante> ejecucionesModulos) {
        this.ejecucionesModulos = ejecucionesModulos;
    }
}
