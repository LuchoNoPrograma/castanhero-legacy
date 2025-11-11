package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pg_prs_tipos_empresas_telefonicas")
public class PgPrsTiposEmpresasTelefonicas extends SigaUsicRevisiones{
    /*===================RELACIONES======================
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_empresa_telefonica")
    private Long idTipoEmpresaTelefonica;

    @Column(name = "tipo_empresa_telefonica")
    private String tipoEmpresaTelefonica;

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
