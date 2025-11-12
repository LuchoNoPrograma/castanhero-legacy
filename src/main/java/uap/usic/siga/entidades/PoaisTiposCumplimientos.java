package uap.usic.siga.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "poais_tipos_cumplimientos")
public class PoaisTiposCumplimientos extends SigaUsicRevisiones{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_cumplimientoi")
    private Long idTipoCumplimiento;
	
	@Column(name = "tipo_cumplimiento")
    private String tipoCumplimiento;

	public Long getIdTipoCumplimiento() {
		return idTipoCumplimiento;
	}

	public void setIdTipoCumplimiento(Long idTipoCumplimiento) {
		this.idTipoCumplimiento = idTipoCumplimiento;
	}

	public String getTipoCumplimiento() {
		return tipoCumplimiento;
	}

	public void setTipoCumplimiento(String tipoCumplimiento) {
		this.tipoCumplimiento = tipoCumplimiento;
	}

	
}
