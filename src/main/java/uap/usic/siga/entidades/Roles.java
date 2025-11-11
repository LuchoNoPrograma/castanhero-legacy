package uap.usic.siga.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import uap.usic.siga.anotacionesCliente.ValidRoleName;

@Entity
@Table(name="roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidRoleName
    @Column(unique = true)
    private String name;

    @JsonManagedReference
    @ManyToMany(mappedBy = "roles")
     private Set<Usuarios> users = new HashSet<>();

    public Roles() {
    }

    public Roles(String name) {
        super();
        this.name = name;
    }

    public Roles(String name, Set<Usuarios> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Usuarios> getUsers() {
        return users;
    }

    public void setUsers(Set<Usuarios> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
