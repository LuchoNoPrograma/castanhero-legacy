package uap.usic.siga.domain.compartido;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

/**
 * Entidad que representa tipos de roles de usuario.
 */
@Entity
@Table(name = "usr_tipos_roles")
public class UsrTiposRoles extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_rol")
    private Integer id;

    @Column(name = "tipo_rol")
    private String tipoRol;

    public UsrTiposRoles() {
    }

    public UsrTiposRoles(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
}
