package uap.usic.siga.domain.compartido.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadConGestion extends EntidadAuditable {

    @Column(name = "gestion")
    private Short gestion;

    public Short getGestion() {
        return gestion;
    }

    public void setGestion(Short gestion) {
        this.gestion = gestion;
    }
}
