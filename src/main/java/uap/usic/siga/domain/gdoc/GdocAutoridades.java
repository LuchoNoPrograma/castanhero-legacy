package uap.usic.siga.domain.gdoc;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SigaUsicGestiones;

@Entity
@Table(name = "gdoc_autoridades")
public class GdocAutoridades extends SigaUsicGestiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_consejo", referencedColumnName = "id_gdoc_consejo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocConsejos gdocConsejos;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gdoc_autoridad")
    private Long idGdocAutoridad;

	  @NotNull
	    @DateTimeFormat(pattern = "yyy-MM-dd")
	    @Column(name = "fec_inicio")
	    @Temporal(javax.persistence.TemporalType.DATE)
	    private Date fecInicio;

	    @NotNull
	    @DateTimeFormat(pattern = "yyy-MM-dd")
	    @Column(name = "fec_final")
	    @Temporal(javax.persistence.TemporalType.DATE)
	    private Date fecFinal;

		public Personas getPersonas() {
			return personas;
		}

		public void setPersonas(Personas personas) {
			this.personas = personas;
		}

		public GdocConsejos getGdocConsejos() {
			return gdocConsejos;
		}

		public void setGdocConsejos(GdocConsejos gdocConsejos) {
			this.gdocConsejos = gdocConsejos;
		}

		public Long getIdGdocAutoridad() {
			return idGdocAutoridad;
		}

		public void setIdGdocAutoridad(Long idGdocAutoridad) {
			this.idGdocAutoridad = idGdocAutoridad;
		}

		public Date getFecInicio() {
			return fecInicio;
		}

		public void setFecInicio(Date fecInicio) {
			this.fecInicio = fecInicio;
		}

		public Date getFecFinal() {
			return fecFinal;
		}

		public void setFecFinal(Date fecFinal) {
			this.fecFinal = fecFinal;
		}
}
