package uap.usic.siga.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
*
* @author Freddy Morales
*/
@Entity
@Table(name = "congreso_uap")
public class CongresoUap extends SigaUsicGestiones{

	   
	@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "id_universidad", referencedColumnName = "id_universidad")
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private Universidades universidades;
	    
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "id_congreso_uap")
	    private Long idCongresoUap;

	    @NotNull
	    @Column(name = "congreso")
	    private String congreso;
	    
	    @Column(name = "codigo")
	    private String codigo;

		public Universidades getUniversidades() {
			return universidades;
		}

		public void setUniversidades(Universidades universidades) {
			this.universidades = universidades;
		}

		public Long getIdCongresoUap() {
			return idCongresoUap;
		}

		public void setIdCongresoUap(Long idCongresoUap) {
			this.idCongresoUap = idCongresoUap;
		}

		public String getCongreso() {
			return congreso;
		}

		public void setCongreso(String congreso) {
			this.congreso = congreso;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
	    
	    
}
