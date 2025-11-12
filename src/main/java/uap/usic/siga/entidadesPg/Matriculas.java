package uap.usic.siga.entidadesPg;

import jakarta.persistence.FetchType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
