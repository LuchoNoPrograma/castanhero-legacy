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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "poais")
public class Poais extends SigaUsicGestiones {
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_personal_administrativo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlPersonalAdministrativos pnlPersonalAdministrativos;
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_poai")
    private Long idPoai;
	
	@Column(name = "descripcion")
    private String descripcion;

	public PnlPersonalAdministrativos getPnlPersonalAdministrativos() {
		return pnlPersonalAdministrativos;
	}

	public void setPnlPersonalAdministrativos(PnlPersonalAdministrativos pnlPersonalAdministrativos) {
		this.pnlPersonalAdministrativos = pnlPersonalAdministrativos;
	}

	public Long getIdPoai() {
		return idPoai;
	}

	public void setIdPoai(Long idPoai) {
		this.idPoai = idPoai;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
 
	
}
