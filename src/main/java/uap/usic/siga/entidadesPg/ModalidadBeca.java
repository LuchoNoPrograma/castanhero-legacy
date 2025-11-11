package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modalidades_becas")
public class ModalidadBeca extends SigaUsicRevisiones{
    /*
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modalidad_beca")
    private Long idModalidadBeca;

    @Column(name = "modalidad_beca")
    private String modalidadBeca;

    public Long getIdModalidadBeca() {
        return idModalidadBeca;
    }

    public void setIdModalidadBeca(Long idModalidadBeca) {
        this.idModalidadBeca = idModalidadBeca;
    }

    public String getModalidadBeca() {
        return modalidadBeca;
    }

    public void setModalidadBeca(String modalidadBeca) {
        this.modalidadBeca = modalidadBeca;
    }

    
}
