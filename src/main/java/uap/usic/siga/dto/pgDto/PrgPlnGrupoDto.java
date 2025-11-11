package uap.usic.siga.dto.pgDto;

import uap.usic.siga.entidadesPg.PgPrgPlnVersiones;
import uap.usic.siga.entidadesPg.Programas;

public class PrgPlnGrupoDto {
    private PgPrgPlnVersiones version;
    
    private Programas programas;

    private Long idPgPrgPlnGrupos;

    private String grupo;
    
    private Integer capacidadGrupo;

    public PgPrgPlnVersiones getVersion() {
        return version;
    }

    public void setVersion(PgPrgPlnVersiones version) {
        this.version = version;
    }

    public Long getIdPgPrgPlnGrupos() {
        return idPgPrgPlnGrupos;
    }

    public void setIdPgPrgPlnGrupos(Long idPgPrgPlnGrupos) {
        this.idPgPrgPlnGrupos = idPgPrgPlnGrupos;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getCapacidadGrupo() {
        return capacidadGrupo;
    }

    public void setCapacidadGrupo(Integer capacidadGrupo) {
        this.capacidadGrupo = capacidadGrupo;
    }

    public Programas getProgramas() {
        return programas;
    }

    public void setProgramas(Programas programas) {
        this.programas = programas;
    }
    
}
