package uap.usic.siga.entidades;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "cja_proveedores")
public class CjaProveedores extends SigaUsicRevisiones{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_proveedor")
    private Long idCjaProveedor;

    @Column(name = "proveedor")
    @NotNull
    private String proveedor;
    
    @Size(max = 20)
    @NotNull
    private String sigla;

    public Long getIdCjaProveedor() {
        return idCjaProveedor;
    }

    public void setIdCjaProveedor(Long idCjaProveedor) {
        this.idCjaProveedor = idCjaProveedor;
    }

   
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    

    
}
