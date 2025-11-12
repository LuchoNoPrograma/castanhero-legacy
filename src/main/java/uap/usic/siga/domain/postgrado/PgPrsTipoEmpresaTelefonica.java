package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import jakarta.persistence.*;

@Entity
@Table(name = "pg_prs_tipos_empresas_telefonicas")
public class PgPrsTipoEmpresaTelefonica extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_empresa_telefonica")
    private Long idTipoEmpresaTelefonica;

    @Column(name = "tipo_empresa_telefonica")
    private String tipoEmpresaTelefonica;

    @Column(name = "direccion")
    private String direccion;

    public Long getIdTipoEmpresaTelefonica() {
        return idTipoEmpresaTelefonica;
    }

    public void setIdTipoEmpresaTelefonica(Long idTipoEmpresaTelefonica) {
        this.idTipoEmpresaTelefonica = idTipoEmpresaTelefonica;
    }

    public String getTipoEmpresaTelefonica() {
        return tipoEmpresaTelefonica;
    }

    public void setTipoEmpresaTelefonica(String tipoEmpresaTelefonica) {
        this.tipoEmpresaTelefonica = tipoEmpresaTelefonica;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
