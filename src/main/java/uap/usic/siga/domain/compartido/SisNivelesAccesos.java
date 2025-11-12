package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

/**
 * Entidad que representa niveles de acceso del sistema.
 */
@Entity
@Table(name = "sis_niveles_accesos")
public class SisNivelesAccesos extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_acceso")
    private Integer id;

    @Column(name = "nivel_acceso")
    private String nivelAcceso;

    public SisNivelesAccesos() {
    }

    public SisNivelesAccesos(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
}
