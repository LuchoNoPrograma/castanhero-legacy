package uap.usic.siga.dto.pgDto;
import java.util.List;
import uap.usic.siga.entidadesPg.GradoAcademico;
import uap.usic.siga.entidadesPg.PgPrgModalidad;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.entidadesPg.Postulantes;

public class ProgramasResponse {
    private Long idPrograma;
    private String programa;
    private Short horasVirtuales;
    private Short horasInvestigacion;
    private Short creditos;
    private Short cantidadModulos;
    private Short disponibilidad;
    private String sigla;
    private PgPrgModalidad modalidad;
    private GradoAcademico grado;
    private List<PgPstProgramas> postulantesAdmitidos;
    private List<Postulantes> postulantes;
    public Long getIdPrograma() {
        return idPrograma;
    }
    public void setIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
    }
    public String getPrograma() {
        return programa;
    }
    public void setPrograma(String programa) {
        this.programa = programa;
    }
    public Short getHorasVirtuales() {
        return horasVirtuales;
    }
    public void setHorasVirtuales(Short horasVirtuales) {
        this.horasVirtuales = horasVirtuales;
    }
    public Short getHorasInvestigacion() {
        return horasInvestigacion;
    }
    public void setHorasInvestigacion(Short horasInvestigacion) {
        this.horasInvestigacion = horasInvestigacion;
    }
    public Short getCreditos() {
        return creditos;
    }
    public void setCreditos(Short creditos) {
        this.creditos = creditos;
    }
    public Short getCantidadModulos() {
        return cantidadModulos;
    }
    public void setCantidadModulos(Short cantidadModulos) {
        this.cantidadModulos = cantidadModulos;
    }
    public Short getDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponibilidad(Short disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public PgPrgModalidad getModalidad() {
        return modalidad;
    }
    public void setModalidad(PgPrgModalidad modalidad) {
        this.modalidad = modalidad;
    }
    public GradoAcademico getGrado() {
        return grado;
    }
    public void setGrado(GradoAcademico grado) {
        this.grado = grado;
    }
    public List<PgPstProgramas> getPostulantesAdmitidos() {
        return postulantesAdmitidos;
    }
    public void setPostulantesAdmitidos(List<PgPstProgramas> postulantesAdmitidos) {
        this.postulantesAdmitidos = postulantesAdmitidos;
    }
    public List<Postulantes> getPostulantes() {
        return postulantes;
    }
    public void setPostulantes(List<Postulantes> postulantes) {
        this.postulantes = postulantes;
    }
    
}
