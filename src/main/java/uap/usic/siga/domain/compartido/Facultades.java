package uap.usic.siga.domain.compartido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad que representa facultades universitarias.
 */
@Entity
@Table(name = "facultades")
public class Facultades extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facultad")
    private Long id;

    @NotNull
    @Column(name = "facultad", nullable = false)
    private String facultad;

    @Column(name = "codigo")
    private String codigo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_universidad", referencedColumnName = "id_universidad")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Universidades universidades;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsSedes sedes;

    public Facultades() {
    }

    public Facultades(String facultad, String codigo, Universidades universidades, InsSedes sedes) {
        this.facultad = facultad;
        this.codigo = codigo;
        this.universidades = universidades;
        this.sedes = sedes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Universidades getUniversidades() {
        return universidades;
    }

    public void setUniversidades(Universidades universidades) {
        this.universidades = universidades;
    }

    public InsSedes getSedes() {
        return sedes;
    }

    public void setSedes(InsSedes sedes) {
        this.sedes = sedes;
    }
}
