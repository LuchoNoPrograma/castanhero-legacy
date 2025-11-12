package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "oo_unidades_educativas")
public class OoUnidadesEducativas extends SigaUsicGestiones{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_unidad_educativa")
	private Long idUnidadEducativa;
	
	private String nombre;
	private String descripcion;
	private String imagen;
	
	
	public OoUnidadesEducativas() {
	
	}


	public Long getIdUnidadEducativa() {
		return idUnidadEducativa;
	}


	public void setIdUnidadEducativa(Long idUnidadEducativa) {
		this.idUnidadEducativa = idUnidadEducativa;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	
}
