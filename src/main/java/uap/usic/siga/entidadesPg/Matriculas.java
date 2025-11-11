package uap.usic.siga.entidadesPg;

import javax.persistence.FetchType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "matriculas")
public class Matriculas extends SigaUsicRevisiones{

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_pst_programas", referencedColumnName = "id_pst_programas")
    private PgPstProgramas postulante;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;
	
	@Column(name = "matricula")
    private String matricula;

	
	@Column(name = "password")
    private String password;

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

	public PgPstProgramas getPostulante() {
		return postulante;
	}

	public void setPostulante(PgPstProgramas postulante) {
		this.postulante = postulante;
	}
	
	
	
}
