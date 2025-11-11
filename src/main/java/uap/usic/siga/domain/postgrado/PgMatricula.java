package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

@Entity
@Table(name = "matriculas")
public class PgMatricula extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_pst_programas", referencedColumnName = "id_pst_programas")
    private PgPstPrograma postulante;

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PgPstPrograma getPostulante() {
        return postulante;
    }

    public void setPostulante(PgPstPrograma postulante) {
        this.postulante = postulante;
    }

    public boolean tieneCredenciales() {
        return matricula != null && !matricula.isEmpty() &&
               password != null && !password.isEmpty();
    }
}
