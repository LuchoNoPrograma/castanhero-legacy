package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contratos")
public class Contrato extends SigaUsicRevisiones{
    /*===================RELACIONES====================== 
     * id_programa
     * 
     * id_usuario
     * 
     * id_estudiante???
     * 
     * ult_usuario did_usuario
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Long id_contrato;

    private Double total; //numeric(7,2)

    private Double saldo; //numeric(7,2)

    @Column(name = "num_cuotas")
    private Integer numCuotas;

    public Long getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(Long id_contrato) {
        this.id_contrato = id_contrato;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getNumCuotas() {
        return numCuotas;
    }

    public void setNumCuotas(Integer numCuotas) {
        this.numCuotas = numCuotas;
    }

    
}
